package com.feteforraine;

public class TestAutotamponneuse {
    public static void main(String[] args){
        /*instantiation d'un objet de la classe Autotamponneuse*/
        com.feteforraine.Autotamponneuse Voiture1 =new com.feteforraine.Autotamponneuse(1,3);
        Voiture1.ajouteOccupant("Toi");
        Voiture1.allume();
        Voiture1.demarreClignotement();
        com.feteforraine.Autotamponneuse Voiture2 =new com.feteforraine.Autotamponneuse(6,6);
        Voiture2.ajouteOccupant("Moi");
        Voiture2.allume();
        Voiture2.demarreClignotement();
        System.out.println("Voici les infos sur l'égalité entre la Voiture 1 et la Voiture 2 :");
        System.out.println(" - Egales : "+ Voiture1.equals(Voiture2));
    }
}
