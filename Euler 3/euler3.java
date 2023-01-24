import java.io.*;
import java.math.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.prefs.*;
import java.util.regex.*;
import java.util.stream.*;
int productOfList(List<Integer> factors){
    int product = 1;
    for(Integer factor : factors) {
        product *= factor;
    }
    return product;
}
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    while(isNotPrime(value)) {
        for(int possibleFactor = 2; possibleFactor =< value/2; possibleFactor++)) {
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(isNotPrime(value)) {
        for(int possibleFactor = 2; possibleFactor =< value/2; possibleFactor++) {
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(isNotPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
boolean isNotPrime(int value) {
    for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
        if(value % possibleFactor == 0) {
            return false;
        }
    }
    return true;
}
getPrimeFactors(10)
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(isNotPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(isNotPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
getPrimeFactors(10);
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(!isPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
boolean isPrime(int value) {
    for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
        if(value % possibleFactor == 0) {
            return false;
        }
    }
    return true;
}
getPrimeFactors(10);
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(!isPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    
    return primeFactors;
}
List<Integer> getPrimeFactors(int value) {
    List<Integer> primeFactors = new ArrayList<Integer>();
    
    while(!isPrime(value)) {
        for(int possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    primeFactors.add(value);
    
    return primeFactors;
}
getPrimeFactors(10)
getPrimeFactors(100)
getPrimeFactors(169)
getPrimeFactors(259)
getPrimeFactors(600851475143)
List<Long> getPrimeFactors(long value) {
    List<Long> primeFactors = new ArrayList<>();
    
    while(!isPrime(value)) {
        for(long possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    primeFactors.add(value);
    
    return primeFactors;
}
boolean isPrime(long value) {
    for(long possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
        if(value % possibleFactor == 0) {
            return false;
        }
    }
    return true;
}
List<Long> getPrimeFactors(long value) {
    List<Long> primeFactors = new ArrayList<>();
    
    while(!isPrime(value)) {
        for(long possibleFactor = 2; possibleFactor <= value/2; possibleFactor++) {
            System.out.println(value % possibleFactor);
            if(value % possibleFactor == 0) {
                primeFactors.add(possibleFactor);
                value /= possibleFactor;
                break;
            }
        }
    }
    primeFactors.add(value);
    
    return primeFactors;
}
getPrimeFactors(600851475143)
getPrimeFactors(600851475143L)