package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @Test
    void shouldCalculateFactorialCorrectlyForPositiveNumber(){
        Factorial factorial = new Factorial();
        int result = factorial.factorial(5);
        assertEquals(120,result);
    }

    @Test
    void shouldReturnOneForZero(){
        Factorial factorial = new Factorial();
        int result = factorial.factorial(0);
        assertEquals(1,result);
    }

    @Test
    void shouldThrowExceptionForNegativeNumber(){
        Factorial factorial = new Factorial();
        assertThrows(IllegalArgumentException.class, ()-> factorial.factorial(-1));
    }
}
