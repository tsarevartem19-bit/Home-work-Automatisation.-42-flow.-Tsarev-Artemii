package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {

    @Test
    void shouldCalculateTriangleAreaCorrectly(){
        Triangle triangle = new Triangle();
        double result = triangle.squareTriangle (5, 10);
        assertEquals(25, result);
    }

    @Test
    void shouldThrowWhenBaseIsZero() {
        Triangle triangle = new Triangle();
        assertThrows(IllegalArgumentException.class, ()-> triangle.squareTriangle(0,5));
    }

    @Test
    void shouldThrowWhenHeightIsZero() {
        Triangle triangle = new Triangle();
        assertThrows(IllegalArgumentException.class, ()-> triangle.squareTriangle(5,0));
    }
}
