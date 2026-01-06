package org.example;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class NumberComparatorTest {
    @Test
    public void shouldReturnOneWhenANumberIsGreaterThanB(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(5,3);
        assertEquals(1,result);
    }

    @Test
    public void shouldReturnMinusOneWhenANumberIsLessThanB(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(1,3);
        assertEquals(-1,result);
    }

    @Test
    public void shouldReturnZeroWhenNumbersAreEqual(){
        NumberComparator numberComparator = new NumberComparator();
        int result = numberComparator.compareNumber(3,3);
        assertEquals(0,result);
    }

}
