package Model;

public class OrganisationOrganisation extends Proprietaire<GroupInd, GroupInd> {

    //------------------------Déclaration du constructeur-----------------------------

    public OrganisationOrganisation(int ID,GroupInd origine, String qualificatif, String valeur, GroupInd cible,String commentaire) {
        super(ID,origine, qualificatif, valeur, cible,commentaire);
    }
}