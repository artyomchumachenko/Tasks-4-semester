package hw1.MuseumVisitors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private static final String REGEX_RES_INTERVAL = " ";
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final String PATTERN_FOR_TIME = "kk:mm";
    private static Map<Calendar, Calendar> timeMap = new TreeMap<>();

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String inputTimes;
        String[] time;
        SimpleDateFormat timeFormat = new SimpleDateFormat(PATTERN_FOR_TIME);
        boolean mark = false;
        Calendar enterResult = new GregorianCalendar();
        Calendar exitResult = new GregorianCalendar();

        while (scanner.hasNextLine()) {
            Calendar enterTime = new GregorianCalendar();
            Calendar exitTime = new GregorianCalendar();
            String buffer = scanner.nextLine();
            if (buffer.equals("")) {
                break;
            }
            inputTimes = buffer;
            time = inputTimes.split(REGEX_RES_INTERVAL);

            enterTime.setTime(timeFormat.parse(time[FIRST_ARRAY_ELEMENT]));
            exitTime.setTime(timeFormat.parse(time[SECOND_ARRAY_ELEMENT]));
            timeMap.put(enterTime, exitTime);
        }

        int maxVisitors = 1;
        int currVisitors = 1;
        Calendar enterBuffer = new GregorianCalendar();
        Calendar exitBuffer = new GregorianCalendar();
        for (Calendar enter : timeMap.keySet()) {
            System.out.println(timeFormat.format(enter.getTime()) + "   " + timeFormat.format(timeMap.get(enter).getTime()));

            if (!mark) {
                enterResult = enter;
                exitResult = timeMap.get(enter);
                enterBuffer = enterResult;
                exitBuffer = exitResult;
                mark = true;
            }

            currVisitors++;
            if (enter.before(exitBuffer) && timeMap.get(enter).before(exitBuffer)) {
                currVisitors++;
            } else {
                currVisitors--;
            }
        }
        System.out.println(currVisitors);
    }
}
