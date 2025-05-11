package Model;

import java.util.*;

public class Personne extends Entite {

    //------------------------Déclaration des variables-----------------------------

    private int Rang2024;
    private int Forbes2024;
    private int Rang2023;
    private int Forbes2023;
    private int Rang2022;
    private int Forbes2022;
    private int Rang2021;
    private int Forbes2021;

    //------------------------Déclaration du constructeur-----------------------------

    public Personne(String Nom, int Rang2024, int Forbes2024, int Rang2023, int Forbes2023, int Rang2022, int Forbes2022, int Rang2021, int Forbes2021) {
        super(Nom);
        this.Rang2024 = Rang2024;
        this.Forbes2024 = Rang2024; // Probable erreur ici : Forbes2024 devrait être utilisé
        this.Rang2023 = Rang2023;
        this.Forbes2023 = Forbes2023;
        this.Rang2022 = Rang2022;
        this.Forbes2022 = Forbes2022;
        this.Rang2021 = Rang2021;
        this.Forbes2021 = Forbes2021;
    }

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode toString redéfinie pour afficher les détails d'une personne
     * @return chaîne contenant tous les rangs et scores Forbes
     */
    @Override
    public String toString() {
        return getNom() + " [Rang 2024: " + Rang2024 + ", Forbes 2024: " + Forbes2024 +
                " | Rang 2023: " + Rang2023 + ", Forbes 2023: " + Forbes2023 +
                " | Rang 2022: " + Rang2022 + ", Forbes 2022: " + Forbes2022 +
                " | Rang 2021: " + Rang2021 + ", Forbes 2021: " + Forbes2021 + "]";
    }

    /**
     * Méthode statique pour afficher une liste de personnes
     * @param personnes liste d'objets Personne
     */
    public static void afficherPersonnes(List<Personne> personnes) {
        System.out.println("\n--- Liste des Personnes ---");
        for (Personne m : personnes) {
            System.out.println(m);
        }
    }

    /**
     * Méthode statique pour trier les personnes par ordre alphabétique
     * @param personnes liste d'objets Personne
     */
    public static void trierPersonnesAlphabetiquement(List<Personne> personnes) {
        personnes.sort(Comparator.comparing(Entite::getNom));
        afficherPersonnes(personnes);
    }

    /**
     * Méthode statique pour trier les personnes par nombre d'organisations possédées
     * @param personnes liste de personnes
     * @param personneOrganisation liste de relations personne-organisation
     */
    public static void trierParNombreOrganisations(List<Personne> personnes, List<Proprietaire<Personne, GroupInd>> personneOrganisation) {
        Map<String, Integer> compteur = new HashMap<>();

        for (Proprietaire<Personne, GroupInd> relation : personneOrganisation) {
            String proprietaire = relation.getSource().getNom();
            compteur.put(proprietaire, compteur.getOrDefault(proprietaire, 0) + 1);
        }

        for (Personne p : personnes) {
            int nb = compteur.getOrDefault(p.getNom(), 0);
            p.setNombreOrganisationsPossedees(nb);
        }

        personnes.sort(Comparator.comparing(Personne::getNombreOrganisationsPossedees).reversed());
        afficherPersonnes(personnes);
    }

    /**
     * Méthode statique pour trier les personnes par nombre de médias possédés
     * @param personnes liste de personnes
     * @param personneMedia liste de relations personne-média
     */
    public static void trierParNombreMedia(List<Personne> personnes, List<Proprietaire<Personne, Media>> personneMedia) {
        Map<String, Integer> compteur = new HashMap<>();

        for (Proprietaire<Personne, Media> relation : personneMedia) {
            String proprietaire = relation.getSource().getNom();
            compteur.put(proprietaire, compteur.getOrDefault(proprietaire, 0) + 1);
        }

        for (Personne p : personnes) {
            int nb = compteur.getOrDefault(p.getNom(), 0);
            p.setNombreMediaPossedees(nb);
        }

        personnes.sort(Comparator.comparing(Personne::getNombreMediaPossedees).reversed());
        afficherPersonnes(personnes);
    }

}
