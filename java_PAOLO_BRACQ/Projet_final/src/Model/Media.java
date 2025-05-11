package Model;

import java.util.Comparator;
import java.util.List;

public class Media extends Entite{

    //------------------------Déclaration des variables-----------------------------

    private String Type;
    private String Periodicite;
    private String Echelle;
    private String Prix;
    private boolean Disparu;

    //------------------------Déclaration du constructeur-----------------------------

    public Media (String Nom, String Type, String Periodicite,String Echelle,String Prix,boolean Disparu){
        super(Nom);
        this.Type=Type;
        this.Periodicite=Periodicite;
        this.Echelle=Echelle;
        this.Prix=Prix;
        this.Disparu=Disparu;
    }

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode toString redéfinie pour afficher les informations du média
     * @return chaîne contenant les détails du média
     */
    @Override
    public String toString() {
        return getNom() + " — Type: " + Type + ", Périodicité: " + Periodicite + ", Échelle: " + Echelle +
                ", Prix: " + Prix + "€, Disparu: " + (Disparu ? "Oui" : "Non");
    }

    /**
     * Méthode statique pour afficher une liste de médias
     * @param medias liste d'objets Media
     */
    public static void afficherMedias(List<Media> medias) {
        System.out.println("\n--- Liste des Médias ---");
        for (Media m : medias) {
            System.out.println(m);
        }
    }

    /**
     * Méthode statique pour trier les médias par ordre alphabétique
     * @param medias liste d'objets Media
     */
    public static void trierMediasAlphabetiquement(List<Media> medias) {
        medias.sort(Comparator.comparing(Entite::getNom));
        afficherMedias(medias);
    }

}
