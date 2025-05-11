package Complx_number;

public class TestComplx {
    public static void main(String[] args) {
        Complexe test1 = new Complexe(2, 3);
        System.out.println("test1 = "+test1);
        Complexe test2 = new Complexe(0, 3);
        System.out.println("test2 = "+test2);
        Complexe test3 = new Complexe(2, 0);
        System.out.println("test3 = "+test3);
        Complexe test4 = new Complexe(0, 0);
        System.out.println("test4 = "+test4);
        Complexe test5 = new Complexe(2, 3);
        System.out.println("test5 = "+test5);
        System.out.println("Est-ce que test1=test2 : "+test1.equals(test2));
        System.out.println("Est-ce que test1=test5 : "+test1.equals(test5));
        System.out.println("Est-ce que test1=test3 : "+test1.equals(test3));
        System.out.println("Est-ce que test1=test4 : "+test1.equals(test4));
        System.out.println("test1 + test2 : "+test1.addition(test2));
        System.out.println("test1 * test5 : "+test1.multiplication(test5));
        System.out.println("test1+test4 : "+test1.addition(test4));
        System.out.println("test1*test4 : "+test1.multiplication(test4));
    }
}

