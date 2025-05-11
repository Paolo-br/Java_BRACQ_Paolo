package com.feteforraine;
/*classe représentant une autotamponneuse*/
public class Autotamponneuse {
    private boolean onpist;
    private boolean occuped;
    private boolean turnon;
    private boolean light;
    private float x;
    private float y;
    private String user;
    private int Id;
    private static int compteur=0;



    /* Déclaration dun champ constant*/
    public static final double DISTANCE_MINIMALE = 1.00;

    /*Constructeur avec 0 paramètre*/
    public Autotamponneuse(){
        Id=++compteur;
        onpist=false;
        occuped=false;
        turnon=false;
        light=false;
    }

    /*Constructeur avec 2 paramètres, les positions x et y*/
    public Autotamponneuse(float position_x, float position_y){
        this();
        onpist=true;
        x=position_x;
        y=position_y;
    }
    /*Méthode consultation occupation*/
    public boolean estOccupee(){
        return occuped;
    }

    /*Méthode consultation l'id*/
    public int getID(){
        return Id;
    }

    /*Méthode consultation utilisateur*/
    public String getNomOccupant(){
        if (occuped) {
            return user;
        }
        return "Nobody";
    }
    /*Méthode consultation allumage*/
    public boolean estAllumee(){
        return turnon;
    }
    /*Méthode consultation clignotement*/
    public boolean estClignotante(){
        return light;
    }

    @Override
    public String toString(){
        return "["+Id+"] ("+x+"," + y+")"+
        (occuped ?"occupée (" + user +") ":" libre")+
        (turnon?" allumée ":" éteinte ") +
        (light?" clignotante":" non clignotante");
    }

     boolean place(float positionx, float positiony){
        if (onpist){
            x=positionx;
            y=positiony;
        }else{
            x=-1;
            y=-1;
        }
        return true;
    }

    boolean ajouteOccupant(String nom){
        if (onpist & !occuped ){
            user=nom;
            occuped=true;
            return true;
        }else{
            return false;
        }
    }

    boolean enleveOccupant(){
        if (onpist & occuped ){
            user="Nobody";
            occuped=false;
            return true;
        }else {
            return false;
        }
    }

    boolean allume (){
        if (occuped & !turnon ){
            turnon=true;
            return true;
        }else{
            return false;
        }
    }

    boolean eteint(){
        if(turnon){
            turnon=false;
            return true;
        }else{
            return false;
        }
    }

    boolean demarreClignotement(){
        if (turnon & !light){
            light=true;
            return true;
        }else{
            return false;
        }
    }

    boolean arreteClignotement(){
        if (light){
            light=false;
            return true;
        }else{
            return false;
        }
    }

    private double calculeDistance(Autotamponneuse autreAuto){
        double X=x-autreAuto.x;
        double Y= y-autreAuto.y;
        return Math.sqrt(Math.pow(X,2)+Math.pow(Y,2));
    }

    /* Pour l'instant on laisse la méthode collision en privare package*/
    boolean collision(Autotamponneuse autreAuto){
        return calculeDistance(autreAuto)<=DISTANCE_MINIMALE;
    }

    static double calculeDistance(Autotamponneuse auto1, Autotamponneuse auto2) {
        if (auto1 != null) {
            return auto1.calculeDistance(auto2);
        }else{
            return -1; /*J'ai choisi -1 car java demande un deuxième élément*/
        }
    }

    static boolean collision(Autotamponneuse auto1, Autotamponneuse auto2) {
        if (auto1 != null) {
            return auto1.collision(auto2);
        }else{
            return false;/*J'ai choisi false car java demande un deuxième élément*/
        }
    }

    @Override
    public boolean equals(Object autreObjet){
        Autotamponneuse auto =  (Autotamponneuse) autreObjet;
        if (onpist != auto.onpist){
            return false;
        }
        if (turnon != auto.turnon){
            return false;
        }
        if (occuped != auto.occuped){
            return false;
        }
        if (light != auto.light){
            return false;
        }
        if (x != auto.x){
            return false;
        }
        if (y != auto.y){
            return false;
        }
        if (user != auto.user){
            return false;
        }
        return true;
    }

    boolean miseEnPiste(){
        onpist=true;
        return true;
    }

    float getX(){
        return x;
    }

    float getY(){
        return y;
    }
    public static void main(String[] args){
    }
}
