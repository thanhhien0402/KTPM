package lab6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AirthematicTest {

    @Test(expected = ArithmeticException.class)
    public void testDivide_ByZero_ShouldThrowArithmeticException() {
        JunitMessage jm = new JunitMessage();
        jm.divide(10, 0);
    }

    @Test
    public void testDivide_ValidNumbers_ShouldReturnQuotient() {
        JunitMessage jm = new JunitMessage();
        assertEquals(5, jm.divide(10, 2));
    }
}
