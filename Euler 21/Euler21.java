import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class Euler21 { 
    
    private static final int NUMBER_OF_ITERATIONS = 10000;
    
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_ITERATIONS);
        
        int sum = 0;
        
        Set<Integer> amicableNumbers = new HashSet<>();
        
        for (int i = NUMBER_OF_ITERATIONS; i > 0; i--) {
            final int someConstant = i;
            executor.submit( () -> 
            {
                System.out.println("On iteration: " + someConstant);
                for(int j = someConstant; j > 0; j--) {
                    if (isAmicable(someConstant, j)) {
                        amicableNumbers.add(someConstant);
                        amicableNumbers.add(j);
                    }
                }
                latch.countDown();
            }
            );
        }
        
        latch.await();
        
        for (Integer value : amicableNumbers) {
            sum += value;
        }
        
        System.out.println("Numbers: " + amicableNumbers);
        System.out.println("Result: " + sum);
        executor.shutdown();
    }
    
    private static boolean isAmicable(int a, int b) {
        boolean result = a != b && 
            getSum(getDivisors(a)) == b &&
            getSum(getDivisors(b)) == a;
        if (result) {
            System.out.println("Amicable: " + a + " & " + b);
        }
        return result;
    }
    
    private static int getSum(List<Integer> values) {
        int result = 0;
        
        for (Integer value : values) {
            result += value;
        }
        
        return result;
    }
    
    private static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && n != i) {
                divisors.add(i);
            }
        }
        
        return divisors;
    }
}