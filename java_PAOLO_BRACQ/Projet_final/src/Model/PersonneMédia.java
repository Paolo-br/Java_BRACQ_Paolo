package Model;

public class PersonneMédia extends Proprietaire<Personne, Media> {

    //------------------------Déclaration du constructeur-----------------------------

    public PersonneMédia(int ID,Personne personne, String qualificatif, String valeur, Media media,String commentaire) {
        super(ID,personne, qualificatif, valeur, media,commentaire);
    }

}