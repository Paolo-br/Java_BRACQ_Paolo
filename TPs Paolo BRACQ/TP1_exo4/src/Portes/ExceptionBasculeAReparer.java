package Portes;

public class ExceptionBasculeAReparer extends Exception {
    private String porte;

    public ExceptionBasculeAReparer(String message, String porte) {
        super(message);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }
}
