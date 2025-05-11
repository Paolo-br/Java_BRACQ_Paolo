package Users;

public class Tests {
    public static void main(String[] args) {
        System.out.print("Voici la somme des entiers 11,2,54,9,0,76,99,13 : \n"+ Users.TableauUser.SommeEntiers(11,2,54,9,0,76,99,13)+" \n");
        System.out.print("Voici la somme d'aucun entier' : \n" );
        System.out.print(Users.TableauUser.SommeEntiers());
    }
}
