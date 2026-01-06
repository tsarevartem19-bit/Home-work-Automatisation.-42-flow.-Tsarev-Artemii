package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberComparatorTest {

    @Test
    void shouldReturnOneWhenANumberIsGreaterThanB(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(5,3);
        assertEquals(1,result);
    }

    @Test
    void shouldReturnMinusOneWhenANumberIsLessThanB(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(1,3);
        assertEquals(-1,result);
    }

    @Test
    void shouldReturnZeroWhenNumbersAreEqual(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(3,3);
        assertEquals(0,result);
    }


}
