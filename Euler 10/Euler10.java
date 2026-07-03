//boolean isPrime(long value) {
//    for(int factor = 2; factor <= value/2; factor++) {
//        if(value % factor == 0) {
//            return false;
//        }
//    }
//
//    return true;
//}
//long findPrimeSum(long upperLimit) {
//    long sum = 0;
//
//    for(long value; value <= upperLimit; value++) {
//        if(isPrime(value)) {
//            sum += value;
//        }
//    }
//
//    return sum;
//}
//long findPrimeSum(long upperLimit) {
//    long sum = 0;
//
//    for(long value = 2; value <= upperLimit; value++) {
//        if(isPrime(value)) {
//            sum += value;
//        }
//    }
//
//    return sum;
//}
//boolean isPrime(long value) {
//    for(int factor = 2; factor <= value/2; factor++) {
//        if(value % factor == 0 && value != 2) {
//            return false;
//        }
//    }
//
//    return true;
//}
//findPrimeSum(2_000_000)
