package Mémoire;

import Complx_number.Complexe;

public class Test_Memoire {
    public static void main(String[] args) {
        ComplexeMemoire c1 = new ComplexeMemoire(1, 2);
        ComplexeMemoire c2 = new ComplexeMemoire(1, 2);

        System.out.println(c1.equals(c2)); // false

        Complexe c3 = new Complexe(1, 2);
        ComplexeMemoire c4 = new ComplexeMemoire(1, 2);

        System.out.println(c3.equals(c4)); // false

    }
}
