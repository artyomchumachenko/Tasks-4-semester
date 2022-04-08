package hw1.MuseumVisitors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final String REGEX_RES_INTERVAL = " ";
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final String PATTERN_FOR_TIME = "hh:mm";

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        HashMap<Calendar, Calendar> timeMap = new HashMap<>();
        Calendar enterTime = new GregorianCalendar();
        Calendar exitTime = new GregorianCalendar();
        String inputTimes;
        String[] time;
        Calendar enterResult = new GregorianCalendar();
        Calendar exitResult = new GregorianCalendar();
        SimpleDateFormat timeFormat = new SimpleDateFormat(PATTERN_FOR_TIME);
        boolean mark = false;

        while (scanner.hasNextLine()) {
            String buffer = scanner.nextLine();
            if (buffer.equals("")) {
                break;
            }
            inputTimes = buffer;
            time = inputTimes.split(REGEX_RES_INTERVAL);

            enterTime.setTime(timeFormat.parse(time[FIRST_ARRAY_ELEMENT]));
            exitTime.setTime(timeFormat.parse(time[SECOND_ARRAY_ELEMENT]));
            if (!mark) {
                enterResult = enterTime;
                exitResult = exitTime;
                mark = true;
            }
            timeMap.put(enterTime, exitTime);
        }

        for (Calendar enterTimeKey : timeMap.keySet()) {
            if (exitResult.after(timeMap.get(enterTimeKey))) {
                exitResult = timeMap.get(enterTimeKey);
            }
            if (enterResult.before(enterTimeKey) && enterTimeKey.before(exitResult)) {
                enterResult = enterTimeKey;
            }
        }

        System.out.println(timeFormat.format(enterResult.getTime()) + REGEX_RES_INTERVAL + timeFormat.format(exitResult.getTime()));
    }
}
