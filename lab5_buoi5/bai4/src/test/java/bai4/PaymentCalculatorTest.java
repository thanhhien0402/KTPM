package bai4;

import org.junit.Assert;
import org.junit.Test;

public class PaymentCalculatorTest {

    // ===== CHILD =====
    @Test
    public void testChildPayment() {
        int payment = PaymentCalculator.calculate(
                false,   // male
                false,   // female
                true,    // child
                10       // age
        );
        Assert.assertEquals(50, payment);
    }

    // ===== MALE =====
    @Test
    public void testMale18to35() {
        int payment = PaymentCalculator.calculate(
                true,
                false,
                false,
                30
        );
        Assert.assertEquals(100, payment);
    }

    @Test
    public void testMale36to50() {
        int payment = PaymentCalculator.calculate(
                true,
                false,
                false,
                40
        );
        Assert.assertEquals(120, payment);
    }

    @Test
    public void testMaleAbove50() {
        int payment = PaymentCalculator.calculate(
                true,
                false,
                false,
                60
        );
        Assert.assertEquals(140, payment);
    }

    // ===== FEMALE =====
    @Test
    public void testFemale18to35() {
        int payment = PaymentCalculator.calculate(
                false,
                true,
                false,
                25
        );
        Assert.assertEquals(80, payment);
    }

    @Test
    public void testFemale36to50() {
        int payment = PaymentCalculator.calculate(
                false,
                true,
                false,
                45
        );
        Assert.assertEquals(110, payment);
    }

    @Test
    public void testFemaleAbove50() {
        int payment = PaymentCalculator.calculate(
                false,
                true,
                false,
                70
        );
        Assert.assertEquals(140, payment);
    }

    // ===== BOUNDARY CASES =====
    @Test
    public void testAge17IsChild() {
        int payment = PaymentCalculator.calculate(
                false,
                false,
                false,
                17
        );
        Assert.assertEquals(50, payment);
    }

    @Test
    public void testAge18Male() {
        int payment = PaymentCalculator.calculate(
                true,
                false,
                false,
                18
        );
        Assert.assertEquals(100, payment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAge() {
        PaymentCalculator.calculate(
                true,
                false,
                false,
                -1
        );
    }
}
