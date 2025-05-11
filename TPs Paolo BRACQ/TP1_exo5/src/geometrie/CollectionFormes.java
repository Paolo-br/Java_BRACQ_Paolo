package geometrie;

import java.util.ArrayList;

public class CollectionFormes extends ArrayList<Forme> {

    public void Translation(double x, double y){
        for (Forme forme:this) {
            forme.Translation(x,y);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection de formes :\n\n");
        int i=1;
        for (Forme forme : this) {
            sb.append("Forme n°"+i+": ").append(forme.toString()).append("\n\n");
            i+=1;
        }
        return sb.toString();
    }


}
