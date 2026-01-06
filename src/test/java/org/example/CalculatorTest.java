package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    void shouldAddTwoNumbers() {
        Calculator calculator = new Calculator();
        double result = calculator.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void shouldSubtractTwoNumbers(){
        Calculator calculator = new Calculator();
        double result = calculator.subtract(5,3);
        assertEquals(2,result);
    }

    @Test
    void shouldMultiplyTwoNumbers(){
        Calculator calculator = new Calculator();
        double result = calculator.multiply(5,5);
        assertEquals(25,result);
    }

    @Test
    void shouldDivideTwoNumbers(){
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));
        double result = calculator.divide(10,5);
        assertEquals(2,result);

    }
}
