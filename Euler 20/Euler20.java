import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;

public class Euler20 {
    
    public static final long N = 100L;
    
    
    public static void main(String[] args) {
        BigInteger value = new BigInteger("1");
        
        for (int i = 100; i > 0; i--) {
            value = value.multiply(new BigInteger(i + ""));
        }
        
        String numberAsString = value.toString();
        
        String[] digits = numberAsString.split("");
        
        int sumOfDigits = 0;
        for(String digit : digits) {
            sumOfDigits += Integer.parseInt(digit);
        }
        
        System.out.println(sumOfDigits);
    }
}