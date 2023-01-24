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
int pythagTripletProduct(int goalSum) {
    for(int a = 1; a < 987; a++) {
        for(int b = 1; b < 991; b++) {
            int c = Math.sqrt((b*b) + (a*a));
            
            if(a + b + c = 1000) {
                return a*b*c;
            }
        }
    }

    return -1;
}
int pythagTripletProduct(int goalSum) {
    for(double a = 1; a < 987; a++) {
        for(double b = 1; b < 991; b++) {
            int c = Math.sqrt((b*b) + (a*a));
            
            if(a + b + c == 1000) {
                return a*b*c;
            }
        }
    }

    return -1;
}
double pythagTripletProduct(int goalSum) {
    for(double a = 1; a < 987; a++) {
        for(double b = 1; b < 991; b++) {
            double c = Math.sqrt((b*b) + (a*a));
            
            if(a + b + c == 1000) {
                return a*b*c;
            }
        }
    }

    return -1;
}
double pythagTripletProduct(int goalSum) {
    for(double a = 1; a < goalSum - 13; a++) {
        for(double b = 1; b < goalSum - 9; b++) {
            double c = Math.sqrt((b*b) + (a*a));
            
            if(a + b + c == goalSum) {
                return a*b*c;
            }
        }
    }

    return -1;
}
pythagTripletProduct(1000)