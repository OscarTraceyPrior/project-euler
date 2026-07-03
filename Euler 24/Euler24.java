import java.util.*;
import java.util.concurrent.*;

public class Euler24 {

    /* TODO: Mad notes from sticky
        012345678.9 - Fully sorted, pivot starts between last and penultimate digit (8 & 9). # Digits right of pivot = 1, expect 1! permutations.
        01234567.98 - Swap the digit to the left of the pivot with the smallest digit to the right of the pivot. Move pivot left. # digits right of the pivot = 2, expect 2! total permutations for 2 digits.
        0123456.87’9 - Swap the digit to the left of the pivot with the smallest digit to the right of the pivot. Move pivot to the left. Expect 3! permutations, so no pivot move. Introduce child pivot w/ 1 permutation under its belt.
        0123456.8’97 - Swap the digit to the left of the child pivot with the smallest digit to the right of the child pivot. Move the child pivot to the left. Child pivot expects 2! total permutations.
        0123456.97’8 - If child pivot position = parent pivot position, swap the digit to the right of the pivot with the next smallest digit after the one it just saw. Re-sort the digits after the number to the right of the pivot. Introduce child pivot
        0123456.9’87 - Swap the digit to the left of the child pivot with the smallest digit to the right of the child pivot. Move the child pivot to the left. Child pivot expects 2! total permutations.
        012345.768’9 - If child pivot position = parent pivot position & number of permutations = (number of digits to right of pivot)!, move parent pivot, start again.
        012345.76’98
        012345.7’86~9 - This essentially just becomes recursion; child pivots on child pivots.
        */

    public static List<String> NUMBERS = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    public static List<String> TEST_CASE = List.of("7", "8", "9");
    public static List<String> RECURSIVE_TEST_CASE = List.of("6", "7", "8", "9");
    public static List<String> PROFILING_CASE = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    public static Map<String, List<Permutation>> solved = new HashMap<>();

    // TODO: (1) job is an optimisation for adding 0 at the front. Might not need to calculate, surely just the millionth ordered permutation with 0 at the front?
    //  [this doesn't work, there are only 300k permutations without the 0
    // TODO: (2) thought, cached results style again. We'll get some memory overhead and pain in the form of comparisons to keys, but it might save us more computation.
    //  [this also doesn't work, gave an incorrect answer of 2783915604
    // TODO: (3) thought, El suggested we go for a simple recursive structure. In the initial consideration of the problem, I think we missed that if
    //  we went for the prepend-value based recursion, the *other* loops would account for every value being included in the mix early on.
    //  I suppose the other consideration is the sorting, though. We'd have to give another thorough sort after the fact since there's no guarantee the basic recursion would keep it sorted.
    // TODO: (4) thought, El's solution didn't work; it gave me the same answer as my original implementation: 2783915604
    public static void main(String[] args) throws Exception {

//        List<Permutation> permutations = generateOrderedPermutations(NUMBERS);

        NUMBERS.forEach(e -> solved.put(e, List.of(new Permutation(List.of(e)))));
        List<Permutation> permutations = generateOrderedPermutations(NUMBERS);
        if (permutations.size() != factorial(10)) {
            throw new RuntimeException("bad number");
        }

        testLexicographicPermutationOrder(permutations);
        System.out.println("THE ANSWER:   " + permutations.get(999_999));
    }

    static List<Permutation> generateOrderedPermutations(List<String> digits) {
        if (digits.size() == 1) {
            return List.of(new Permutation(digits));
        }

        if (digits.size() == 2) {
            return List.of(new Permutation(digits), new Permutation(digits.reversed()));
        }

        List<Permutation> permutations = new ArrayList<>();

        for (String digit : digits) {
            permutations.addAll(generateOrderedPermutations(digits.stream()
                    .sorted()
                    .filter(e -> !e.equals(digit))
                    .toList()).stream()
                    .map(e -> {
                        e.prepend(digit);
                        return e;
                    }).toList());
        }

        return permutations.stream().distinct().toList();
    }


    public static int factorial(int base) {
        if (base < 2) {
            return base;
        } else {
            return base * factorial(base - 1);
        }
    }

    public static String convertToString(List<String> digits) {
        StringBuilder sb = new StringBuilder();

        for (String digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }


    public static void testLexicographicPermutationOrder(List<Permutation> permutations) {
        for (int i = 0; i < permutations.size() - 1; i++) {
            if (permutations.get(i).compareTo(permutations.get(i + 1)) > 0) {
                System.out.println(permutations);
                throw new AssertionError("Permutations are not properly ordered!");
            }
        }

        System.out.println(permutations);
        System.out.println("Permutations are correctly ordered!");
    }


    public static class Permutation implements Comparable<Permutation> {

        private String permutation;

        public Permutation(List<String> permutation) {
            this.permutation = convertToString(permutation);
        }

        public Permutation(String permutation) {
            this.permutation = permutation;
        }

        @Override
        public String toString() {
            return permutation;
        }

        @Override
        public int compareTo(Permutation o) {
            return this.permutation.compareTo(o.toString());
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Permutation && this.permutation.equals(((Permutation) o).permutation);
        }

        public void prepend(String digit) {
            this.permutation = digit + permutation;
        }
    }
}


