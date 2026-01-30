package bai4;

public class PaymentCalculator {

    public static int calculate(boolean isMale, boolean isFemale, boolean isChild, int age) {

        if (age < 0 || age > 145) {
            throw new IllegalArgumentException("Age invalid");
        }

        // Child
        if (isChild || age <= 17) {
            return 50;
        }

        // Male
        if (isMale) {
            if (age >= 18 && age <= 35) return 100;
            if (age >= 36 && age <= 50) return 120;
            if (age >= 51) return 140;
        }

        // Female
        if (isFemale) {
            if (age >= 18 && age <= 35) return 80;
            if (age >= 36 && age <= 50) return 110;
            if (age >= 51) return 140;
        }

        return 0;
    }
}
