import Model.*;

import java.io.File;
import java.util.*;
import Modules.*;

public class Console {

    //------------------------Déclaration des méthodes-----------------------------

    /**
     * Méthode pour afficher et trier les médias selon le choix de l'utilisateur.
     * @param medias la liste des médias
     * @param scanner le scanner pour la saisie utilisateur
     */
    private static void afficherEtTrierMedias(List<Media> medias, Scanner scanner) {
        System.out.println("1. Tri alphabétique");
        System.out.println("2. Sans Tri particulier");
        System.out.print("Choix du tri : ");
        int tri = scanner.nextInt();
        scanner.nextLine();

        if (tri == 1) {
            Media.trierMediasAlphabetiquement(medias);
        }
        if (tri == 2) {
            Media.afficherMedias(medias);
        }
    }

    /**
     * Méthode pour afficher et trier les personnes selon le choix de l'utilisateur.
     * @param personnes la liste des personnes
     * @param scanner le scanner pour la saisie utilisateur
     * @param perso_media la liste des relations de propriété entre personnes et médias
     * @param perso_org la liste des relations de propriété entre personnes et organisations
     */
    private static void afficherEtTrierPersonnes(List<Personne> personnes, Scanner scanner, List<Proprietaire<Personne, Media>> perso_media, List<Proprietaire<Personne, GroupInd>> perso_org) {
        System.out.println("1. Tri alphabétique");
        System.out.println("2. Tri par nombre décroissant de médias possédés");
        System.out.println("3. Tri par nombre décroissant d'organisations possédées");
        System.out.print("Choix du tri : ");
        int tri = scanner.nextInt();
        scanner.nextLine();

        if (tri == 1) {
            Personne.trierPersonnesAlphabetiquement(personnes);
        }
        if (tri == 2) {
            Personne.trierParNombreMedia(personnes, perso_media);
        }
        if (tri == 3) {
            Personne.trierParNombreOrganisations(personnes, perso_org);
        }
    }

    /**
     * Méthode pour afficher et trier les organisations selon le choix de l'utilisateur.
     * @param organisations la liste des organisations
     * @param scanner le scanner pour la saisie utilisateur
     * @param org_media la liste des relations de propriété entre organisations et médias
     * @param org_org la liste des relations de propriété entre organisations
     */
    private static void afficherEtTrierOrganisations(List<GroupInd> organisations, Scanner scanner, List<Proprietaire<GroupInd, Media>> org_media, List<Proprietaire<GroupInd, GroupInd>> org_org) {
        System.out.println("1. Tri alphabétique");
        System.out.println("2. Tri par nombre décroissant de médias possédés");
        System.out.println("3. Tri par nombre décroissant d'organisations possédées");
        System.out.print("Choix du tri : ");
        int tri = scanner.nextInt();
        scanner.nextLine();

        if (tri == 1) {
            GroupInd.trierGroupeAlphabetiquement(organisations);
        }
        if (tri == 2) {
            GroupInd.trierParNombreMedia(organisations, org_media);
        }
        if (tri == 3) {
            GroupInd.trierParNombreOrganisations(organisations, org_org);
        }
    }

    /**
     * Méthode pour simuler un événement (rachat ou publication).
     * @param scanner le scanner pour la saisie utilisateur
     * @param toutesRelations la liste de toutes les relations de propriété
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param organisations la liste des organisations
     * @param modules la liste des modules spécialisés
     */
    private static void simulerEvenement(Scanner scanner, List<Proprietaire<? extends Entite, ? extends Entite>> toutesRelations, List<Media> medias, List<Personne> personnes, List<GroupInd> organisations, List<ModuleSpecialise> modules,List<Publication>ensemblePublication) {
        System.out.println("\n=== SIMULATION D'ÉVÉNEMENT ===");
        System.out.println("1. Rachat de parts");
        System.out.println("2. Publication");
        System.out.println("3. Afficher les publications");
        System.out.print("Choix de l'événement : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            SimulationEvenement.Rachat(toutesRelations, scanner, modules,personnes,organisations);
        }
        if (choix == 2) {
            SimulationEvenement.Publication(medias, personnes, organisations, scanner, toutesRelations, modules,ensemblePublication);
        }
        if (choix == 3) {
            System.out.println("\n=== ENSEMBLE DES PUBLICATIONS ===");
            for (Publication p :ensemblePublication){
                System.out.println(p);
            }
        }
    }

