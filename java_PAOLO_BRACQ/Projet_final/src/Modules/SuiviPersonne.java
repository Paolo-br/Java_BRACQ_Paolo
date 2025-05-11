package Modules;

import Model.*;
import java.util.*;

public class SuiviPersonne implements ModuleSpecialise {

    //------------------------ Déclaration des variables -----------------------------

    private Personne suivie;  // La personne à surveiller
    private Map<Media, Integer> mentionsParMedia = new HashMap<>();  // Nombre de mentions par média
    private List<Publication> historique = new ArrayList<>();  // Historique des publications liées à la personne
    private Vigie vigie;  // Système d’alerte

    //------------------------ Déclaration du constructeur -----------------------------

    public SuiviPersonne(Personne suivie, Vigie vigie) {
        this.suivie = suivie;
        this.vigie = vigie;
    }

    //------------------------Déclaration des méthodes-----------------------------

    //------------------------ Méthode utilitaire -----------------------------

    /**
     * Vérifie si une entité (propriétaire) possède une autre entité (cible) dans les relations passées
     */
    private boolean estProprietaire(Entite cible, Entite proprietaire, List<Proprietaire<? extends Entite, ? extends Entite>> relations) {
        for (Proprietaire<? extends Entite, ? extends Entite> relation : relations) {
            if (relation.getCible().equals(cible) && relation.getSource().equals(proprietaire)) {
                return true;
            }
        }
        return false;
    }

    //------------------------ Méthodes héritées -----------------------------

    /**
     * Traite un événement de publication : enregistre la mention et génère une alerte si le média appartient à la personne suivie
     */
    @Override
    public void traiterEvenement(Publication publication, List<Proprietaire<? extends Entite, ? extends Entite>> toutesRelations) {
        for (Entite m : publication.getMentions()) {
            if (m.equals(suivie)) {
                historique.add(publication);
                mentionsParMedia.merge(publication.getMedia(), 1, Integer::sum);

                // Alerte si le média appartient (même partiellement) à la personne suivie
                if (estProprietaire(publication.getMedia(), suivie, toutesRelations)) {
                    vigie.recevoirAlerte("Alerte : " + suivie.getNom() + " mentionné(e) dans un média qu'il/elle détient.");
                }
            }
        }
    }

    /**
     * Traite un rachat par la personne suivie : déclenche une alerte sur le média ou groupe acquis
     */
    @Override
    public void traiterRachat(Proprietaire rachat) {
        if (rachat.getSource().equals(suivie)) {
            String cible = rachat.getCible().getNom();
            if (rachat.getCible() instanceof Media) {
                vigie.recevoirAlerte(suivie + " a acquis des parts dans le média : " + cible);
            } else if (rachat.getCible() instanceof GroupInd) {
                vigie.recevoirAlerte(suivie + " a acquis des parts dans le groupe : " + cible);
            }
        }
    }

    //------------------------ Méthodes supplémentaires -----------------------------

    /**
     * Affiche l’historique des publications liées à la personne suivie, et les statistiques par média
     */
    public void afficherStats() {
        System.out.println("Historique des événements concernant " + suivie + " :");
        historique.forEach(System.out::println);

        System.out.println("\nMentions par média :");
        int total = historique.size();
        if (total == 0) {
            System.out.println("Aucune publication liée à " + suivie.getNom() + " pour l’instant.");
            return;
        }
        mentionsParMedia.forEach((media, count) -> {
            double pourcentage = (count * 100.0) / total;
            System.out.printf(" - %s : %d mentions (%.2f%%)\n", media.getNom(), count, pourcentage);
        });
    }

    /**
     * Méthode pour créer dynamiquement un module de suivi sur une personne choisie par l'utilisateur
     */
    public static void creationModule(List<ModuleSpecialise> modulesSpecialises, Vigie vigie, Scanner scanner, List<Personne> personnes) {
        System.out.print("Qui souhaitez-vous suivre ? ");
        String nom = scanner.nextLine();

        // Recherche de la personne dans la liste
        Optional<Personne> personneSuivie = personnes.stream()
                .filter(p -> p.getNom().equalsIgnoreCase(nom))
                .findFirst();

        if (personneSuivie.isPresent()) {
            modulesSpecialises.add(new SuiviPersonne(personneSuivie.get(), vigie));
            System.out.println("Module de suivi créé pour " + nom);
        } else {
            System.out.println("Personne non trouvée. Assurez-vous que le nom est exact.");
        }
    }
}
