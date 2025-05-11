package Model;

public class Entite {

//------------------------Déclaration des variables-----------------------------

    private String Nom;
    private int nombreOrganisationsPossedees = 0;
    private int nombreMediaPossedees = 0;

//------------------------Déclaration du constructeur-----------------------------

    public Entite( String Nom){
        this.Nom=Nom;
    }

//-------------------------Déclaration des méthodes--------------------------------


    /**
     * Méthode d'instance pour accéder au nom
     * @return la chaîne de caractères correspondant au nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Méthode pour modifier le nombre d'organisations possédées
     * @param nb entier représentant le nombre d'organisations
     */
    public void setNombreOrganisationsPossedees(int nb) {
        this.nombreOrganisationsPossedees = nb;
    }

    /**
     * Méthode d'instance pour accéder au nombre d'organisations possédées
     * @return entier
     */
    public int getNombreOrganisationsPossedees() {
        return nombreOrganisationsPossedees;
    }

    /**
     * Méthode pour modifier le nombre de médias possédés
     * @param nb entier représentant le nombre de médias
     */
    public void setNombreMediaPossedees(int nb) {
        this.nombreMediaPossedees = nb;
    }

    /**
     * Méthode d'instance pour accéder au nombre de médias possédés
     * @return entier
     */
    public int getNombreMediaPossedees() {
        return nombreMediaPossedees;
    }
}
