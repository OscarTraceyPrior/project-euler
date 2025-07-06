import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;

public class Euler16 {
    
    public static void main(String[] args) {
    
        List<Long> sums = new ArrayList<Long>();
        
        for(int i = 1; i <= 1000; i++) {
            BigInteger exponentiated = exponentiate(new BigInteger("2"), i);
            long sumOfDigits = getSumDigits(exponentiated);
            System.out.println("Power of " + i + " is: " + exponentiated);
            System.out.println("Sum of digits is: " + sumOfDigits + "\n");
            sums.add(sumOfDigits);
        }
        
        System.out.println();
        System.out.println("List of sums: " + sums);
    }
    
    private static BigInteger exponentiate(BigInteger base, long exponent) {
        if (exponent == 0) {
            return BigInteger.ONE;
        } else {
            return base.multiply(exponentiate(base, exponent - 1));
        }
    }

    private static long getSumDigits(BigInteger value) {
        String asString = value.toString();
        String[] asDigits = asString.split("");
        
        long total = 0;
        for(String digit : asDigits) {
            total += Integer.parseInt(digit);
        }
        
        return total;
    }
}