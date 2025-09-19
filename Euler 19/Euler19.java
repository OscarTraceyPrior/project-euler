public class Euler19 {
    
    

    public static void main(String[] args) {
        // This is Monday 1st January, 1899
        int daysSinceStart = 1;
        int countOfSundaysIn1900 = 0;
        int countOfSundaysDuring20thCentury = 0;
        
        // year 0 = 1900
        int year = 1900;
        Month currentMonth = Month.JANUARY;
        int dayOfMonth = 1;
        
        boolean isLeapYear = isLeapYear(year);
        
        // every iteration is like moving to the next day
        while(year < 2001) {
            
            //System.out.println("The " + dayOfMonth + " of " + currentMonth.name() + ", " + (year));
            
            if(isSunday(daysSinceStart) && dayOfMonth == 1) {
                if(year > 1900) {
                    System.out.println("Sunday the " + dayOfMonth + " of " + currentMonth.name() + ", " + (year));
                    countOfSundaysDuring20thCentury++;
                } else {
                    countOfSundaysIn1900++;
                }
            }
            
            // Move to next year
            if (currentMonth == Month.DECEMBER && dayOfMonth == Month.DECEMBER.getDaysInMonth(isLeapYear)) {
                year++;
                isLeapYear = isLeapYear(year);
            }
            
            // Move to next month
            if (dayOfMonth == currentMonth.getDaysInMonth(isLeapYear)) {
                currentMonth = currentMonth.getNextMonth();
                dayOfMonth = 0;
            }
            
            daysSinceStart++;
            dayOfMonth++;
        }
        
        System.out.println("Sunday count for 20th century: " + countOfSundaysDuring20thCentury);
    }
    
    private static boolean isSunday(int daysSinceStartOfPeriod) {
        return daysSinceStartOfPeriod % 7 == 0;
    }
    
    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0); 
    }
    
    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);
        
        int daysInMonth;
        
        Month(int daysInMonth) {
            this.daysInMonth = daysInMonth;
        }
        
        public int getDaysInMonth(boolean isLeapYear) {
            if(isLeapYear && this == FEBRUARY) {
                return 29;
            }
            return daysInMonth;
        }
        
        public Month getNextMonth() {
            switch(this) {
                case JANUARY:
                    return FEBRUARY;
                case FEBRUARY:
                    return MARCH; 
                case MARCH:
                    return APRIL;
                case APRIL:
                    return MAY;
                case MAY:
                    return JUNE;
                case JUNE:
                    return JULY;
                case JULY:
                    return AUGUST;
                case AUGUST:
                    return SEPTEMBER;
                case SEPTEMBER:
                    return OCTOBER;
                case OCTOBER:
                    return NOVEMBER;
                case NOVEMBER:
                    return DECEMBER;
                case DECEMBER:
                    return JANUARY;
                default:
                    throw new RuntimeException("How did we get here?");
            }
        }
        
    }
}