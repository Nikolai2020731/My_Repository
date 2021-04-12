package ru.stqa.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args) {

        Point firstpoint = new Point(1,20);
        Point secondpoint = new Point(1,40);
        System.out.println("-----------------------");
        System.out.println("Расстояние между точками " + firstpoint.distance(secondpoint));
        System.out.println("-----------------------");


    }





}