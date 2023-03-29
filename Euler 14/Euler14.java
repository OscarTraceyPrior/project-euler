public class Euler14 { 
    
    public static void main(String args[]) {
        System.out.println("Result: " + findLongestChainWithInitialUnder(1000000));
        
        //TODO: This would be quicker with dynamic programming, took about 5-10 minutes to compute.
    }
    

    private static long findLongestChainWithInitialUnder(long initial) {
        long longestChain = 0;
        long numberWithLongestChain = 1;
        
        for(int i = 1; i < initial; i++) {
            long chainForInitialI = findCollatzChain(i);
            
            if(chainForInitialI > longestChain) {
                longestChain = chainForInitialI;
                numberWithLongestChain = i;
            }
        }
        
        return numberWithLongestChain;
    }
    
    private static long findCollatzChain(long initial) {
        long count = 1;
        System.out.println(initial);
        
        if(initial != 1) {
            if(initial % 2 == 0) {
                count += findCollatzChain(initial/2);
            } else {
                count += findCollatzChain((3*initial) + 1);
            }
        }
        
        return count;
    }

}