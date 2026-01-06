package org.example;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class TriangleTest {

    @Test
   public void shouldCalculateTriangleAreaCorrectly(){
        Triangle triangle = new Triangle();
        double result = triangle.squareTriangle (5, 10);
        assertEquals(25, result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
   public void shouldThrowWhenBaseIsZero() {
        Triangle triangle = new Triangle();
        triangle.squareTriangle(0,5);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
   public void shouldThrowWhenHeightIsZero() {
        Triangle triangle = new Triangle();
        triangle.squareTriangle(6,0);
    }
}
