package bai3;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ErrorCollectorExampleTest.class);

        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Total failures : " + result.getFailureCount());

        for (Failure failure : result.getFailures()) {
            System.out.println("---- Failure ----");
            System.out.println(failure.toString());
        }

        System.out.println("All tests successful? " + result.wasSuccessful());
    }
}