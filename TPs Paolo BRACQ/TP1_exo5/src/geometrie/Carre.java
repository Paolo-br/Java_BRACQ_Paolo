package geometrie;

import java.awt.*;

public final class Carre extends Rectangle{
    private double size;

    /**
     * Constructeur d'un carré
     * @param center type:Point
     * @param color type: Color
     * @param size type: double
     */
    public Carre (Point center, Color color, double size){
        super(center,color,size,size);
        this.size=size;
    }

    @Override
    public String toString(){
        return super.toString()+ " \n  côté : "+ size;
    }
}
