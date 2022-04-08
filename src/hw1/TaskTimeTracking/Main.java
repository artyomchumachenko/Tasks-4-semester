package hw1.TaskTimeTracking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static final int ONE = 1;
    private static final int STEP = 2;
    private static final int MILLS = 1000;
    private static final int SEC_TO_MIN = 60;
    private static final int MIN_TO_HOUR = 60;
    private static final int SEC_IN_STRING_LENGTH = 3;

    private static final String REGEX = "[‘’'\\]\\[}\\{ ,;\"]";
    private static final String REGEX_SPLIT = "start:|end:";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.replaceAll(REGEX, "");
        String[] datesWithSpaceBar = input.split(REGEX_SPLIT);
        Long allTimeInMills = 0L;
        for (int i = ONE; i < datesWithSpaceBar.length - ONE; i += STEP) { // тк нулевой элемент пробел, начинаем с 1
            allTimeInMills += parseStringToDate(datesWithSpaceBar[i + ONE]).getTime() - parseStringToDate(datesWithSpaceBar[i]).getTime();
        }
        System.out.printf("%d-%02d", allTimeInMills / (MILLS * SEC_TO_MIN * MIN_TO_HOUR), allTimeInMills / (MILLS * SEC_TO_MIN) % MIN_TO_HOUR);
    }

    private static Date parseStringToDate(String strToParsing) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyyhh:mm");
            strToParsing.substring(0, strToParsing.length() - SEC_IN_STRING_LENGTH);
            return sdf.parse(strToParsing);
        } catch (ParseException e) {
            System.out.println("Date no correct");
            System.exit(ONE);
        }
        return null;
    }
}