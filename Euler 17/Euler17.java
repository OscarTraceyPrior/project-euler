public class Euler17 {

    
    public static void main(String[] args) {
        
        System.out.println("10 as words: " + convertToWord(10L));
        System.out.println("78 as words: " + convertToWord(78L));
        System.out.println("493 as words: " + convertToWord(493L));
        System.out.println("1000 as words: " + convertToWord(1000L));
        //System.out.println("An error: " + convertToWord(1001L));
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        long total = 0;
        
        for(Long i = 1L; i <= 1000L; i++) {
            System.out.println(i + ": " + convertToWord(i));
            total += convertToWord(i).length();
        }
        
        System.out.println("Total chars of worded numbers up to 1000: " + total);
    }
    
    
    
    
    public static String convertToWord(Long someNumber) {
        if (someNumber > 1000) {
            throw new IllegalArgumentException("I never agreed to any more than this");
        } else if (someNumber == 1000) {
            return "ONETHOUSAND";
        } else {
            String asString = "";
            boolean originalAbove100 = false;
            Long shrinkingTotal = someNumber;
            
            if (shrinkingTotal >= 100) {
                Long hundreds = Long.parseLong(String.valueOf((shrinkingTotal + "").charAt(0)));
                asString += Digit.getDigit(hundreds) + "HUNDRED";
                shrinkingTotal -= hundreds * 100L;
                originalAbove100 = true;
            } 
            
            if (shrinkingTotal >= 20) {
                Integer tens = Integer.parseInt(String.valueOf((shrinkingTotal + "").charAt(0)));
                if(!asString.isEmpty()) {
                    asString += "AND";
                }
                asString += Tens.getTens(tens * 10L);
                shrinkingTotal -= tens * 10L;
            }
            
            if (shrinkingTotal >= 10) {
                if(!asString.isEmpty() && !asString.contains("AND") && originalAbove100) {
                    asString += "AND";
                }
                asString += Teen.getTeen(shrinkingTotal);
                shrinkingTotal -= shrinkingTotal;
            } else if (shrinkingTotal > 0) {
                if(!asString.isEmpty() && !asString.contains("AND") && originalAbove100) {
                    asString += "AND";
                }
                asString += Digit.getDigit(shrinkingTotal);
                shrinkingTotal -= shrinkingTotal;
            }
            
            return asString;
        }
    }
    
    
    private enum Digit {
        
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9);
        
        int value;
        
        private Digit(int value) {
            this.value = value;
        }
        
        public static String getDigit(Long value) {
            for(Digit digit : Digit.values()) {
                if (digit.getValue() == value) {
                    return digit.name();
                }
            }
            
            throw new IllegalArgumentException("not valid: " + value);
        }
        
        public int getValue() {
            return value;
        }
        
    }
    
    private enum Teen {
        
        TEN(10),
        ELEVEN(11),
        TWELVE(12),
        THIRTEEN(13),
        FOURTEEN(14),
        FIFTEEN(15),
        SIXTEEN(16),
        SEVENTEEN(17),
        EIGHTEEN(18),
        NINETEEN(19);
        
        int value;
        
        private Teen(int value) {
            this.value = value;
        }
        
        public static String getTeen(Long value) {
            for(Teen teen : Teen.values()) {
                if (teen.getValue() == value) {
                    return teen.name();
                }
            }
            
            throw new IllegalArgumentException("not valid" + value);
        }
        
        public int getValue() {
            return value;
        }
        
    }
    
    private enum Tens {
        
        TWENTY(20),
        THIRTY(30),
        FORTY(40),
        FIFTY(50),
        SIXTY(60),
        SEVENTY(70),
        EIGHTY(80),
        NINETY(90);
        
        int value;
        
        private Tens(int value) {
            this.value = value;
        }
        
        public static String getTens(Long value) {
            for(Tens tens : Tens.values()) {
                if (tens.getValue() == value) {
                    return tens.name();
                }
            }
            
            throw new IllegalArgumentException("not valid" + value);
        }
        
        public int getValue() {
            return value;
        }
    }
}

