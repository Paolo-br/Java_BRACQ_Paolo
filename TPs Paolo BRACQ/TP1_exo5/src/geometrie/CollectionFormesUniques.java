package geometrie;

import java.util.ArrayList;
public class CollectionFormesUniques <T extends Forme> extends ArrayList<T> {

    public void Translation(int x, int y) {
        for (T forme : this) {
            forme.Translation(x, y);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection de formes :\n");
        int i=1;
        for (T forme : this) {
            sb.append("Forme n°"+i+": ").append(forme.toString()).append("\n\n");
        }
        return sb.toString();
    }
}