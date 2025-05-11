package Modules;

import Model.*;
import java.util.*;

public class SuiviMedia implements ModuleSpecialise {

    //------------------------Déclaration des variables-----------------------------

    private Media mediaSuivi;  // Média surveillé par ce module
    private Map<Entite, Integer> mentionsParEntite = new HashMap<>();  // Nombre de mentions par entité
    private List<Proprietaire<? extends Entite, ? extends Entite>> historiqueRachats = new ArrayList<>();  // Historique des rachats
    private Vigie vigie;  // Système d'alerte
    private Set<Entite> anciensProprietaires = new HashSet<>();  // Ensemble des anciens propriétaires détectés
    private final int SEUIL_MENTIONS = 5;  // Seuil de mentions déclenchant une alerte
    private final double SEUIL_POURCENTAGE = 30;  // Seuil de pourcentage de mentions pour alerte

    //------------------------Déclaration du constructeur-----------------------------

    public SuiviMedia(Media suivie, Vigie vigie){
        this.mediaSuivi = suivie;
        this.vigie = vigie;
    }

    //------------------------Déclaration des méthodes-----------------------------

    /**
     * Traitement d'un événement de type Publication : comptabilise les mentions
     * et génère une alerte si les seuils sont dépassés.
     */
    @Override
    public void traiterEvenement(Publication publication, List<Proprietaire<? extends Entite, ? extends Entite>> relations) {
        if (!publication.getMedia().equals(mediaSuivi)) return;

        for (Entite e : publication.getMentions()) {
            mentionsParEntite.merge(e, 1, Integer::sum);

            int totalMentions = mentionsParEntite.values().stream().mapToInt(i -> i).sum();
            int nbMentions = mentionsParEntite.get(e);
            double pourcentage = (nbMentions * 100.0) / totalMentions;

            if (nbMentions >= SEUIL_MENTIONS | pourcentage >= SEUIL_POURCENTAGE) {
                vigie.recevoirAlerte(
                        "Alerte : " + e.getNom() + " est mentionné(e) très fréquemment (" +
                                nbMentions + " fois, soit " + String.format("%.2f", pourcentage) + "%) dans " + mediaSuivi.getNom()
                );
            }
        }
    }

    /**
     * Traitement d’un nouveau rachat concernant le média surveillé
     * Enregistre le rachat et génère une alerte si nouveau propriétaire
     */
    @Override
    public void traiterRachat(Proprietaire rachat) {
        if (!rachat.getCible().equals(mediaSuivi)) return;

        historiqueRachats.add(rachat);
        Entite source = rachat.getSource();

        if (!anciensProprietaires.contains(source)) {
            anciensProprietaires.add(source);
            vigie.recevoirAlerte("Alerte : Nouvelle entité " + source.getNom() + " est/devient propriétaire (même partiellement) de " + mediaSuivi.getNom());
        }
    }


    //------------------------ Méthodes supplémentaires -----------------------------
    /**
     * Création dynamique du module en demandant à l’utilisateur le nom du média à suivre
     */
    public static void creationModule(List<ModuleSpecialise> modulesSpecialises, Vigie vigie, Scanner scanner, List<Media> medias) {
        System.out.print("Quel média souhaitez-vous suivre ? ");
        String nom = scanner.nextLine();

        // Chercher le média dans la liste
        Optional<Media> mediaSuivi = medias.stream()
                .filter(m -> m.getNom().equalsIgnoreCase(nom))
                .findFirst();

        if (mediaSuivi.isPresent()) {
            // Créer un module de suivi pour le média spécifié
            modulesSpecialises.add(new SuiviMedia(mediaSuivi.get(), vigie));
            System.out.println("Module de suivi créé pour le média : " + nom);
        } else {
            System.out.println("Média non trouvé. Assurez-vous que le nom est exact.");
        }
    }

    /**
     * Affichage des statistiques sur les mentions et les rachats du média surveillé
     */
    public void afficherStats() {
        System.out.println("\nMentions dans le média " + mediaSuivi.getNom() + " :");
        mentionsParEntite.forEach((entite, count) -> {
            int total = mentionsParEntite.values().stream().mapToInt(i -> i).sum();
            double pourcentage = (count * 100.0) / total;
            System.out.printf(" - %s : %d mentions (%.2f%%)\n", entite.getNom(), count, pourcentage);
        });

        System.out.println("\nHistorique des rachats pour " + mediaSuivi.getNom() + " :");
        historiqueRachats.forEach(System.out::println);
    }

}
