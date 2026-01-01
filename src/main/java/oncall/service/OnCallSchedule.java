package oncall.service;

public record OnCallSchedule(
        int month,
        int date,
        String day,
        boolean isHoliday,
        String worker
) {
}
