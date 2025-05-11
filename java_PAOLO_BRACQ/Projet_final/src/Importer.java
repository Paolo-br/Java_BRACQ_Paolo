import java.util.*;
import java.io.*;
import Model.*;

public class Importer {

    //------------------------Déclaration des méthodes-----------------------------

    /**
     * Méthode privée pour chercher une entité par son nom dans les listes de médias, personnes et groupes.
     * @param nom le nom de l'entité à chercher
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param groupes la liste des groupes
     * @return l'entité trouvée ou null si aucune entité n'est trouvée
     */
    private static Entite chercherEntite(String nom, List<Media> medias, List<Personne> personnes, List<GroupInd> groupes) {
        for (Media m : medias) {
            if (m.getNom().equalsIgnoreCase(nom)) return m;
        }
        for (Personne p : personnes) {
            if (p.getNom().equalsIgnoreCase(nom)) return p;
        }
        for (GroupInd g : groupes) {
            if (g.getNom().equalsIgnoreCase(nom)) return g;
        }
        return null;
    }

    /**
     * Méthode pour importer des médias à partir d'un fichier.
     * @param cheminFichier le chemin du fichier à importer
     * @return la liste des médias importés
     */
    public static List<Media> importerMedias(String cheminFichier) {
        List<Media> medias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;

            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                String[] champs = ligne.split("\t", -1); // -1 pour garder les champs vides
                if (champs.length < 5) continue;

                String nom = champs[0];
                String type = champs[1];
                String periodicite = champs[2];
                String echelle = champs[3];
                String prix = champs[4];
                boolean disparu = champs.length >= 6 && champs[5] != null && champs[5].trim().equalsIgnoreCase("checked");

                Media media = new Media(nom, type, periodicite, echelle, prix, disparu);
                medias.add(media);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return medias;
    }

    /**
     * Méthode pour importer des groupes à partir d'un fichier.
     * @param cheminFichier le chemin du fichier à importer
     * @return la liste des groupes importés
     */
    public static List<GroupInd> importerGroupes(String cheminFichier) {
        List<GroupInd> Groupes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;
            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }
                String[] champs = ligne.split("\t", -1); // -1 pour garder les champs vides

                String nom = champs[0];
                String commentaire = champs[1];

