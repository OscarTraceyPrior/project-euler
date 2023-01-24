import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class FileReader {
    
    public Integer[][] readGrid(Path path) throws IOException {
        List<String> fileContents = Files.readAllLines(path);
        
        String[][] numbersAsStrings = new String[fileContents.size()][];
        
        for(int i = 0; i < fileContents.size(); i++) {
            numbersAsStrings[i] = fileContents.get(i).split(" ");
        }
        
        Integer[][] numbers = new Integer[fileContents.size()][numbersAsStrings[0].length];
        
        for(int m = 0; m < numbers.length; m++) {
            for(int n = 0; n < numbers[0].length; n++) {
                numbers[m][n] = Integer.parseInt(numbersAsStrings[m][n]);
            }
        }
        
        return numbers;
    }
}