import java.nio.file.Paths;

public class Application {

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader();
        Integer[][] grid;
        
        if("test".equals(args[0])) {
            runTests();
            
        }
        
        grid = reader.readGrid(Paths.get("./grid.txt"));
        
        double biggestHorizontalProduct = testHorizontals(grid);
        System.out.println("Biggest Horizontal : " + biggestHorizontalProduct);
        
        double biggestVerticalProduct = testVerticals(grid);
        System.out.println("Biggest Vertical : " + biggestVerticalProduct);
        
        double biggest_TLBR_Diagonal = test_TLBR_Diagonal(grid);
        System.out.println("Biggest Top Left Bottom Right Diagonal : " + biggest_TLBR_Diagonal);
        
        double biggest_TRBL_Diagonal = test_TRBL_Diagonal(grid);
        System.out.println("Biggest Top Right Bottom Left Diagonal : " + biggest_TRBL_Diagonal);
        
        double maxValue = max(biggestHorizontalProduct, biggestVerticalProduct, biggest_TLBR_Diagonal, biggest_TRBL_Diagonal);
            
        System.out.println("Biggset Overall : " + maxValue);
    }
    
    
    public static double testHorizontals(Integer[][] grid) {
        double biggestProduct = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length - 3; j++) {
                int factor1 = grid[i][j];
                int factor2 = grid[i][j+1];
                int factor3 = grid[i][j+2];
                int factor4 = grid[i][j+3];
                double product = factor1 * factor2 * factor3 * factor4;
                
                if(product > biggestProduct) {
                    //test(product, factor1, factor2, factor3, factor4);
                    biggestProduct = product;
                }
            }
        }
        
        return biggestProduct;
    }
    
    public static double testVerticals(Integer[][] grid) {
        double biggestProduct = 0;
        
        for(int i = 0; i < grid.length - 3; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                int factor1 = grid[i][j];
                int factor2 = grid[i+1][j];
                int factor3 = grid[i+2][j];
                int factor4 = grid[i+3][j];
                double product = factor1 * factor2 * factor3 * factor4;
                
                if(product > biggestProduct) {
//                    test(product, factor1, factor2, factor3, factor4);
                    biggestProduct = product;
                }
            }
        }
        
        return biggestProduct;
    }
    
    public static double test_TLBR_Diagonal(Integer[][] grid) {
        double biggestProduct = 0;
        
        for(int i = 0; i < grid.length - 3; i++) {
            for(int j = 0; j < grid[i].length - 3; j++) {
                int factor1 = grid[i][j];
                int factor2 = grid[i+1][j+1];
                int factor3 = grid[i+2][j+2];
                int factor4 = grid[i+3][j+3];
                double product = factor1 * factor2 * factor3 * factor4;
                
                if(product > biggestProduct) {
//                    test(product, factor1, factor2, factor3, factor4);
                    biggestProduct = product;
                }
            }
        }
        
        return biggestProduct;
    }
    
    public static double test_TRBL_Diagonal(Integer[][] grid) {
        double biggestProduct = 0;
        
        for(int i = 0; i < grid.length -3; i++) {
            for(int j = 0; j < grid[i].length - 3; j++) {
                int factor1 = grid[i][j+3];
                int factor2 = grid[i+1][j+2];
                int factor3 = grid[i+2][j+1];
                int factor4 = grid[i+3][j];
                double product = factor1 * factor2 * factor3 * factor4;
                
                if(product > biggestProduct) {
//                    test(product, factor1, factor2, factor3, factor4);
                    biggestProduct = product;
                }
            }
        }
        
        return biggestProduct;
    }
    
    public static void test(double product, int... factors) {
        System.out.println(factors[0]);
        System.out.println(factors[1]);
        System.out.println(factors[2]);
        System.out.println(factors[3]);
        System.out.println(product);
        System.out.println("BREAK");
        System.out.println("");
    }
    
    public static double max(double... comparands) {
        double biggestNumber = 0;
        
        for(double number : comparands) {
            if(number > biggestNumber){
                biggestNumber = number;
            }
        }
    
        return biggestNumber;
    }
    
    
    
    
    public static void runTests() {
        if(test_max()) {
            System.out.println("max function works");
        }
        
    }
    
    public static boolean test_max() {
        return max(20, 3, 543, 27.0, 27.1) == 543;
    }
}