package oncall.common;

import java.util.Set;

public class Validator {
    public static boolean validateMonthAndDay(String[] input) {
        if (input == null || input.length != 2) {
            return false;
        }

        try {
            int month = Integer.parseInt(input[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        Set<String> days = Set.of("월", "화", "수", "목", "금", "토", "일");
        return days.contains(input[1]);
    }
}
