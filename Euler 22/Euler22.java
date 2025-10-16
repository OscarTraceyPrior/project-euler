import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;

public class Euler22 {
    
    private static final HashMap<String, Integer> LETTER_SCORE = new HashMap<>();
    
    static {
        LETTER_SCORE.put("A",1);
        LETTER_SCORE.put("B",2);
        LETTER_SCORE.put("C",3);
        LETTER_SCORE.put("D",4);
        LETTER_SCORE.put("E",5);
        LETTER_SCORE.put("F",6);
        LETTER_SCORE.put("G",7);
        LETTER_SCORE.put("H",8);
        LETTER_SCORE.put("I",9);
        LETTER_SCORE.put("J",10);
        LETTER_SCORE.put("K",11);
        LETTER_SCORE.put("L",12);
        LETTER_SCORE.put("M",13);
        LETTER_SCORE.put("N",14);
        LETTER_SCORE.put("O",15);
        LETTER_SCORE.put("P",16);
        LETTER_SCORE.put("Q",17);
        LETTER_SCORE.put("R",18);
        LETTER_SCORE.put("S",19);
        LETTER_SCORE.put("T",20);
        LETTER_SCORE.put("U",21);
        LETTER_SCORE.put("V",22);
        LETTER_SCORE.put("W",23);
        LETTER_SCORE.put("X",24);
        LETTER_SCORE.put("Y",25);
        LETTER_SCORE.put("Z",26);        
    }
    
    public static void main(String[] args) throws IOException {
        List<String> fileContents = Files.readAllLines(Paths.get("./names.txt"));
        String[] rawNames = fileContents.get(0).replaceAll("\"", "").split(",");
        List<String> actualNames = Arrays.asList(rawNames);
        
        
        List<String> sorted = actualNames.stream().sorted().toList();
        
        Long total = 0L;
        
        for (int i = 0; i < sorted.size(); i++) {
            total += ((i+1) * getWordScore(sorted.get(i)));
        }
        
        System.out.println("The total score is: " + total);
    }
    
    public static int getWordScore(String word) {
        int value = 0;
        
        for (String letter : word.toUpperCase().split("")) {
            value += LETTER_SCORE.get(letter);
        }
        
        return value;
    }
}