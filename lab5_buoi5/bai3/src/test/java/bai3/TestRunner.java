package bai3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitAnnotationsExample.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Số test chạy: " + result.getRunCount());
        System.out.println("Số test thất bại: " + result.getFailureCount());
        System.out.println("Số test bị bỏ qua: " + result.getIgnoreCount());
        System.out.println("Kết quả: " + result.wasSuccessful());
    }
}
