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
long square(long value) {
    return value*value;
}
long sumRange(long lowerBound, long upperBound) {
    long sum = 0;

    for(long i = lowerBound; i <= upperBound; i++) {
        sum += i;
    }
    
    return sum;
}
sumRange(1, 10)
square(sumRange(1, 10))
long sumRange(long lowerBound, long upperBound, Function<Long, Long> operation) {
    long sum = 0;
    
    for(long i = lowerBound; i <= upperBound; i++) {
        sum += operation.apply(i);
    }

    return sum;
}
sumRange(1, 10, square)
sumRange(1, 10, {(a) -> square(a)})
sumRange(1, 10, (a) -> square(a))
sumRange(1, 10) - sumRange(1, 10, (a) -> square(a))
square(sumRange(1, 10)) - sumRange(1, 10, (a) -> square(a))
square(sumRange(1, 100)) - sumRange(1, 100, (a) -> square(a))