package bai3;

import org.junit.*;

public class JunitAnnotationsExample {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass: Chạy 1 lần trước tất cả test");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("AfterClass: Chạy 1 lần sau tất cả test");
    }

    @Before
    public void before() {
        System.out.println("Before: Chạy trước mỗi test");
    }

    @After
    public void after() {
        System.out.println("After: Chạy sau mỗi test");
    }

    @Test
    public void testCase1() {
        System.out.println("Test case 1");
        Assert.assertTrue(true);
    }

    @Test
    public void testCase2() {
        System.out.println("Test case 2");
        Assert.assertEquals(10, 5 + 5);
    }

    @Ignore
    @Test
    public void testCaseIgnore() {
        System.out.println("Test case bị bỏ qua");
    }
}
