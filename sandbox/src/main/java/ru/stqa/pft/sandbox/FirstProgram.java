package ru.stqa.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");
        double len =5;
        System.out.println("Площадь квадрата=" + area(len));
    }

    public static void hello(String somebody) {
        System.out.println("Let's do it, " + somebody);
    }

    public static double area(double l){
        return l*l;
    }


}