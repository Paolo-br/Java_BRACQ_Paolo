package Portes;

public class Test_porte {
    public static void main(String[] args){
        // Test de la porte NAND
        System.out.println("Table de vérité de la porte NOR");
        nor norGate = new nor(true, false);
        System.out.println("A: " + norGate.getA() + ", B: " + norGate.getB() + ", Q: " + norGate.getQ());

        norGate.setA(false);
        System.out.println("A: " + norGate.getA() + ", B: " + norGate.getB() + ", Q: " + norGate.getQ());

        norGate.setB(true);
        System.out.println("A: " + norGate.getA() + ", B: " + norGate.getB() + ", Q: " + norGate.getQ());

        norGate.setA(true);
        norGate.setB(true);
        System.out.println("A: " + norGate.getA() + ", B: " + norGate.getB() + ", Q: " + norGate.getQ());

        nand nandGate = new nand(true, false);

        System.out.println("Table de vérité de la porte NANd");
        System.out.println( "A: " + nandGate.getA() + ", B: " + nandGate.getB() + ", Q: " + nandGate.getQ());

        nandGate.setA(false);
        System.out.println("A: " + nandGate.getA() + ", B: " + nandGate.getB() + ", Q: " + nandGate.getQ());

        nandGate.setB(true);
        System.out.println("A: " + nandGate.getA() + ", B: " + nandGate.getB() + ", Q: " + nandGate.getQ());

        nandGate.setA(true);
        nandGate.setB(true);
        System.out.println("A: " + nandGate.getA() + ", B: " + nandGate.getB() + ", Q: " + nandGate.getQ());
    }


}


