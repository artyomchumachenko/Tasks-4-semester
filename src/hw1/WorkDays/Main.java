package hw1.WorkDays;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    private static final String REGEX_FOR_DATE = "(0?[1-9]|[12][0-9]|3[01])[\\/\\-\\.](0?[1-9]|1[012])[ \\/\\.\\-](\\d{4})";
    private static final String PATTERN_FOR_DATE = "d.M.yyyy";

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
            lastDate.add(Calendar.MONTH, 1);

            do {
                System.out.println(dateFormat.format(firstCalendar.getTime()));
                firstCalendar.add(Calendar.DATE, 4);
                if (firstCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    firstCalendar.add(Calendar.DATE, 1);
                }
            } while (!firstCalendar.after(lastDate));
        } else {
            throw new Exception("Date no correct");
        }
    }
}
