public class Euler28 {
    public static final int MAX_SIZE = 1001;


    // PROBLEM DEFINITION
    // Number are arranged in a spiral grid, moving clockwise from the centre. The grid has odd-numbered side lengths.
    // Find the sum of diagonal numbers on a 1001*1001 grid.

    // THOUGHTS
    // 1. Several approaches:
    //      - Brute force (generate the grid element by element following the spiral then physically tally up the values)
    //      - Maths it out (there's probably a calculable formula for how the diagonal values grow)
    //      - Recursion (the outer spiral is just a ring around the inner one; generate the inner ones first)
    //      - Non-Array Representation (direct modelling is complicated; you could just use mathematics to calculate the jumps)
    //  "Maths it out" is probably the fastest and easiest
    //  "Recursion" is probably second, but will have a massive memory footprint
    //  "Brute force" will be slow and complicated, but lightweight
    // 2. For "Brute force", you can either generate from the centre clockwise or the top right anticlockwise. Top right is probably easier.
    // 3. Brute forcing it into a grid is too much faff; you have to remember where you were, what you last changed, what your index is relative to the size of the array
    // 4. Maths way is stupid easy

    // EXAMPLE GRID
    //
    //         43 44 45 46 47 48 49
    //         42 21 22 23 24 25 26
    //         41 20  7  8  9 10 27
    //         40 19  6  1  2 11 28
    //         39 18  5  4  3 12 29
    //         38 17 16 15 14 13 30
    //         37 36 35 34 33 32 31

    // MATHS APPROACH
    // Jumps centre to top left: +6, +14, +22
    // Jumps centre to top right: +8, +16, +24
    // Jumps centre to bottom left: +4, +12, +20
    // Jumps centre to bottom right: +2, +10, +18
    // Size:       1, 3, 5, 7, 9, 11
    // Ringcount:  1, 2, 3, 4, 5, 6

    static void main() {
        Sequence centreToTopLeft = new Sequence(1, 6, 8);
        Sequence centreToTopRight = new Sequence(1, 8, 8);
        Sequence centreToBottomLeft = new Sequence(1, 4, 8);
        Sequence centreToBottomRight = new Sequence(1, 2, 8);

        int ringCount = 1;
        int size = 1;
        while (size != 1001) {
            ringCount++;
            size += 2;
        }

        int total = 0;
        for (int i = 0; i < ringCount; i++) {

            total += centreToTopLeft.getNthTerm(i);
            total += centreToTopRight.getNthTerm(i);
            total += centreToBottomLeft.getNthTerm(i);
            total += centreToBottomRight.getNthTerm(i);
        }

        total -= 3; // It gets counted 4 times, we should only count it once.

        System.out.println(total);
    }
}


class Sequence {

    private final int startNumber;
    private final int baseDifferenceBetweenTerms;
    private final Integer additiveRateOfGrowth;


    public Sequence(int startNumber, int baseDifferenceBetweenTerms, Integer additiveRateOfGrowth) {
        this.startNumber = startNumber;
        this.baseDifferenceBetweenTerms = baseDifferenceBetweenTerms;
        this.additiveRateOfGrowth = additiveRateOfGrowth;
    }

    public int getNthTerm(int n) {
        int result = startNumber;

        if (additiveRateOfGrowth != null) {
            for (int i = 0; i < n; i++) {
                result += baseDifferenceBetweenTerms + (additiveRateOfGrowth*i);
            }
        } else {
            result += baseDifferenceBetweenTerms*n;
        }

        return result;
    }

}
