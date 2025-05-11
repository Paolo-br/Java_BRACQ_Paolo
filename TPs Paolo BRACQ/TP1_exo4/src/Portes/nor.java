package Portes;

public class nor extends PorteLogique{
    /**
     * Constructeur d'une porte nor
     * @param A boolean
     * @param B boolean
     */
    public nor (boolean A,boolean B, int maxCycles){
        super(A,B, maxCycles);
        updateQ();
    }

    /**
     * Redéfintion de la méthode de mise à jour de la valeur Q
     */
    @Override
    protected void updateQ(){
        Q=! (A||B);
    }
}