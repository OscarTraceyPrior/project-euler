import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Euler32 {


    // PROBLEM DEFINITION
    // Definition - A pandigital expression from a to b is an expression that contains all digits a through b
    //     e.g. 15423 is 1 through 5 pandigital
    //     e.g. 39 * 186 = 7254 is 1 through 9 pandigital

    // Find the sum of all products whose expression incl. factors are 1 through 9 pandigital. Include each product only once.

    // THOUGHTS
    // 1. Providing pandigitality isn't really hard. The hard bit is generating all the expressions
    // 2. Naively, there are 9! ways to arrange 9 digits. That's 362,880 combinations.
    // 3. Except, we also have to include the random placement of x and =, which takes us to 39,916,800.
    // 4. Even if we exclude the invalid expressions (e.g. x123456789=), there's still too many
    // 5. We can shrink the problem space by attacking it product-end first. The biggest "potentially" pandigital expression
    //      under these conditions is 1 x 2 = 9876543, which is obviously invalid.
    // 6. We could count down from probably around 98765 and try and factorise each, then see if there's some
    //      combination of factors that is pandigital.
    // 7. Forgot about duplicates, we'll have to check every expression to make sure it includes every digit exactly once.
    // 8. Forgot to account for 0's, too.

    static void main() {

        List<Expression> pandigitalExpressions = new ArrayList<>();

        for (int i = 98675; i > 1; i--) {
            List<Pair> factors = findFactors(i);

            for (Pair pair : factors) {
                Expression expression = new Expression(pair.multiplicand(), pair.multiplier());

                if (expression.isPandigital(1,9)) {
                    pandigitalExpressions.add(expression);
                }
            }
        }

        pandigitalExpressions = getDistinctExpressions(pandigitalExpressions);
        int total = 0;
        for (Expression expression : pandigitalExpressions) {
            total += expression.getProduct();
        }

        System.out.println(pandigitalExpressions);
        System.out.println("Total of products of pandigital expressions: " + total);
    }

    private static List<Expression> getDistinctExpressions(List<Expression> expressions) {
        List<Expression> distinctExpressions = new ArrayList<>();
        Set<Integer> seenProducts = new HashSet<>();

        for(Expression expression : expressions) {
            if (!seenProducts.contains(expression.getProduct())) {
                distinctExpressions.add(expression);
                seenProducts.add(expression.getProduct());
            }
        }

        return distinctExpressions;
    }

    private static List<Pair> findFactors(Integer value) {
        List<Pair> factors = new ArrayList<>();
        factors.add(new Pair(1, value));

        for (int i = 2; i < Math.sqrt(value); i++) {
            if (value % i == 0) {
                factors.add(new Pair(i, value/i));
            }
        }

        return factors;
    }
}

record Pair(int multiplicand, int multiplier) { }


class Expression {

    private final int multiplicand;
    private final int multiplier;
    private final int product;

    public Expression (int multiplicand, int multiplier) {
        this.multiplicand = multiplicand;
        this.multiplier = multiplier;
        this.product = multiplicand * multiplier;
    }

    public int getProduct() {
        return this.product;
    }

    // Pretty quick and dirty way to check if it contains a digit more than once, but it should work.
    public boolean isPandigital(int startDigit, int endDigit) {
        for(int i = startDigit; i <= endDigit; i++) {
            String asString = this.toString();
            if (!asString.contains(i + "") ||
                asString.contains("0") ||
            asString.replace(i + "", "").length() < asString.length() - 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return multiplicand + "x" + multiplier + " = " + product;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Expression) {
            return ((Expression) o).getProduct() == this.getProduct();
        } else {
            return false;
        }
    }

}
