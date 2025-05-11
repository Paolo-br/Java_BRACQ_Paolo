package Portes;

public class nand extends PorteLogique{
    /**
     * constructeur de porte nand
     * @param A boolean
     * @param B boolean
     */
    public nand (boolean A,boolean B, int maxCycles){
        super(A,B, maxCycles);
        updateQ();
    }
    /**
     * Redéfintion de la méthode de mise à jour de la valeur Q
     */
    @Override
    protected void updateQ(){
        Q=! (A && B);
    }
}