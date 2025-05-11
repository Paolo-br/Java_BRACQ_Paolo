package Model;

import java.util.*;

public class GroupInd extends Entite{

    //------------------------Déclaration des variables-----------------------------

    private String Commentaire;


    //------------------------Déclaration du constructeur-----------------------------

    public GroupInd (String Nom, String Commentaire){
        super(Nom);
        this.Commentaire=Commentaire;
    }

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode toString redéfinie pour afficher les informations du groupe
     * @return chaîne contenant le nom et le commentaire
     */
    @Override
    public String toString() {
        return getNom() + " — Commentaire : \"" + Commentaire + "\"";
    }

    /**
     * Méthode statique pour afficher une liste de groupes
     * @param organisations liste d'objets GroupInd
     */
    public static void afficherOrganisations(List<GroupInd> organisations) {
        System.out.println("\n--- Liste des Organisations ---");
        for (GroupInd o : organisations) {
            System.out.println(o);
        }
    }

    /**
     * Méthode statique pour trier les groupes par ordre alphabétique
     * @param groupes liste d'objets GroupInd
     */
    public static void trierGroupeAlphabetiquement(List<GroupInd> groupes) {
        groupes.sort(Comparator.comparing(Entite::getNom));
        afficherOrganisations(groupes);
    }

    /**
     * Méthode statique pour trier les groupes selon le nombre d'organisations possédées
     * @param organisations liste de groupes
     * @param groupeOrganisation liste de relations entre groupes
     */
    public static void trierParNombreOrganisations(List<GroupInd> organisations, List<Proprietaire<GroupInd, GroupInd>> groupeOrganisation) {
        Map<String, Integer> compteur = new HashMap<>();

        for (Proprietaire<GroupInd, GroupInd> relation : groupeOrganisation) {
            String proprietaire = relation.getSource().getNom();
            compteur.put(proprietaire, compteur.getOrDefault(proprietaire, 0) + 1);
        }

        for (GroupInd g : organisations) {
            int nb = compteur.getOrDefault(g.getNom(), 0);
            g.setNombreOrganisationsPossedees(nb);
        }

        organisations.sort(Comparator.comparing(GroupInd::getNombreOrganisationsPossedees).reversed());
        afficherOrganisations(organisations);
    }

    /**
     * Méthode statique pour trier les groupes selon le nombre de médias possédés
     * @param organisations liste de groupes
     * @param groupeMedia liste de relations entre groupes et médias
     */
    public static void trierParNombreMedia(List<GroupInd> organisations, List<Proprietaire<GroupInd, Media>> groupeMedia) {
        Map<String, Integer> compteur = new HashMap<>();

        for (Proprietaire<GroupInd, Media> relation : groupeMedia) {
            String proprietaire = relation.getSource().getNom();
            compteur.put(proprietaire, compteur.getOrDefault(proprietaire, 0) + 1);
        }

        for (GroupInd g : organisations) {
            int nb = compteur.getOrDefault(g.getNom(), 0);
            g.setNombreMediaPossedees(nb);
        }

        organisations.sort(Comparator.comparing(GroupInd::getNombreMediaPossedees).reversed());
        afficherOrganisations(organisations);
    }

}
