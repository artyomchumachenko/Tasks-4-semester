package hw1.WorkDays;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    private static final String REGEX_FOR_DATE = "(0?[1-9]|[12][0-9]|3[01])[\\/\\-\\.](0?[1-9]|1[012])[ \\/\\.\\-](\\d{4})";
    private static final String PATTERN_FOR_DATE = "d.M.yyyy";
    private static final int PLUS_FOUR = 4;
    private static final int PLUS_ONE = 1;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String inputDate;
        inputDate = scanner.nextLine();
        if (inputDate.matches(REGEX_FOR_DATE)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_FOR_DATE);
            Calendar firstCalendar = new GregorianCalendar();
            firstCalendar.setTime(dateFormat.parse(inputDate));
            Calendar lastDate = new GregorianCalendar();
            lastDate.setTime(dateFormat.parse(inputDate));
            lastDate.add(Calendar.MONTH, PLUS_ONE);

            do {
                System.out.println(dateFormat.format(firstCalendar.getTime()));
                firstCalendar.add(Calendar.DATE, PLUS_FOUR);
                if (firstCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    firstCalendar.add(Calendar.DATE, PLUS_ONE);
                }
            } while (!firstCalendar.after(lastDate));
        } else {
            throw new Exception("Date no correct");
        }
    }
}
