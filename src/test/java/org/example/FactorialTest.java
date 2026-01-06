package org.example;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class FactorialTest {

    @Test
    public void shouldCalculateFactorialCorrectlyForPositiveNumber(){
        Factorial factorial = new Factorial();
        assertEquals(factorial.factorial(6),720);
    }

    @Test
    public void shouldReturnOneForZero() {
        Factorial factorial = new Factorial();
        assertEquals(factorial.factorial(0), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativeNumber(){
        Factorial factorial = new Factorial();
        factorial.factorial(-5);
    }

}