                GroupInd groupe = new GroupInd(nom, commentaire);
                Groupes.add(groupe);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Groupes;
    }

    /**
     * Méthode pour importer des liens de propriété entre GroupInd et Media à partir d'un fichier.
     * @param cheminFichier le chemin du fichier à importer
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param groupes la liste des groupes
     * @return la liste des liens de propriété importés
     */
    public static List<Proprietaire<GroupInd, Media>> importerProprietairesGroupIndMedia(String cheminFichier, List<Media> medias, List<Personne> personnes, List<GroupInd> groupes) {
        List<Proprietaire<GroupInd, Media>> proprietaires = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;

            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] champs = ligne.split("\t", -1);
                if (champs.length < 5 || champs[0].isEmpty()) continue;

                int id = Integer.parseInt(champs[0]);
                String origineNom = champs[1].trim();
                String qualificatif = champs[2].trim();
                String valeur = champs[3].trim();
                String cibleNom = champs[4].trim();
                String commentaire = champs.length > 5 ? champs[5].trim() : "";

                Entite origine = chercherEntite(origineNom, medias, personnes, groupes);
                Entite cible = chercherEntite(cibleNom, medias, personnes, groupes);

                if (origine != null && cible != null) {
                    proprietaires.add(new Proprietaire<>(id, (GroupInd) origine, qualificatif, valeur, (Media) cible, commentaire));
                } else {
                    System.err.println("Entité non trouvée: " + origineNom + " → " + cibleNom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proprietaires;
    }

    /**
     * Méthode générique pour importer les liens de propriété avec GroupInd → GroupInd.
     * @param cheminFichier le chemin du fichier à importer
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param groupes la liste des groupes
     * @return la liste des liens de propriété importés
     */
    public static List<Proprietaire<GroupInd, GroupInd>> importerProprietairesGroupIndGroupInd(String cheminFichier, List<Media> medias, List<Personne> personnes, List<GroupInd> groupes) {
        List<Proprietaire<GroupInd, GroupInd>> proprietaires = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;

            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] champs = ligne.split("\t", -1);
                if (champs.length < 5 || champs[0].isEmpty()) continue;

                int id = Integer.parseInt(champs[0]);
                String origineNom = champs[1].trim();
                String qualificatif = champs[2].trim();
                String valeur = champs[3].trim();
                String cibleNom = champs[4].trim();
                String commentaire = champs.length > 5 ? champs[5].trim() : "";

                Entite origine = chercherEntite(origineNom, medias, personnes, groupes);
                Entite cible = chercherEntite(cibleNom, medias, personnes, groupes);

                if (origine != null && cible != null) {
                    proprietaires.add(new Proprietaire<>(id, (GroupInd) origine, qualificatif, valeur, (GroupInd) cible, commentaire));
                } else {
                    System.err.println("Entité non trouvée: " + origineNom + " → " + cibleNom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proprietaires;
    }

    /**
     * Méthode générique pour importer les liens de propriété avec Personne → Media.
     * @param cheminFichier le chemin du fichier à importer
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param groupes la liste des groupes
     * @return la liste des liens de propriété importés
     */
    public static List<Proprietaire<Personne, Media>> importerProprietairesPersonneMedia(String cheminFichier, List<Media> medias, List<Personne> personnes, List<GroupInd> groupes) {
        List<Proprietaire<Personne, Media>> proprietaires = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;

            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] champs = ligne.split("\t", -1);
                if (champs.length < 5 || champs[0].isEmpty()) continue;

                int id = Integer.parseInt(champs[0]);
                String origineNom = champs[1].trim();
                String qualificatif = champs[2].trim();
                String valeur = champs[3].trim();
                String cibleNom = champs[4].trim();
                String commentaire = champs.length > 5 ? champs[5].trim() : "";

                Entite origine = chercherEntite(origineNom, medias, personnes, groupes);
                Entite cible = chercherEntite(cibleNom, medias, personnes, groupes);

                if (origine != null && cible != null) {
                    proprietaires.add(new Proprietaire<>(id, (Personne) origine, qualificatif, valeur, (Media) cible, commentaire));
                } else {
                    System.err.println("Entité non trouvée: " + origineNom + " → " + cibleNom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proprietaires;
    }

    /**
     * Méthode générique pour importer les liens de propriété avec Personne → GroupInd.
     * @param cheminFichier le chemin du fichier à importer
     * @param medias la liste des médias
     * @param personnes la liste des personnes
     * @param groupes la liste des groupes
     * @return la liste des liens de propriété importés
     */
    public static List<Proprietaire<Personne, GroupInd>> importerProprietairesPersonneGroupInd(String cheminFichier, List<Media> medias, List<Personne> personnes, List<GroupInd> groupes) {
        List<Proprietaire<Personne, GroupInd>> proprietaires = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;

            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] champs = ligne.split("\t", -1);
                if (champs.length < 5 || champs[0].isEmpty()) continue;

                int id = Integer.parseInt(champs[0]);
                String origineNom = champs[1].trim();
                String qualificatif = champs[2].trim();
                String valeur = champs[3].trim();
                String cibleNom = champs[4].trim();
                String commentaire = champs.length > 5 ? champs[5].trim() : "";

                Entite origine = chercherEntite(origineNom, medias, personnes, groupes);
                Entite cible = chercherEntite(cibleNom, medias, personnes, groupes);

                if (origine != null && cible != null) {
                    proprietaires.add(new Proprietaire<>(id, (Personne) origine, qualificatif, valeur, (GroupInd) cible, commentaire));
                } else {
                    System.err.println("Entité non trouvée: " + origineNom + " → " + cibleNom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proprietaires;
    }

    /**
     * Méthode pour importer des personnes à partir d'un fichier.
     * @param cheminFichier le chemin du fichier à importer
     * @return la liste des personnes importées
     */
    public static List<Personne> importerPersonnes(String cheminFichier) {
        List<Personne> Personnes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean isFirstLine = true;
            while ((ligne = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }
                String[] champs = ligne.split("\t", -1); // -1 pour garder les champs vides

                String nom = champs[0];
                int rang2024 = champs[1].isEmpty() ? 0: Integer.parseInt(champs[1]);
                int forbes2024 = champs[2].isEmpty() ? 0 : Integer.parseInt(champs[2]);
                int rang2023 = champs[3].isEmpty() ? 0 : Integer.parseInt(champs[3]);
                int forbes2023 = champs[4].isEmpty() ? 0 : Integer.parseInt(champs[4]);
                int rang2022 = champs[5].isEmpty() ? 0 : Integer.parseInt(champs[5]);
                int forbes2022 = champs[6].isEmpty() ? 0 : Integer.parseInt(champs[6]);
                int rang2021 = champs[7].isEmpty() ? 0 : Integer.parseInt(champs[7]);
                int forbes2021 = champs[8].isEmpty() ? 0 : Integer.parseInt(champs[8]);

                Personne personne = new Personne(nom,rang2024, forbes2024, rang2023, forbes2023, rang2022, forbes2022, rang2021, forbes2021);
                Personnes.add(personne);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Personnes;
    }
}
