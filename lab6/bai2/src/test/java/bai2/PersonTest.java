package bai2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonTest {

    // Cách 2: ExpectedException Rule
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // ========== CASE HỢP LỆ ==========
    @Test
    public void TC_B2_01_CreatePerson_AgeZero_ShouldPass() {
        Person p = new Person("An", 0);
        assertEquals("An", p.getName());
        assertEquals(0, p.getAge());
    }

    @Test
    public void TC_B2_02_CreatePerson_AgePositive_ShouldPass() {
        Person p = new Person("Binh", 25);
        assertEquals("Binh", p.getName());
        assertEquals(25, p.getAge());
    }

    // ========== CASE NGOẠI LỆ (age < 0) ==========

    // Cách 1: @Test(expected = ...)
    @Test(expected = IllegalArgumentException.class)
    public void TC_B2_03_CreatePerson_AgeNegative_WithExpectedAnnotation_ShouldThrow() {
        new Person("Chi", -1);
    }

    // Cách 2: ExpectedException Rule + check message
    @Test
    public void TC_B2_04_CreatePerson_AgeNegative_WithRule_ShouldThrowAndMessageMatch() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Age must be >= 0");
        new Person("Dung", -5);
    }

    // Cách 3: try-catch + assert message/type
    @Test
    public void TC_B2_05_CreatePerson_AgeNegative_WithTryCatch_ShouldThrow() {
        try {
            new Person("Huy", -10);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Age must be >= 0", ex.getMessage());
        }
    }

    // Thêm vài case robust cho tuổi âm khác
    @Test(expected = IllegalArgumentException.class)
    public void TC_B2_06_CreatePerson_AgeMinValue_ShouldThrow() {
        new Person("Lan", Integer.MIN_VALUE);
    }

    @Test
    public void TC_B2_07_CreatePerson_AgeOne_ShouldPass() {
        Person p = new Person("Minh", 1);
        assertEquals(1, p.getAge());
    }
}
