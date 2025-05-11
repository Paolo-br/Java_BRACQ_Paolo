package geometrie;

import java.awt.*;

public class Rectangle extends Forme{
    private double largeur;
    private double longueur;

    /**
     * Constructeur d'un rectangle
     * @param center type:Point
     * @param color type: Color
     * @param largeur type: double
     * @param longueur type: double
     */
    public Rectangle (Point center, Color color, double largeur, double longueur){
        super(center, color);
        this.largeur=largeur;
        this.longueur=longueur;
    }

    @Override
    public String toString(){
        return super.toString()+ " \n  largeur : "+ largeur+" \n  longueur : "+longueur;
    }
}
