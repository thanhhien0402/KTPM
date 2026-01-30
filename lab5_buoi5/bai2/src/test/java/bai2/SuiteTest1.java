package bai2;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuiteTest1 {

    @Test
    public void testString() {
        String message = "Hello JUnit";
        assertEquals("Hello JUnit", message);
    }
}
