package Model;

import java.util.List;

public class Proprietaire<T extends Entite, U extends Entite> {

    //------------------------Déclaration des variables-----------------------------

    private final int ID;
    private T source;  // L'origine de la relation
    private String qualificatif;  // Le type de relation (ex : "égal à", "partenaire", etc.)
    private String valeur;  // La valeur de la relation
    private U cible;  // La cible de la relation (Média, Organisation, Personne)
    private String commentaire;

    //------------------------Déclaration du constructeur-----------------------------

    public Proprietaire(int ID, T source, String qualificatif, String valeur, U cible, String commentaire) {
        this.ID = ID;
        this.source = source;
        this.qualificatif = qualificatif;
        this.valeur = valeur;
        this.cible = cible;
        this.commentaire = commentaire;
    }

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode pour accéder à l'origine de la relation
     *
     * @return source
     */
    public T getSource() {
        return source;
    }


    /**
     * Méthode pour accéder à l'ID
     *
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Méthode pour modifier l'origine de la relation
     *
     * @param source objet de type T
     */
    public void setSource(T source) {
        this.source = source;
    }

    /**
     * Méthode pour accéder au qualificatif de la relation
     *
     * @return chaîne de caractères
     */
    public String getQualificatif() {
        return qualificatif;
    }

    /**
     * Méthode pour modifier le qualificatif de la relation
     *
     * @param qualificatif chaîne de caractères
     */
    public void setQualificatif(String qualificatif) {
        this.qualificatif = qualificatif;
    }

    /**
     * Méthode pour accéder à la valeur de la relation
     *
     * @return chaîne de caractères
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Méthode pour modifier la valeur en tant que pourcentage formaté
     *
     * @param part nombre réel représentant un pourcentage
     */
    public void setPart(double part) {
        this.valeur = String.format("%.2f", part);
    }

    /**
     * Méthode pour accéder à la cible de la relation
     *
     * @return cible
     */
    public U getCible() {
        return cible;
    }

    /**
     * Méthode pour modifier la cible de la relation
     *
     * @param cible objet de type U
     */
    public void setCible(U cible) {
        this.cible = cible;
    }

    /**
     * Méthode pour récupérer la part sous forme numérique
     *
     * @return double représentant la valeur
     */
    public double getPart() {
        try {
            return Double.parseDouble(valeur.replace("%", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Méthode toString redéfinie pour afficher les informations de la relation
     *
     * @return chaîne contenant les détails
     */
    @Override
    public String toString() {
        return ID + " | Origine: " + source.getNom() + " | Qualificatif: " + qualificatif +
                " | Valeur: " + valeur + "% | Cible: " + cible.getNom() +
                (commentaire != null && !commentaire.isEmpty() ? " | Commentaire: " + commentaire : "");
    }
}
