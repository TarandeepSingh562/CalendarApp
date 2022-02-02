package CalendarApp;


import java.lang.Exception;
import java.util.*;
import java.util.Calendar;
import java.time.*;

public class CalendarView {
    static String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    static String days[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };


    public static final String RED = "\033[0;31m";
    public static final String WHITE = "\033[0m";

    void displayCalendarMonthly(String cID) {

        String header = "Su Mo Tu We Th Fr Sa";

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the year: ");
        if (!scan.hasNextInt()) {
            System.out.println("Invalid input -- try number next time");
        }
        int year = scan.nextInt();
        int month;

        while (true) {
            try {
                System.out.println("Enter the month(1-12): ");
                month = scan.nextInt();
                if (month <= 0 || month > 12) {
                    throw new Exception();
                } else {
                    break;
                }

            }

            catch (Exception e) {
                System.out.println("Input a valid number");

            }
        }
        month -= 1;

        Calendar myCalendar = new GregorianCalendar();
        int era = myCalendar.get(Calendar.ERA);
        int current_day = myCalendar.get(Calendar.DATE);
        int current_month = myCalendar.get(Calendar.MONTH);
        int current_year = myCalendar.get(Calendar.YEAR);

        GregorianCalendar GCalendar = new GregorianCalendar(year, month, 1);
        int numDays = GCalendar.getActualMaximum(Calendar.DATE);
        int startofweek = GCalendar.get(Calendar.DAY_OF_WEEK);

        GCalendar = new GregorianCalendar(year, month, numDays);
        int weeks = GCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        System.out.println(" ");
        System.out.println(header);

        int count = 1;
        for (int i = 1; i <= weeks; i++) {
            System.out.println();
            for (int j = 1; j <= days.length; j++) {
                int checkcurrent = (count - startofweek + 1);

                if (count < startofweek || checkcurrent > numDays) {
                    System.out.print("--" + " ");

                } else {
                    if (current_day == checkcurrent && current_month == month && current_year == year) {
                        System.out.print(RED + getDay((checkcurrent)) + WHITE + " ");

                    } else {
                        System.out.print(getDay((checkcurrent)) + " ");
                    }
                }
                count++;

            }
        }

    }


    public static String getDay(int i) {
        String date = Integer.toString(i);
        if (date.length() == 1) {
            date = " " + date;
            return date;
        }
        return date;
    }
}
