package geometrie;

import java.awt.*;

public class Cercle extends Forme{
    private double rayon;

    /**
     * Constructeur d'un cercle
     * @param center type:Point
     * @param color type: Color
     * @param rayon type: double
     */
    public Cercle (Point center, Color color,double rayon){
        super(center,color);
        this.rayon=rayon;
    }

    @Override
    public String toString(){
        return super.toString()+ " \n  rayon : "+ rayon;
    }

}
