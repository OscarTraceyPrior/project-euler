import java.util.*;
import java.util.stream.Collectors;

public class Euler31 {

    // PROBLEM DEFINITION
    // How many ways can you make £2 with the coins currently in circulation in the UK (£2, £1, 50p, 20p, 10p, 5p, 2p, 1p)

    // THOUGHTS
    // 1. This is something we've done before; or at least similar: The number of ways to make x value is all
    //      the ways to make x - C where C is each of the coins.

    private static final Integer TARGET_VALUE_PENCE = 200;
    private static final Set<Integer> VALID_COINS = Set.of(1, 2, 5, 10, 20, 50, 100, 200);

    static void main() {

        Map<Integer, Set<List<Integer>>> changeFor2Pound = waysToMakeChangeForValue(TARGET_VALUE_PENCE);

        System.out.println("Ways to make change for £2: " + changeFor2Pound.get(TARGET_VALUE_PENCE).size());

    }

    private static Map<Integer, Set<List<Integer>>> waysToMakeChangeForValue(Integer targetValuePence) {
        HashMap<Integer, Set<List<Integer>>> waysToMakeValue = new HashMap<>();

        for (int i = 1; i < targetValuePence + 1; i++) {
            final int currentTargetValue = i;
            Set<Integer> validCoinsToUse = VALID_COINS.stream().filter(e -> e <= currentTargetValue).collect(Collectors.toSet());

            for (Integer coin : validCoinsToUse) {
                if (!waysToMakeValue.containsKey(currentTargetValue)) {
                    waysToMakeValue.put(currentTargetValue, new HashSet<>());
                }
                if (currentTargetValue - coin == 0) {
                    ArrayList<Integer> oneCoinList = new ArrayList<>();
                    oneCoinList.add(coin);
                    waysToMakeValue.get(i).add(oneCoinList);
                } else {
                    Set<List<Integer>> waysToMakeValueMinusCoin = waysToMakeValue.get(currentTargetValue - coin);

                    for(List<Integer> changeMinusCoin : waysToMakeValueMinusCoin) {

                        List<Integer> newWay = new ArrayList<>(changeMinusCoin);
                        newWay.add(coin);
                        newWay.sort(Integer::compareTo);

                        waysToMakeValue.get(i).add(newWay);
                    }
                }
            }
        }

        return waysToMakeValue;
    }
}
