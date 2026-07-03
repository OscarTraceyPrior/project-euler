import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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

    public static List<Integer> NUMBERS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static List<Integer> TEST_CASE  = List.of(7, 8, 9);
    public static List<Integer> RECURSIVE_TEST_CASE  = List.of(6, 7, 8, 9);
    public static List<Integer> PROFILING_CASE  = List.of(2, 3, 4, 5, 6, 7, 8, 9);

    public static void main (String[] args) {

        List<Permutation> permutations = generateOrderedPermutations(PROFILING_CASE);
//        List<Permutation> permutations = generateOrderedPermutations(PROFILING_CASE);

        testLexicographicPermutationOrder(permutations);
    }
    
//    public static List<Permutation> generateOrderedPermutations(List<Integer> values) {
//        List<Permutation> permutations = new ArrayList<>();
//        int expectedPermutations = factorial(values.size());
//
//        // Easier to work with sorted values; the smallest permutation will always be the one with digits in ascending order.
//        List<Integer> sortedValues = values.stream().sorted(Integer::compareTo).toList();
//        Permutation smallestPermutation = new Permutation(sortedValues);
//        permutations.add(smallestPermutation);
//
//        for (int i = 0; i < values.size(); i++) {
//            List<Integer> rawPermutation = new ArrayList<>();
//
//            rawPermutation.add(values.get(i));
//            List<Integer> sublist = values.subList(1, values.size());
//        }
//
//
//        if (permutations.size() != expectedPermutations) {
//            throw new RuntimeException("Got less permutations than expected.");
//        }
//
//        return null;
//    }


    static List<Permutation> generateOrderedPermutations(List<Integer> digits) {
        if (digits.size() == 1) {
            return List.of(new Permutation(digits));
        }

        if (digits.size() == 2) {
            return List.of(new Permutation(digits), new Permutation(digits.reversed()));
        }

        int pivotIndex = digits.size() - 1; // Pivot sits on the index of the element it is to the *LEFT* of.
        int expectedNumberOfPermutations = factorial(digits.size());
        List<Permutation> permutations = new ArrayList<>();

        while (permutations.size() != expectedNumberOfPermutations) {
            if (pivotIndex > 0) {
                List<Permutation> subPermutations = generateOrderedPermutations(digits.subList(pivotIndex, digits.size()));

                for (Permutation subPermutation : subPermutations) {
                    permutations.add(new Permutation(convertToDigits(convertToString(digits.subList(0, pivotIndex)) + subPermutation.toString())));
                }

                pivotIndex--;
            } else {
                List<Integer> digitsToBeFirst = new ArrayList<>(digits.stream().filter(e -> !Objects.equals(e, digits.getFirst())).toList());


                while (!digitsToBeFirst.isEmpty()) {
                    String smallest = digitsToBeFirst.stream().sorted().findFirst().get() + "";

//                    List<Integer> digitsExcludingSmallest = digits.stream().filter(e -> !Objects.equals(e, Integer.parseInt(smallest))).toList();
                    List<Integer> digitsExcludingSmallest = new ArrayList<>(digits);
                    digitsExcludingSmallest.remove((Object) Integer.parseInt(smallest));
                    List<Permutation> subPermutations = generateOrderedPermutations(digitsExcludingSmallest);
                    for (Permutation subPermutation : subPermutations) {
                        Permutation newPermutation = new Permutation(convertToDigits(smallest + subPermutation.toString()));
                        permutations.add(newPermutation);
                    }

//                    digitsToBeFirst = digitsToBeFirst.stream().filter(e -> !Objects.equals(e, Integer.parseInt(smallest))).toList();
                    digitsToBeFirst.remove((Object) Integer.parseInt(smallest));
                }
            }
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

    public static String convertToString(List<Integer> digits) {
        StringBuilder sb = new StringBuilder();

        for (Integer digit : digits) {
            sb.append(digit);
        }

        return sb.toString();
    }

    public static List<Integer> convertToDigits(String stringDigits) {
        return Arrays.stream(stringDigits.split("")).map(Integer::parseInt).toList();
//        List<Integer> ints = new ArrayList<>();
//
//        for (int i = 0; i < stringDigits.length(); i++) {
//            ints.add(Character.getNumericValue(stringDigits.charAt(i)));
//        }
//
//        return ints;
    }


    public static void testLexicographicPermutationOrder(List<Permutation> permutations) {
        for (int i = 0; i < permutations.size() -1; i++) {
            if (permutations.get(i).compareTo(permutations.get(i + 1)) > 0) {
                System.out.println(permutations);
                throw new AssertionError("Permutations are not properly ordered!");
            }
        }

        System.out.println(permutations);
        System.out.println("Permutations are correctly ordered!");
    }


    public static class Permutation implements Comparable<Permutation> {

        private final String permutation;

        public Permutation(List<Integer> permutation) {
            this.permutation = convertToString(permutation);
        }

        @Override
        public String toString() {
            return permutation;
        }

        @Override
        public int compareTo(Permutation o) {
            return Integer.parseInt(this.toString()) - Integer.parseInt(o.toString());
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Permutation && this.permutation.equals(((Permutation) o).permutation);
        }
    }
}


