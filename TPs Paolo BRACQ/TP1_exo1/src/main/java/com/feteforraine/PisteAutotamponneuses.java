package com.feteforraine;

public class PisteAutotamponneuses {
    private Autotamponneuse[] Voitures;
    private int size;

    public PisteAutotamponneuses(int taille) {
        // On initialise le tableau avec la taille spécifiée
        this.Voitures = new Autotamponneuse[taille];
        size=taille;
        for (int i = 0; i < taille; i++) {
            Voitures[i]=new com.feteforraine.Autotamponneuse();
            Voitures[i].miseEnPiste();
        }
    }

    @Override
    public String toString(){
        String result="";
        for (int i = 0; i < size; i++) {
            result+="Position liste : "+i+" Autotamponneuse : "+Voitures[i].getID()+" Place : ("+Voitures[i].getX()+"," + Voitures[i].getY()+") \n";
        }
        return result;
    }

    public static void main(String[] args){
        int nbr= 20;
        com.feteforraine.PisteAutotamponneuses Piste1= new PisteAutotamponneuses(nbr);
        for (int i = 0; i < nbr; i++) {
            float x= (float)Math.random()*10;
            float y= (float)Math.random()*10;
            boolean estPlace= Piste1.Voitures[i].place(x,y);
            for (int j = 0; j < i; j++) {
                while (com.feteforraine.Autotamponneuse.collision(Piste1.Voitures[i],Piste1.Voitures[j])){
                    x= (float)Math.random()*10;
                    y= (float)Math.random()*10;
                    estPlace= Piste1.Voitures[i].place(x,y);
                }
            }
        }
        System.out.println("Voici les infos sur Piste1 :");
        System.out.println(Piste1.toString());

    }
}
