package album;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month; // 1-12 for January-December
    private int day;

    // Constants for date validation
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    // Constructor
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Check if the date is a valid calendar date
    public boolean isValid() {
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) return false;
        if (month < 1 || month > 12) return false;

        int daysInMonth;
        switch (month) {
            case 4: case 6: case 9: case 11: // April, June, September, November
                daysInMonth = 30;
                break;
            case 2: // February
                if ((year % QUADRENNIAL == 0 && year % CENTENNIAL != 0) || (year % QUATERCENTENNIAL == 0)) {
                    daysInMonth = 29; // Leap year
                } else {
                    daysInMonth = 28;
                }
                break;
            default: // January, March, May, July, August, October, December
                daysInMonth = 31;
        }

        return day > 0 && day <= daysInMonth;
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year)
            return this.year - other.year;
        if (this.month != other.month)
            return this.month - other.month;
        return this.day - other.day;
    }

    // Testbed main method
    public static void main(String[] args) {
        // Test cases for the isValid() method
        System.out.println(new Date(2020, 2, 29).isValid()); // true - Leap year test
        System.out.println(new Date(1900, 2, 29).isValid()); // false - Common year test
        System.out.println(new Date(2000, 2, 29).isValid()); // true - Century leap year test
        System.out.println(new Date(2021, 4, 31).isValid()); // false - Invalid day for April
        System.out.println(new Date(1980, 12, 31).isValid()); // true - Valid end-of-year
        System.out.println(new Date(2023, 1, 1).isValid()); // true - Valid start-of-year
    }
    public boolean isFutureDate() {
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        if (this.year > currentYear) {
            return true;
        } else if (this.year == currentYear) {
            if (this.month > currentMonth) {
                return true;
            } else if (this.month == currentMonth) {
                return this.day > currentDay;
            }
        }
        return false;
    }
    public boolean isBefore1900() {
        return this.year < 1900;
    }
    @Override
    public String toString() {
        return String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + year;
    }
}
