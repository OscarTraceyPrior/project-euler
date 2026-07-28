import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Euler26 {


    // TODO: We just need accurate division, right? Let's try a naive approach and upgrade if necessary
    static void main() {

        Map<Integer, BigDecimal> bigDecimals = new HashMap<>();

        for(int i = 2; i < 1000; i ++) {
            bigDecimals.put(i, BigDecimal.ONE.setScale(2000, RoundingMode.DOWN).divide(new BigDecimal(i).setScale(2000, RoundingMode.DOWN), RoundingMode.DOWN));
        }

        String biggestChain = "";
        Integer d = 0;
        for(Map.Entry<Integer, BigDecimal> bigDecimal : bigDecimals.entrySet()) {
            String representation = bigDecimal.getValue().toPlainString().substring(2);

            String recurringCycle = findRecurringCycle(representation);

            if (recurringCycle.length() > biggestChain.length()) {
                biggestChain = recurringCycle;
                d = bigDecimal.getKey();
            }

        }

        System.out.println(biggestChain);
        System.out.println("Denominator w/ biggest chain: " + d);
    }

    public static String findRecurringCycle(String digits) {

        if (digits.matches("[0-9]+0+")) {
            BigDecimal truncated = new BigDecimal("0." + digits).stripTrailingZeros();
            digits = truncated.toPlainString();
            digits = digits.substring(2);
        }

        String[] splitDigits = digits.split("");
        List<String> chain = new ArrayList<>();
        chain.add(splitDigits[0]);

        for (int i = 1; i < splitDigits.length; i++) {
            if (splitDigits[i].equals(chain.getFirst()) &&
                i < splitDigits.length - 1 && chain.size() > 1 &&
                splitDigits[i+1].equals(chain.get(1)) &&
                i < splitDigits.length - 2 && chain.size() > 2 &&
                splitDigits[i+2].equals(chain.get(2))) {
                return String.join("", chain);
            } else {
                chain.add(splitDigits[i]);
            }
        }

        return splitDigits[0];
    }

}
