package bai1;

public class MathFunc {

    public static int calls = 0;

    // Hàm tính giai thừa
    public long factorial(int n) {
        calls++;
        if (n < 0) {
            throw new IllegalArgumentException("So am khong hop le");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Hàm tính tổng
    public int plus(int a, int b) {
        calls++;
        return a + b;
    }
}