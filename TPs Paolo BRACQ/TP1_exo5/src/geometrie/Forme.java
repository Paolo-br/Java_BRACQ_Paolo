package geometrie;

import java.awt.*;

abstract class Forme {
    private String Name;
    private Point gravityCenter;
    private Color color;

    /**
     * Constructeur de la classe abstraite forme
     * @param center type:Point
     * @param color type:Color
     */
    public Forme(Point center, Color color){
        Name=getClass().getSimpleName();
        this.color=color;
        this.gravityCenter=center;
    }

    /**
     * Méthode de translation en utilisant setLocation
     * Pour moi une translation vers x,y correspond à mettre l'objet en x et y
     * @param x type:double
     * @param y type:double
     */
    public void Translation(double x, double y){
        gravityCenter.setLocation(x,y);
    }

    /**
     * Méthode d'accès à la couleur
     * @return type Color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Méthode d'accès au centre de gravité
     * @return type:Point
     */
    public Point getGravityCenter(){
        return gravityCenter;
    }

    /**
     * Redéfinition de la fonction toString
     * @return chaine de caractères
     */

    @Override
    public String toString(){
        return Name +"\n "
                +" centre de gravité : x = "+gravityCenter.getX()+" ; y = "+gravityCenter.getY()+"\n "
                + " couleur : r = "+color.getRed()+" ; g = "+color.getGreen()+" ; b = "+color.getBlue();
    }



}
