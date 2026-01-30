package bai1;


import org.junit.*;
        import static org.junit.Assert.*;

public class MathFuncTest {

    private MathFunc math;

    @Before
    public void setUp() {
        math = new MathFunc();
        MathFunc.calls = 0;
        System.out.println("Before test");
    }

    @After
    public void tearDown() {
        System.out.println("After test");
    }

    // Kiểm tra số lần gọi hàm
    @Test
    public void calls() {
        math.plus(1, 2);
        math.factorial(3);
        assertEquals(2, MathFunc.calls);
    }

    // Kiểm tra hàm giai thừa
    @Test
    public void factorial() {
        assertEquals(120, math.factorial(5));
    }

    // Kiểm tra giai thừa số âm
    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        math.factorial(-1);
    }

    // Kiểm tra hàm cộng
    @Ignore
    @Test
    public void todo() {
        assertEquals(5, math.plus(2, 3));
    }
}