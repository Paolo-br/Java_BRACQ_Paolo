package Users;

public class TableauUser {

    public static int[] charToInt(String[] charTableau) {
        if (charTableau == null || charTableau.length == 0) {
            System.out.println("Aucun tableau n'a été transmis.");
            return new int[0];
        }

        int[]intTableau = new int[charTableau.length];
        for (int i=0; i<charTableau.length; i++){
            intTableau[i] =Integer.parseInt(charTableau[i]);
        }
        return intTableau;
    }

    public static void affichageTableau(int[] intTableau){
        System.out.print("Voici les éléments du tableau: \n ");
        if (intTableau == null || intTableau.length == 0){
            System.out.print("Le tableau est vide ");
        }else {
            for (int i = 0; i < intTableau.length; i++) {
                System.out.print(intTableau[i] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static int valeurMax(int[] intTableau){
        if (intTableau == null || intTableau.length == 0) {
            System.out.println("Aucun tableau n'a été transmis.");
            return -1;
        }else{
            int max=intTableau[0];
            for (int i = 1; i < intTableau.length; i++) {
                if (intTableau[i]>max){
                    max=intTableau[i];
                }
            }
            return max;
        }

    }

    //On peut créer une telle méthode mais cela implique de mettre les deux tableaux en paramètre.
    // Car nous n'avons pas implémenté de tableau dans la classe Tableau User comme nous avions pu le faire avec les autamponneuses.

    public static void echangerMinMax(int[] tableau1, int[] tableau2) {
        if (tableau1 == null || tableau2 == null || tableau1.length == 0 || tableau2.length == 0) {
            System.out.println("Erreur : un des tableaux est vide.");
            return; // il sert de break
        }

        // Trouver la valeur minimale du premier tableau et son index
        int minIndex1 = 0;
        int minValue = tableau1[0];
        for (int i = 1; i < tableau1.length; i++) {
            if (tableau1[i] < minValue) {
                minValue = tableau1[i];
                minIndex1 = i;
            }
        }

        // Trouver la valeur maximale du second tableau et son index
        int maxIndex2 = 0;
        int maxValue = tableau2[0];
        for (int i = 1; i < tableau2.length; i++) {
            if (tableau2[i] > maxValue) {
                maxValue = tableau2[i];
                maxIndex2 = i;
            }
        }

        // Échanger les valeurs
        tableau1[minIndex1] = maxValue;
        tableau2[maxIndex2] = minValue;
    }

    public static int SommeEntiers(int... entiers){
        if (entiers.length == 0){
            System.out.println("Aucun entier n'a été transmis.");
            return 0;  // Si aucun entier n'est transmis, on retourne 0
        }
        int result =0;
        for ( int num : entiers){
            result += num;
        }
        return result;
    }


    public static void main(String[] args){
        int[] tableauConvertie = charToInt(args);
        System.out.println("Tableau converti :");
        for (int num : tableauConvertie) {
            System.out.print(num + " ");
        }
    }
}

