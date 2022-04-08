package hw1.HotelReservationSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final String REGEX_CLEAR = "[\\[\\]'\"]";
    private static final String REGEX_SPLIT = ", ";
    private static final String REGEX_RES_INTERVAL = "-";
    private static final int FIRST_ARRAY_ELEMENT = 0;
    private static final int SECOND_ARRAY_ELEMENT = 1;
    private static final String PATTERN_FOR_DATE = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String inputArray;
        String interval;

        // Ввели строку с массивом
        inputArray = scanner.nextLine();
        // Отрезали символы
        inputArray = inputArray.replaceAll(REGEX_CLEAR, "");

        // Режем даты
        String[] bufferDates = inputArray.split(REGEX_SPLIT);
        HashMap<String, String> dateMap = new HashMap<>();
        for (String date : bufferDates) {
            // Если есть -
            if (date.contains(REGEX_RES_INTERVAL)) {
                String[] buffer = date.split(REGEX_RES_INTERVAL);
                dateMap.put(buffer[FIRST_ARRAY_ELEMENT], buffer[SECOND_ARRAY_ELEMENT]);
            } else {
                dateMap.put(date, "");
            }
        }

        // Ввод интервала бронирования
        interval = scanner.nextLine();

        System.out.println(bookingAvailabilityCheck(dateMap, interval));
    }

    public static boolean bookingAvailabilityCheck(HashMap<String, String> dateMap, String interval) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_FOR_DATE);
        Calendar firstCalendar = new GregorianCalendar();
        Calendar secondCalendar = new GregorianCalendar();
        boolean bookingIsPossible = true;
        boolean intervalIsInterval = false;

        String[] intervalDates = new String[SECOND_ARRAY_ELEMENT];
        if (interval.contains(REGEX_RES_INTERVAL)) {
            intervalIsInterval = true;
            intervalDates = interval.split(REGEX_RES_INTERVAL);
        }

        for (String key : dateMap.keySet()) {
            if (dateMap.get(key).equals("")) {
                firstCalendar.setTime(dateFormat.parse(key)); // дата из массива
                if (intervalIsInterval) {
                    secondCalendar.setTime(dateFormat.parse(intervalDates[FIRST_ARRAY_ELEMENT]));
                    if (!firstCalendar.before(secondCalendar)) {
                        secondCalendar.setTime(dateFormat.parse(intervalDates[SECOND_ARRAY_ELEMENT]));
                        if (!firstCalendar.after(secondCalendar)) {
                            bookingIsPossible = false;
                            break;
                        }
                    }
                } else {
                    secondCalendar.setTime(dateFormat.parse(interval));
                    if (firstCalendar.getTime().equals(secondCalendar.getTime())) {
                        bookingIsPossible = false;
                        break;
                    }
                }
            } else if (!intervalIsInterval) {
                firstCalendar.setTime(dateFormat.parse(interval)); // дата для бронирования
                secondCalendar.setTime(dateFormat.parse(key)); // первая дата из интервала из массива
                if (!firstCalendar.before(secondCalendar)) {
                    secondCalendar.setTime(dateFormat.parse(dateMap.get(key)));
                    if (!firstCalendar.after(secondCalendar)) {
                        bookingIsPossible = false;
                        break;
                    }
                }
            } else {
                firstCalendar.setTime(dateFormat.parse(intervalDates[FIRST_ARRAY_ELEMENT])); // a
                secondCalendar.setTime(dateFormat.parse(key)); // x
                Calendar thirdCalendar = new GregorianCalendar();
                thirdCalendar.setTime(dateFormat.parse(dateMap.get(key))); // y
                if (secondCalendar.before(firstCalendar) && thirdCalendar.after(firstCalendar)) {
                    bookingIsPossible = false;
                    break;
                } else {
                    firstCalendar.setTime(dateFormat.parse(intervalDates[SECOND_ARRAY_ELEMENT])); // b
                    if (secondCalendar.before(firstCalendar) && thirdCalendar.after(firstCalendar)) {
                        bookingIsPossible = false;
                        break;
                    }
                }
            }
        }
        return bookingIsPossible;
    }
}
