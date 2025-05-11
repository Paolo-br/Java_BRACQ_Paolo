package Model;

public class OrganisationMedia extends Proprietaire<GroupInd, Media> {

    //------------------------Déclaration du constructeur-----------------------------

    public OrganisationMedia(int ID,GroupInd organisation, String qualificatif, String valeur, Media media,String commentaire) {
        super(ID,organisation, qualificatif, valeur, media,commentaire);
    }
}