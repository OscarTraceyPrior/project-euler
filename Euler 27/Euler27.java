public class Euler27 {

    // PROBLEM DEFINITION
    // For |a| < 1000, |b| ≤ 1000, find a * b where a,b are chosen such that n^2 + an + b is prime for the most consecutive integer values of n

    // MISC. THOUGHTS
    // 1. This is going to be a mountain of computations to brute-force, let's start stupid and work from there
    // 2. There are probably some easy wins around even numbers we can secure.
    // 3. Speed seems fine, and the formula is correctly implemented, so why are we failing?
    // 4. Forgot to account for negative numbers, which can't be prime.
    // 5. Still wrong, but the jump in primes and the absence of known cases makes me think we're dropping primes somewhere

    private static final long INITIAL_A = -999L;
    private static final long INITIAL_B = -1000L;

    static void main() {

        long bestA = 0;
        long bestB = 0;
        long bestCount = 0;

        for (long a = INITIAL_A; a < 1000; a++) {
            for (long b = INITIAL_B; b < 1001; b++) {
                boolean shouldContinue;
                long n = 0;

                do {
                    long result = applyFunction(a, b, n);

                    shouldContinue = isPrime(result);

                    if (shouldContinue) {
                        n++;
                    } else {
                        if (n > bestCount) {
                            bestA = a;
                            bestB = b;
                            bestCount = n;
                            System.out.println("Most so far a: " + bestA + ", best b: " + bestB + ", with " + bestCount + " consecutive primes");
                        }

                    }
                } while (shouldContinue);
            }
        }


        System.out.println("Best a: " + bestA + ", best b: " + bestB + ", with " + bestCount + " consecutive primes");
    }

    private static long applyFunction(long a, long b, long n) {
        return square(n) + (a * n) + b;
    }

    private static long square(long value) {
        return value * value;
    }

    private static boolean isPrime(long value) {
        if (value <= 1) {
            return false;
        }

        for (long i = (long) Math.sqrt(value); i > 1; i--) {
            if (value % i == 0) {
                return false;
            }
        }

        return true;
    }
}
