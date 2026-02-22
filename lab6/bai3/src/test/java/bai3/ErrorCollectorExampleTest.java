package bai3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;

public class ErrorCollectorExampleTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testWithErrorCollector_ShouldCollectAllErrors() {
        ErrorCollectorExample ex = new ErrorCollectorExample();

        // Những dòng dưới cố tình tạo lỗi để thấy ErrorCollector gom lỗi
        collector.checkThat("Add 2+3 should be 5", ex.add(2, 3), equalTo(5));      // PASS
        collector.checkThat("Add 10+5 should be 20", ex.add(10, 5), equalTo(20)); // FAIL (thực tế 15)
        collector.checkThat("Concat A+B should be AB", ex.concat("A", "B"), equalTo("AB")); // PASS
        collector.checkThat("Concat Hello+World should be Hello World", ex.concat("Hello", "World"),
                equalTo("Hello World")); // FAIL (thực tế HelloWorld)
    }
}