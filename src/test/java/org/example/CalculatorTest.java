package org.example;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class CalculatorTest {

    @Test
    public void shouldAddTwoNumbers(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.add(3,2),5);
    }

    @Test
    public void shouldSubtractTwoNumbers(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.subtract(6,3),3);

    }

    @Test
    public void shouldMultiplyTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(calculator.multiply(5, 5), 25);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowWhenDivideByZero() {
        Calculator calculator = new Calculator();
        calculator.divide(6, 0);
    }

    @Test
   public void shouldDivideTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(calculator.divide(15, 5), 3);
    }


}
