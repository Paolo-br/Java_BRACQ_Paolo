package TestTableauUser;

import Users.TableauUser; // Importer la classe TableauUser du package Users

public class TestTableauUser {

    public static void main(String[] args) {
        // Test des méthodes de TableauUser

        // Exemple d'appel à la méthode charToInt()
        String[] argsTableau = {"1", "2", "3", "4"};
        int[] tableauConverti = TableauUser.charToInt(argsTableau);

        // Affichage du tableau
        TableauUser.affichageTableau(tableauConverti);

        // Test de la méthode valeurMax()
        int max = TableauUser.valeurMax(tableauConverti);
        System.out.println("Valeur maximale : " + max);

        // Test de la méthode echangerMinMax() avec deux tableaux
        int[] tableau1 = {5, 3, 8};
        int[] tableau2 = {7, 1, 9};
        TableauUser.echangerMinMax(tableau1, tableau2);

        // Affichage des tableaux après échange
        TableauUser.affichageTableau(tableau1);
        TableauUser.affichageTableau(tableau2);

        // Test de la méthode SommeEntiers()
        int somme = TableauUser.SommeEntiers(1, 2, 3, 4);
        System.out.println("Somme des entiers : " + somme);
    }
}