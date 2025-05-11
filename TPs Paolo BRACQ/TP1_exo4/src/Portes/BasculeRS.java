package Portes;

public class BasculeRS {
    private boolean S;
    private boolean R;
    private boolean Q;
    private boolean nonQ;
    private nor nor1;
    private nor nor2;

    public BasculeRS(boolean S, boolean R, int maxCycles)throws ExceptionPorteLogique, ExceptionBasculeAReparer{
        this.S=S;
        this.R=R;
        this.Q = false;
        this.nonQ = true;
        this.nor1 = new nor(S,true, maxCycles);
        this.nor2 = new nor(R, false, maxCycles);
        majEtat();
    }
    public boolean getS() throws ExceptionPorteLogique{
        return S;
    }
    public boolean getR()throws ExceptionPorteLogique{
        return R;
    }
    public boolean getQ()throws ExceptionPorteLogique{
        return Q;
    }
    public boolean getNonQ()throws ExceptionPorteLogique{
        return nonQ;
    }
    public void setR(boolean r)  throws ExceptionPorteLogique, ExceptionBasculeAReparer{
        R=r;
        majEtat();
    }
    public void setS(boolean s)  throws ExceptionPorteLogique, ExceptionBasculeAReparer {
        S=s;
        majEtat();
    }
    private void majEtat() throws ExceptionPorteLogique, ExceptionBasculeAReparer {
        try {
            nor1.setA(R);
            nor1.setB(nonQ);
            Q = nor1.getQ();

            nor2.setA(S);
            nor2.setB(Q);
            nonQ = nor2.getQ();
        } catch (ExceptionPorteAChanger e) {
            // Catch and wrap specific exception
            throw new ExceptionBasculeAReparer("La bascule RS doit être réparée.", e.getMessage());
        }
    }

    public void etatBascule(){
        System.out.println("S: "+S+ " R:" + R+ " Q:"+Q+" NonQ: "+ nonQ);
    }

}