    /**
     * Méthode pour afficher les relations d'une entité
     * @param scanner
     * @param toutesRelations liste de propriétaire
     */
    private static void afficherPropriete(Scanner scanner, List<Proprietaire<? extends Entite, ? extends Entite>> toutesRelations) {
        System.out.println("\nDe quelle personne/organisation souhaitez-vous voir les propriétés : ");
        String nom = scanner.nextLine();
        boolean trouve = false;

        for (Proprietaire<? extends Entite, ? extends Entite> relation : toutesRelations) {
            if (relation.getSource().getNom().equalsIgnoreCase(nom)) {
                System.out.println(relation);
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Aucune propriété trouvée pour \"" + nom + "\".");
        }
    }

    /**
     * Méthode pour créer et gérer les modules spécialisés.
     * @param modulesSpecialises la liste des modules spécialisés
     * @param vigie l'instance de Vigie
     * @param scanner le scanner pour la saisie utilisateur
     * @param personnes la liste des personnes
     * @param medias la liste des médias
     */
    private static void afficherModule(List<ModuleSpecialise> modulesSpecialises, Vigie vigie, Scanner scanner, List<Personne> personnes, List<Media> medias) {
        System.out.println("\n=== Gestion des modules spécialisés ===");
        System.out.println("1. Créer un module de suivi de personne");
        System.out.println("2. Créer un module de suivi de média");
        System.out.println("3. Afficher les statistiques des modules");
        System.out.println("4. Afficher l'historique des alertes");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                SuiviPersonne.creationModule(modulesSpecialises, vigie, scanner, personnes);
                break;

            case 2:
                SuiviMedia.creationModule(modulesSpecialises, vigie, scanner, medias);
                break;

            case 3:
                System.out.println("\n=== Statistiques des modules ===");
                for (ModuleSpecialise module : modulesSpecialises) {
                    if (module instanceof SuiviPersonne) {
                        ((SuiviPersonne) module).afficherStats();
                    } else if (module instanceof SuiviMedia) {
                        ((SuiviMedia) module).afficherStats();
                    }
                }
                break;

            case 4:
                System.out.println("\n=== Historique alertes ===");
                System.out.println(vigie.getAlertes());
                break;

            default:
                System.out.println("Choix invalide.");
        }
    }

    /**
     * Méthode principale pour exécuter le programme.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        List<Personne> Personnes = Importer.importerPersonnes("data/personnes.tsv");
        List<Media> Medias = Importer.importerMedias("data/medias.tsv");
        List<GroupInd> Organisations = Importer.importerGroupes("data/organisations.tsv");
        List<Proprietaire<GroupInd, Media>> org_media = Importer.importerProprietairesGroupIndMedia("data/organisation-media.tsv", Medias, Personnes, Organisations);
        List<Proprietaire<GroupInd, GroupInd>> org_org = Importer.importerProprietairesGroupIndGroupInd("data/organisation-organisation.tsv", Medias, Personnes, Organisations);
        List<Proprietaire<Personne, Media>> perso_media = Importer.importerProprietairesPersonneMedia("data/personne-media.tsv", Medias, Personnes, Organisations);
        List<Proprietaire<Personne, GroupInd>> perso_org = Importer.importerProprietairesPersonneGroupInd("data/personne-organisation.tsv", Medias, Personnes, Organisations);

        List<Proprietaire<? extends Entite, ? extends Entite>> toutesRelations = new ArrayList<>();
        toutesRelations.addAll(org_media);
        toutesRelations.addAll(org_org);
        toutesRelations.addAll(perso_media);
        toutesRelations.addAll(perso_org);

        List<Publication> ensemblePublication =new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int choix;

        List<ModuleSpecialise> modulesSpecialises = new ArrayList<>();
        Vigie vigie = new Vigie();

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Afficher tous les médias");
            System.out.println("2. Afficher toutes les personnes");
            System.out.println("3. Afficher toutes les organisations");
            System.out.println("4. Afficher les propriétés d'une entité");
            System.out.println("5. Simuler un événement");
            System.out.println("6. Gestion de module");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consomme la ligne

            switch (choix) {
                case 1:
                    afficherEtTrierMedias(Medias, scanner);
                    break;
                case 2:
                    afficherEtTrierPersonnes(Personnes, scanner, perso_media, perso_org);
                    break;
                case 3:
                    afficherEtTrierOrganisations(Organisations, scanner, org_media, org_org);
                    break;
                case 4:
                    afficherPropriete(scanner, toutesRelations);
                    break;
                case 5:
                    simulerEvenement(scanner, toutesRelations, Medias, Personnes, Organisations, modulesSpecialises,ensemblePublication);
                    break;
                case 6:
                    afficherModule(modulesSpecialises, vigie, scanner, Personnes, Medias);
                    break;
                case 7:
                    System.out.println("Fermeture du programme. À bientôt !");
                    break;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }

        } while (choix != 7);
    }
}
