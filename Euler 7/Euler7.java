//import java.util.*;
//
//boolean isPrime(long value) {
//    for(int i = 2; i <= value/2; i++) {
//        if(value % i != 0) {
//            return false;
//        }
//    }
//
//    return true;
//}
//isPrime(5)
//boolean isPrime(long value) {
//    for(int i = 2; i <= value/2; i++) {
//        if(value % i == 0) {
//            return false;
//        }
//    }
//
//    return true;
//}
//isPrime(5)
//isPrime(37))
//isPrime(37)
//isPrime(64)
//List<Long> listPrimesUpToIndex(int highestIndex) {
//    List<Long> primes = new ArrayList<>();
//
//    for(int i = 2; primes.size() == highestIndex; i++) {
//        if(isPrime(i)) {
//            primes.add(i);
//        }
//    }
//
//    return primes;
//}
//List<Long> listPrimesUpToIndex(int highestIndex) {
//    List<Long> primes = new ArrayList<>();
//
//    for(long i = 2; primes.size() == highestIndex; i++) {
//        if(isPrime(i)) {
//            primes.add(i);
//        }
//    }
//
//    return primes;
//}
//listPrimesUpToIndex(6)
//List<Long> listPrimesUpToIndex(int highestIndex) {
//    List<Long> primes = new ArrayList<>();
//
//    for(long i = 2; primes.size() <= highestIndex; i++) {
//        if(isPrime(i)) {
//            primes.add(i);
//        }
//    }
//
//    return primes;
//}
//listPrimesUpToIndex(6)
//List<Long> listPrimesUpToIndex(int highestIndex) {
//    List<Long> primes = new ArrayList<>();
//
//    for(long i = 2; primes.size() < highestIndex; i++) {
//        if(isPrime(i)) {
//            primes.add(i);
//        }
//    }
//
//    return primes;
//}
//listPrimesUpToIndex(6)
//listPrimesUpToIndex(6).get(10,000)
//listPrimesUpToIndex(6).get(10000)
//listPrimesUpToIndex(10001).get(10000)
