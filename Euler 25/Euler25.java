import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Euler25 {

    // I actually massively overestimated this as a problem, I fully thought 1000 would be too much to handle, but, looks like no


    static void main() {

        BigInteger bigInteger = BigInteger.ZERO;
        int index = 0;
        while(bigInteger.toString().length() < 1000) {
            bigInteger = fibonacci(BigInteger.ONE, BigInteger.ONE, index, new HashMap<>());
            System.out.println(bigInteger.toString().length());
            index++;
        }

        System.out.println("Index of first fibonacci over 1000 digits: " + index);
    }


    private static BigInteger fibonacci(BigInteger start, BigInteger start2, int indexToReach, Map<Integer, BigInteger> cache) {
        cache.put(0, start);
        cache.put(1, start2);

        BigInteger result = cache.get(indexToReach);
        if (result == null) {
            result = fibonacci(start, start2, indexToReach - 2, cache).add(fibonacci(start, start2, indexToReach-1, cache));
            cache.put(indexToReach, result);
        }

        return result;
    }
}
