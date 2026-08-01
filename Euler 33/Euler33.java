import java.util.*;
import java.util.stream.Collectors;

public class Euler33 {


    // PROBLEM DEFINITION
    // Definition - Flukey Fractions are fractions where an inexperienced mathematician will get the right answer
    //              when simplifying them by "cancelling out" digits that appear on both the top and bottom
    //              even though that's not a valid mechanism for simplifying fractions (e.g. 49/98 = 4/8)
    //
    // There are 4 non-trivial examples of this type of fraction (e.g. not 11/22) less than < 1 and with two digits in numerator
    // and denominator
    // Find the product of those four examples and simplify to smallest terms, providing the denominator

    // THOUGHTS
    // 1. Lots of operations to do here, but not really all that much work.
    // 2. We should reimplement factorisation methods and work from there.
    // 3. We have definitely spent too long messing with the ProperSet and Fraction classes

    // This was horrendous, I am dead certain I have missed some critical breakthrough that would have trivialised it
    // Maybe something to do with the problem space? I probably automated too much of it, really...


    static void main() {

        List<Fraction> freakyFractions = new ArrayList<>();

        for (int i = 10; i < 100; i++) {
            for (int j = (i + 1); j < 100; j++) {
                Fraction fraction = new Fraction(i, j);
                if (fraction.isFreaky()) {
                    freakyFractions.add(fraction);
                }
            }
        }

        System.out.println("List of freaky fractions: " + freakyFractions);


        Fraction product = new Fraction(1, 1);

        for (Fraction freakyFraction : freakyFractions) {
            product = product.multiply(freakyFraction);
        }

        System.out.println("The product of freaky fractions in simplest form is: " + product.toSimplestForm());
    }

}

class TypedPair<T> {

    private final T left;
    private final T right;

    TypedPair(T[] values) {
        if (values.length != 2) {
            throw new RuntimeException("Nope, need exactly 2 values");
        }
        this.left = values[0];
        this.right = values[1];
    }

    public T left() {
        return left;
    }

    public T right() {
        return right;
    }
}

class Fraction {

    final int denominator;
    final int numerator;
    final double value;

    public Fraction(int numerator, int denominator) {
        this.denominator = denominator;
        this.numerator = numerator;
        this.value = (double) numerator / denominator;

        if (value > 1) {
            throw new RuntimeException(numerator + "/" + denominator + " | Nope, don't care about values greater than 1 today");
        }
    }

    public double getValue() {
        return value;
    }

    // This is SUPREMELY sketchy code; I'm missing a trick somewhere for sure
    public boolean isFreaky() {
        TypedPair<String> numeratorDigits = new TypedPair((numerator + "").split(""));
        TypedPair<String> denominatorDigits = new TypedPair((denominator + "").split(""));


        int newNumerator = 0;
        int newDenominator = 0;
        if (numeratorDigits.left().equals(denominatorDigits.left()) && !"0".equals(numeratorDigits.left())) {
            newNumerator = Integer.parseInt(numeratorDigits.right());
            newDenominator = Integer.parseInt(denominatorDigits.right());
        } else if (numeratorDigits.left().equals(denominatorDigits.right()) && !"0".equals(numeratorDigits.left())) {
            newNumerator = Integer.parseInt(numeratorDigits.right());
            newDenominator = Integer.parseInt(denominatorDigits.left());
        } else if (numeratorDigits.right().equals(denominatorDigits.left()) && !"0".equals(numeratorDigits.right())) {
            newNumerator = Integer.parseInt(numeratorDigits.left());
            newDenominator = Integer.parseInt(denominatorDigits.right());
        } else if (numeratorDigits.right().equals(denominatorDigits.right()) && !"0".equals(numeratorDigits.right())) {
            newNumerator = Integer.parseInt(numeratorDigits.left());
            newDenominator = Integer.parseInt(denominatorDigits.left());
        }

        if (newNumerator < newDenominator) {
            Fraction maybeFreaky = new Fraction(newNumerator, newDenominator);
            return maybeFreaky.getValue() == this.getValue();
        }


        return false;
    }

    public Fraction toSimplestForm() {
        Fraction result = this;

        while (result.canBeSimplified()) {
            result = result.simplify();
        }

        return result;
    }

    public Fraction simplify() {
        ProperSet<Integer> factorsOfNumerator = Util.getNon1Factors(numerator);
        ProperSet<Integer> factorsOfDenominator = Util.getNon1Factors(denominator);

        ProperSet<Integer> sharedFactors = factorsOfNumerator.intersection(factorsOfDenominator);
        Fraction result = this;

        if (canBeSimplified()) {
            Integer biggestSharedFactor = sharedFactors.max();
            result = new Fraction(numerator / biggestSharedFactor, denominator / biggestSharedFactor);
        }

        return result;
    }

    public boolean canBeSimplified() {
        ProperSet<Integer> factorsOfNumerator = Util.getNon1Factors(numerator);
        ProperSet<Integer> factorsOfDenominator = Util.getNon1Factors(denominator);

        return factorsOfNumerator.intersection(factorsOfDenominator).size() != 0;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public Fraction multiply(Fraction otherFraction) {
        return new Fraction(this.numerator * otherFraction.getNumerator(), this.denominator * otherFraction.getDenominator());
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction otherFraction) {
            return this.numerator == otherFraction.getNumerator() &&
                   this.denominator == otherFraction.getDenominator() &&
                   this.value == otherFraction.getValue();
        } else {
            return false;
        }
    }
}

// Sick of the Set API not actually having set operations on it, absolutely useless.
class ProperSet<T extends Comparable<T>> {

    private final HashSet<T> backingSet = new HashSet<>();

    public ProperSet(ProperSet<T> set) {
        this.backingSet.addAll(set.getValues());
    }

    public ProperSet(Collection<T> values) {
        this.backingSet.addAll(values);
    }

    public boolean contains(T value) {
        return backingSet.contains(value);
    }

    public void add(T value) {
        backingSet.add(value);
    }

    public void addAll(ProperSet<T> set) {
        backingSet.addAll(set.getValues());
    }

    public void remove(T value) {
        backingSet.remove(value);
    }

    public Set<T> getValues() {
        return new HashSet<>(backingSet);
    }

    public ProperSet<T> union(ProperSet<T> otherSet) {
        ProperSet<T> union = new ProperSet<>(new HashSet<T>());
        union.addAll(this);
        union.addAll(otherSet);
        return union;
    }

    // ugly, but instantiating generic arrays seems hard
    public ProperSet<T> intersection(ProperSet<T> otherSet) {
        Set<T> sharedElements = backingSet.stream().filter(otherSet::contains).collect(Collectors.toSet());

        return new ProperSet<>(sharedElements);
    }

    public int size() {
        return backingSet.size();
    }

    public T max() {
        return backingSet.stream().max(Comparator.naturalOrder()).orElse(null);
    }
}

// very naughty, but I can't be bothered making this pretty
class Util {

    public static ProperSet<Integer> getNon1Factors(int value) {
        ProperSet<Integer> factors = new ProperSet<>(new HashSet<Integer>());

        for (int i = 1; i < Math.sqrt(value); i++) {
            if (value % i == 0) {
                factors.add(i);
                factors.add(value / i);
            }
        }

        return factors;
    }

}
