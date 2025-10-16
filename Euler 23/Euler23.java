import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Euler23 { 
    
    public static final int LIMIT = 28_124;
    
    public static void main(String[] args) {
        List<Integer> abundantNumbers = new ArrayList<>();
        Set<Integer> sumsOfAbundantNumbers = new HashSet<>();
        List<Integer> numbersUpToLimit = new ArrayList<>();
        
        for (int i = 1; i < LIMIT; i++) {
            if(isAbundant(i)) {
                abundantNumbers.add(i);
            }
            numbersUpToLimit.add(i);
        }
        
        System.out.println("Number of abundant numbers: " + abundantNumbers.size());
        for (int a = 0; a < abundantNumbers.size(); a++) {
            for (int b = 0; b < abundantNumbers.size(); b++) {
                sumsOfAbundantNumbers.add(abundantNumbers.get(a) + abundantNumbers.get(b));
                System.out.println(abundantNumbers.get(a) + abundantNumbers.get(b));
            }
        }
        
        List<Integer> nonAbundantSums = numbersUpToLimit.stream()
            .filter(e -> !sumsOfAbundantNumbers.contains(e))
            .toList();
        
        int total = 0;
        
        for (Integer value : nonAbundantSums) {
            total += value;
        }
        
        System.out.println("Total value of non-abundant sums: " + total);
    }
    
    public static boolean isAbundant(Integer value) {
        int total = 0;
        
        List<Integer> divisors = getProperDivisors(value);
        
        for (Integer divisor : divisors) {
            total += divisor;
        }
        
        return total > value;
    }
    
    public static List<Integer> getProperDivisors(Integer value) {
        List<Integer> divisors = new ArrayList<>();
        
        for (int i = 1; i < value; i++) {
            if (value % i == 0) {
                divisors.add(i);
            }
        }
        
        return divisors;
    }
}