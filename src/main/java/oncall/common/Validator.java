package oncall.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    public static final int MINIMUM_WORKER = 5;
    public static final int MAXIMUM_NAME_LENGTH = 5;

    public static boolean validateMonthAndDay(String[] input) {
        if (input == null || input.length != 2) {
            return false;
        }

        try {
            int month = Integer.parseInt(input[0]);
            if (month < 1 || month > 12) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        Set<String> days = Set.of("월", "화", "수", "목", "금", "토", "일");
        return days.contains(input[1]);
    }

    public static boolean validateSchedule(List<String> weekdayWorkers, List<String> holidayWorkers) {
        Set<String> uniqueWeekdayWorkers = new HashSet<>(weekdayWorkers);
        Set<String> uniqueHolidayWorkers = new HashSet<>(holidayWorkers);

        if (uniqueWeekdayWorkers.size() != weekdayWorkers.size()) {
            return false;
        }

        if (uniqueHolidayWorkers.size() != holidayWorkers.size()) {
            return false;
        }

        if (!uniqueWeekdayWorkers.equals(uniqueHolidayWorkers)) {
            return false;
        }

        if (weekdayWorkers.stream().anyMatch(worker -> worker.length() >= MAXIMUM_NAME_LENGTH)) {
            return false;
        }

        if (holidayWorkers.stream().anyMatch(worker -> worker.length() >= MAXIMUM_NAME_LENGTH)) {
            return false;
        }

        if (weekdayWorkers.size() < MINIMUM_WORKER) {
            return false;
        }

        if (holidayWorkers.size() < MINIMUM_WORKER) {
            return false;
        }

        return true;
    }
}
