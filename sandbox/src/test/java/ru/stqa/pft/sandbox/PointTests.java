package ru.stqa.pft.sandbox;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testArea1(){
        Point firstpoint = new Point(1,2);
        Point secondpoint = new Point(1,4);
        assert Point.distance(firstpoint, secondpoint) == 2.0;

    }
    @Test
    public void testArea2(){
        Point firstpoint = new Point(1,2);
        Point secondpoint = new Point(1,4);
        assert Point.distance(firstpoint, secondpoint) ==3.0;

    }
    @Test
    public void testArea3(){
        Point firstpoint = new Point(1,20);
        Point secondpoint = new Point(1,40);
        assert Point.distance(firstpoint, secondpoint) ==20.0;

    }
    @Test
    public void testArea4(){
        Point firstpoint = new Point(1,20);
        Point secondpoint = new Point(1,40);
        assert Point.distance(firstpoint, secondpoint) ==22.0;

    }

}
