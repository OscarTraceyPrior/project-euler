import java.util.ArrayList;
import java.util.List;

public class Euler12 {

    public static void main(String[] args) {
        System.out.println(findFirstTriangleNumberWithNDivisors(500));
    }
    
    private static long findFirstTriangleNumberWithNDivisors(int desiredNumberOfDivisors) {
        long maxDivisorsFound = 0;
        long triangleNumberWithMaxDivisors = 0;
        int indexOfTriangleNumber = 1;
        
        while(maxDivisorsFound < desiredNumberOfDivisors) {
            long nthTriangleNumber = getNthTriangleNumber(indexOfTriangleNumber);
            long numberOfDivisors = getDivisors(nthTriangleNumber).size();
            
            if(numberOfDivisors > maxDivisorsFound) {
                maxDivisorsFound = numberOfDivisors;
                triangleNumberWithMaxDivisors = nthTriangleNumber;
                System.out.println("Triangle Number: " + triangleNumberWithMaxDivisors);
                System.out.println("Divisors: " + maxDivisorsFound);
                System.out.println();
            }
            
            indexOfTriangleNumber++;
            
        }
        
        return triangleNumberWithMaxDivisors;
    }
    
    private static long getNthTriangleNumber(int n) {
        long result = 0;
        
        for(int i = n; i > 0; i--) {
            result += i;
        }
        
        return result;
    }
    
    private static List<Long> getDivisors(long n) {
        List<Long> divisors = new ArrayList<>();
        
        for(long i = 1; i <= n/2; i++) {
            if(n % i == 0) {
                if(!divisors.contains(i)) {
                    divisors.add(i);
                }
                if(!divisors.contains(n/i)) {
                    divisors.add(n/i);
                }
            }
        }
        
        return divisors;
    }
}