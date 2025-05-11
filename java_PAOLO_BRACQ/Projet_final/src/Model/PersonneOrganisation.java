package Model;

public class PersonneOrganisation extends Proprietaire<Personne, GroupInd> {

    //------------------------Déclaration du constructeur-----------------------------

    public PersonneOrganisation(int ID,Personne personne, String qualificatif, String valeur, GroupInd organisation,String commentaire) {
        super(ID, personne, qualificatif, valeur, organisation,commentaire);
    }
}
