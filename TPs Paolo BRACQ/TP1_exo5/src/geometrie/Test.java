package geometrie;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Point centre = new Point(10, 4);
        Color couleur = new Color(82, 255, 0);
        Cercle test1 = new Cercle(centre, couleur, 20.5);
        Rectangle test2 = new Rectangle(centre, couleur, 20.5, 4);
        Carre test3 = new Carre(centre, couleur, 20.5);
        Rectangle test4 = new Rectangle(centre, couleur, 20.5, 5);

        CollectionFormesUniques<Rectangle> collection = new CollectionFormesUniques();


        collection.add(test1);
        collection.add(test2);
        collection.add(test3);
        collection.add(test4);

        System.out.println(collection);


        collection.Translation(2, 3);


        System.out.println("Après translation : \n");
        System.out.println(collection);
    }
}