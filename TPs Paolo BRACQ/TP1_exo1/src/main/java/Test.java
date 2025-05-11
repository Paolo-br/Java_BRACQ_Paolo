package com.feteforraine;

import com.feteforraine.Autotamponneuse;

public class Test {
    public static void main(String[] args){
        /*instantiation d'un objet de la classe Autotamponneuse*/

        Autotamponneuse Voiture1 =new Autotamponneuse(5,6);
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.place(9,0);
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.ajouteOccupant("Moi");
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.allume();
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.demarreClignotement();
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.arreteClignotement();
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.eteint();
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
        Voiture1.enleveOccupant();
        System.out.println("Voici les infos sur Voiture1 :");
        System.out.println(Voiture1.toString());
    }
}
