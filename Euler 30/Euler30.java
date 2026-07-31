import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Euler30 {


    // PROBLEM DEFINITION
    // Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

    // THOUGHTS
    // 1. Feels like this will outscale itself quite quickly; 9^5 is only 59,049, we'll try brute force first
    // 2. Maths-y way to state that is at what point 10^a < a * 9^5 flips to 10^a > a*9^5. I think 10^6 bounds our problem space

    // This was also not a great exercise in writing elegant code, really.

    static void main() {

        List<Integer> validIntegers = new ArrayList<>();

        for(int i = 2; i < 1_000_000; i++) {
            List<Integer> digits = getDigits(i);
            List<Integer> powerDigits = digits.stream().map(e -> power(e, 5)).toList();

            int sumOfDigitsToTheFifthPower = 0;
            for (Integer powerDigit : powerDigits) {
                sumOfDigitsToTheFifthPower += powerDigit;
            }

            if (i == sumOfDigitsToTheFifthPower) {
                validIntegers.add(i);
            }

            System.out.println("Complete for i = " + i);
        }

        int total = 0;
        for (Integer value : validIntegers) {
            total += value;
        }

        System.out.println(total);
    }

    static int power(int base, int power) {
        int result = 1;

        for(int i = power; i > 0; i--) {
            result *= base;
        }

        return result;
    }

    static List<Integer> getDigits(Integer value) {
        return Arrays.stream((value + "").split("")).map(Integer::parseInt).toList();
    }
}
