package hw1.MuseumVisitors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    private static final String REGEX_RES_INTERVAL = " ";
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final String PATTERN_FOR_TIME = "kk:mm";
    private static final ArrayList<Calendar> enterTimeArray = new ArrayList<>();
    private static final ArrayList<Calendar> exitTimeArray = new ArrayList<>();
    private static final ArrayList<Boolean> checkedTimeArray = new ArrayList<>();

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String inputInterval;
        String[] splitTime;
        SimpleDateFormat timeFormat = new SimpleDateFormat(PATTERN_FOR_TIME);
        boolean firstVisitorMark = false;
        Calendar enterResult = new GregorianCalendar();
        Calendar exitResult = new GregorianCalendar();
        int currVisitors;
        int maxVisitors = 1;

        // Input intervals and split them then put in ArrayLists
        while (scanner.hasNextLine()) {
            Calendar enterTime = new GregorianCalendar();
            Calendar exitTime = new GregorianCalendar();
            String buffer = scanner.nextLine();
            if (buffer.equals("")) {
                break;
            }
            inputInterval = buffer;
            splitTime = inputInterval.split(REGEX_RES_INTERVAL);

            enterTime.setTime(timeFormat.parse(splitTime[FIRST_ARRAY_ELEMENT]));
            exitTime.setTime(timeFormat.parse(splitTime[SECOND_ARRAY_ELEMENT]));

            if (enterTimeArray.contains(enterTime) && exitTimeArray.contains(exitTime)) {
                checkedTimeArray.add(true);
            } else {
                checkedTimeArray.add(false);
            }

            enterTimeArray.add(enterTime);
            exitTimeArray.add(exitTime);

            if (!firstVisitorMark) {
                enterResult = enterTime;
                exitResult = exitTime;
                firstVisitorMark = true;
            }
        }

        // Searching max visitors for intervals
        int index = 0;
        for (Calendar enterKey1 : enterTimeArray) {
            for (Calendar enterKey2 : enterTimeArray) {
                currVisitors = 0;
                for (Calendar enterKey3 : enterTimeArray) {
                    if (enterKey1.after(exitTimeArray.get(enterTimeArray.indexOf(enterKey2))) || enterKey1.equals(exitTimeArray.get(enterTimeArray.indexOf(enterKey2)))) {
                        break;
                    }
                    if (checkedTimeArray.get(index)) {
                        break;
                    }

                    if ((enterKey3.before(enterKey1) && exitTimeArray.get(enterTimeArray.indexOf(enterKey3)).after(exitTimeArray.get(enterTimeArray.indexOf(enterKey2)))) || enterKey3.equals(enterKey1) && exitTimeArray.get(enterTimeArray.indexOf(enterKey3)).equals(exitTimeArray.get(enterTimeArray.indexOf(enterKey2))) || (enterKey3.before(enterKey1) && exitTimeArray.get(enterTimeArray.indexOf(enterKey3)).equals(exitTimeArray.get(enterTimeArray.indexOf(enterKey2)))) || (enterKey3.equals(enterKey1) && exitTimeArray.get(enterTimeArray.indexOf(enterKey3)).after(exitTimeArray.get(enterTimeArray.indexOf(enterKey2))))) {
                        currVisitors++;
                    }
                    if (maxVisitors < currVisitors) {
                        maxVisitors = currVisitors;
                        enterResult = enterKey1;
                        exitResult = exitTimeArray.get(enterTimeArray.indexOf(enterKey2));
                    }
                }
            }
            index++;
        }

        // Print answer the question
        System.out.println(timeFormat.format(enterResult.getTime()) + " " + timeFormat.format(exitResult.getTime()));
    }
}
