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
boolean isPalindrome(int value) {
String numberAsString = value + "";
}
boolean isPalindrome(int value) {
String numberAsString = value + "";
}
boolean isPalindrome(int value) {
String numberAsString = value + "";
return true:
}
boolean isPalindrome(int value) {
String numberAsString = value + "";
return true
}
boolean isPalindrome(int value) {
String numberAsString = value + "";
return true;
}
"test".get(1);
"test"[1];
"test".toCharArray();
boolean isPalindrome(int value) {
    String numberAsString = value + "";
    Char[] numberAsChars = numberAsString.toCharArray();
    for(int i; i < numbersAsChars.length; i++) {
        if(numbersAsChars[i].equals(numbersAsChars]
    return true;
boolean isPalindrome(int value) {
    String numberAsString = value + "";
    Char[] numberAsChars = numberAsString.toCharArray();
    int length = numberAsChars.length;

    for(int i; i < length; i++) {
        if(numbersAsChars[i] != numbersAsChars[length -(1 + i)]) {
            return false;
        }
    }

    return true;
}
boolean isPalindrome(int value) {
    String numberAsString = value + "";
    Char[] numberAsChars = numberAsString.toCharArray();
    int length = numberAsChars.length;

    for(int i = 0; i < length; i++) {
        if(numbersAsChars[i] != numbersAsChars[length -(1 + i)]) {
            return false;
        }
    }

    return true;
}
boolean isPalindrome(int value) {
    String numberAsString = value + "";
    char[] numberAsChars = numberAsString.toCharArray();
    int length = numberAsChars.length;

    for(int i = 0; i < length; i++) {
        if(numberAsChars[i] != numberAsChars[length -(1 + i)]) {
            return false;
        }
    }

    return true;
}
isPalindrome(999)
isPalindrome(998)
isPalindrome(1001)
isPalindrome(1234567890987654321)
isPalindrome(12345654321))
isPalindrome(123454321))
isPalindrome(123454321);
isPalindrome(123454221);
int findBiggestPalindrome(int lowerBound, int upperBound) {
    int biggestPalindrome = 1;
    
    for(int i = lowerBound; i <= upperBound; i++) {
        for(int j = i; j <= upperBound; j++) {
            if(i*j > biggestPalindrome) {
                biggestPalindrome = i*j)
int findBiggestPalindrome(int lowerBound, int upperBound) {
    int biggestPalindrome = 1;
    
    for(int i = lowerBound; i <= upperBound; i++) {
        for(int j = i; j <= upperBound; j++) {
            int product = i*j;
            if(product > biggestPalindrome && isPalindrome(product)) {
                biggestPalindrome = product;
            }
        }
    }
    
    return BiggestPalindrome;
}
int findBiggestPalindrome(int lowerBound, int upperBound) {
    int biggestPalindrome = 1;
    
    for(int i = lowerBound; i <= upperBound; i++) {
        for(int j = i; j <= upperBound; j++) {
            int product = i*j;
            if(product > biggestPalindrome && isPalindrome(product)) {
                biggestPalindrome = product;
            }
        }
    }
    
    return biggestPalindrome;
}
findBiggestPalindrome(100, 999)