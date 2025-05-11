import Model.*;
import Modules.*;

import java.util.*;

public class SimulationEvenement {

    //------------------------Déclaration des méthodes-----------------------------

    /**
     * Méthode pour simuler un rachat entre entités.
     * @param proprietaires la liste des propriétaires
     * @param scanner le scanner pour la saisie utilisateur
     * @param modules la liste des modules spécialisés
     */
    public static void Rachat(List<Proprietaire<? extends Entite, ? extends Entite>> proprietaires,
                              Scanner scanner,
                              List<ModuleSpecialise> modules,
                              List<Personne> personnes,
                              List<GroupInd> organisations) {

        System.out.print("Nom de l'entité acheteuse : ");
        String acheteurNom = scanner.nextLine();

        System.out.print("Nom de la cible (média ou organisation) : ");
        String cibleNom = scanner.nextLine();

        System.out.print("Pourcentage de parts à racheter (ex: 20) : ");
        double pourcentage = scanner.nextDouble();
        scanner.nextLine();

        // Trouver un vendeur valide
        Proprietaire<? extends Entite, ? extends Entite> vendeur = null;
        for (Proprietaire<? extends Entite, ? extends Entite> p : proprietaires) {
            if (p.getCible().getNom().equalsIgnoreCase(cibleNom)
                    && !p.getSource().getNom().equalsIgnoreCase(acheteurNom)
                    && p.getPart() >= pourcentage) {
                vendeur = p;
                break;
            }
        }

        if (vendeur == null) {
            System.out.println("Aucun propriétaire trouvé avec assez de parts pour vendre.");
            return;
        }

        // Vérification que la somme des parts n'excède pas 100%
        double totalParts = 0.0;
        double partAcheteurAvant = 0.0;
        for (Proprietaire<? extends Entite, ? extends Entite> p : proprietaires) {
            if (p.getCible().getNom().equalsIgnoreCase(cibleNom)) {
                totalParts += p.getPart();
                if (p.getSource().getNom().equalsIgnoreCase(acheteurNom)) {
                    partAcheteurAvant = p.getPart();
                }
            }
        }

        if (partAcheteurAvant + pourcentage > 100.0) {
            System.out.println("Erreur : l'acheteur aurait plus de 100% des parts.");
            return;
        }

        if ((totalParts - vendeur.getPart() + (vendeur.getPart() - pourcentage) + partAcheteurAvant + pourcentage) > 100.0) {
            System.out.println("Erreur : la somme totale des parts dépasserait 100%.");
            return;
        }

        // Appliquer la transaction
        vendeur.setPart(vendeur.getPart() - pourcentage);

        // Rechercher si acheteur existe déjà
        Proprietaire<? extends Entite, ? extends Entite> acheteurExistant = null;
        for (Proprietaire<? extends Entite, ? extends Entite> p : proprietaires) {
            if (p.getCible().getNom().equalsIgnoreCase(cibleNom)
                    && p.getSource().getNom().equalsIgnoreCase(acheteurNom)) {
                acheteurExistant = p;
                break;
            }
        }

        if (acheteurExistant != null) {
            acheteurExistant.setPart(acheteurExistant.getPart() + pourcentage);
        } else {
            // Trouver l'entité acheteur (Personne ou Organisation)
            Entite acheteur = null;
            for (Personne p : personnes) {
                if (p.getNom().equalsIgnoreCase(acheteurNom)) {
                    acheteur = p;
                    break;
                }
            }
            if (acheteur == null) {
                for (GroupInd g : organisations) {
                    if (g.getNom().equalsIgnoreCase(acheteurNom)) {
                        acheteur = g;
                        break;
                    }
                }
            }

            if (acheteur == null) {
                System.out.println("Acheteur introuvable.");
                vendeur.setPart(vendeur.getPart() + pourcentage); // rollback
                return;
            }

            int newID = proprietaires.stream().mapToInt(Proprietaire::getID).max().orElse(0) + 1;
            Proprietaire<Entite, Entite> nouveauProprietaire = new Proprietaire<>(newID, acheteur, "", String.format("%.2f", pourcentage), vendeur.getCible(), "");
            proprietaires.add(nouveauProprietaire);
            acheteurExistant = nouveauProprietaire;
        }

        System.out.println("Rachat effectué !");
        System.out.println("Acheteur -> " + acheteurExistant);
        System.out.println("Vendeur -> " + vendeur);

        for (ModuleSpecialise module : modules) {
            module.traiterRachat(acheteurExistant);
        }
    }


    /**
     * Méthode pour simuler une publication dans un média.
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param organisations la liste des organisations
     * @param scanner le scanner pour la saisie utilisateur
     * @param relations la liste des relations de propriété
     * @param modules la liste des modules spécialisés
     */
    public static void Publication(List<Media> medias, List<Personne> personnes, List<GroupInd> organisations, Scanner scanner, List<Proprietaire<? extends Entite, ? extends Entite>> relations, List<ModuleSpecialise> modules,List<Publication>ensemblePublication) {
        System.out.println("--- Simulation de publication ---");

        System.out.print("Type (article, reportage, interview) : ");
        String type = scanner.nextLine();

        System.out.println("Choisissez le média (nom exact) : ");
        for (Media m : medias) {
            System.out.println("- " + m.getNom());
        }
        String nomMedia = scanner.nextLine();
        Media mediaChoisi = medias.stream().filter(m -> m.getNom().equalsIgnoreCase(nomMedia)).findFirst().orElse(null);
        if (mediaChoisi == null) {
            System.out.println("Média introuvable.");
            return;
        }

        List<Entite> mentions = new ArrayList<>();

        while (true) {
            System.out.print("Ajouter une entité mentionnée (nom exact, vide pour terminer) : ");
            String nom = scanner.nextLine();
            if (nom.isEmpty()) break;

            Optional<? extends Entite> entiteOpt = personnes.stream().filter(p -> p.getNom().equalsIgnoreCase(nom)).findFirst();
            if (entiteOpt.isEmpty()) {
                entiteOpt = organisations.stream().filter(o -> o.getNom().equalsIgnoreCase(nom)).findFirst();
            }
            if (entiteOpt.isEmpty()) {
                System.out.println("Entité non trouvée.");
                continue;
            }

            mentions.add(entiteOpt.get());
        }

        Publication e = new Publication(type, mediaChoisi, mentions);
        ensemblePublication.add(e);
        System.out.println("Événement enregistré :\n" + e);
        for (ModuleSpecialise module : modules) {
            module.traiterEvenement(e, relations);  // Publication à traiter
        }
    }
}
