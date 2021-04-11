package ru.stqa.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {

        Point firstpoint = new Point(2,4);
        Point secondpoint = new Point(3,5);
        System.out.println("-----------------------");
        System.out.println("Расстояние между точками " + Point.distance(firstpoint, secondpoint));
        System.out.println("-----------------------");


    }





}