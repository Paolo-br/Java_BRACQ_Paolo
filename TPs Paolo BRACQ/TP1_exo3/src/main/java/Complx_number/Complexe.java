package Complx_number;

public class Complexe {
    private final double real;
    private final double imaginary;


    /**
     * Constructeur d'une instance de la classe Complexe
     * @param real
     * @param imaginary
     */
    public Complexe(double real,double imaginary){
        this.real=real;
        this.imaginary=imaginary;
    }

    /**
     * Affichage d'un nombre complexe
     * @return String qui représente la valeur complexe
     */
    @Override
    public String toString(){
        if(imaginary == 0 && real == 0){
            return "0";
        }
        if(real == 0){
            return imaginary+" * i ";
        }
        if(imaginary == 0){
            return ""+real;
        }

        return real+" + "+imaginary+" * i ";
    }

    /**
     * Méthode d'accès pour la partie imaginaire
     * @return double
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Méthode d'accès pour la partie réelle
     * @return double
     */
    public double getReal() {
        return real;
    }

    /**
     *Constructeur de copie
     * @param number il s'agit d'une instance de la classe Complexe
     */
    Complexe(Complexe number){
        real=number.real;
        imaginary=number.imaginary;
    }

    /**
     * Redéfinition de la fonction equals pour deux objets de la classe Complexe
     * @param autreObjet instance de la classe Complexe
     * @return booleen
     */
    @Override
    public boolean equals(Object autreObjet){
        Complexe number=(Complexe) autreObjet;
        if (number.real != real){
            return false;
        }
        if (number.imaginary != imaginary){
            return false;
        }
        return true;
    }

    /**
     * Méthode d’instance pour le calcul du module
     * @return double qui représente le module
     */
    public double module(){
        return Math.sqrt(Math.pow(real,2)+Math.pow(imaginary,2));
    }

    /**
     * Méthode d’instance pour le calcul de l'argument
     * @return double qui représente l'argument
     */
    public double argument(){
        return Math.acos(real/imaginary);
    }

    /**
     * Méthodes pour l’addition de deux nombres complexes
     * @param autreNombre instance de la classe Complexe
     * @return une nouvelle instance correspondant au résultat de l’addition
     */
    public Complexe addition(Complexe autreNombre){
        return new Complexe(real+autreNombre.real,imaginary+ autreNombre.imaginary);
    }

    /**
     * Méthodes pour la multipication de deux nombres complexes
     * @param autreNombre instance de la classe Complexe
     * @return une nouvelle instance correspondant au résultat de la multiplication
     */
    public Complexe multiplication(Complexe autreNombre){
        return new Complexe((real*autreNombre.real)-(imaginary*autreNombre.imaginary),(real*autreNombre.imaginary)+(imaginary* autreNombre.real));
    }

}
