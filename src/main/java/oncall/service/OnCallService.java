package oncall.service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import oncall.view.OnCallScheduleOrder;

public class OnCallService {
    private final OnCallScheduleOrder onCallScheduleOrder;
    private final List<List<Integer>> holidays;

    public OnCallService(OnCallScheduleOrder onCallScheduleOrder) {
        this.onCallScheduleOrder = onCallScheduleOrder;
        holidays = new ArrayList<>();
        init();
    }

    public List<OnCallSchedule> calculateOnCall(int month, String startDay) {
        List<OnCallSchedule> schedules = new ArrayList<>();
        List<String> days = List.of("월", "화", "수", "목", "금", "토", "일");

        int dayIdx = days.indexOf(startDay);
        int endDate = YearMonth.of(LocalDateTime.now().getYear(), month).lengthOfMonth();
        int date = 1;

        int holidayIdx = 0;
        int weekdayIdx = 0;

        String prevWorker = null;
        String holidaySwap = null;
        String weekdaySwap = null;

        while (date <= endDate) {
            String day = days.get((date + dayIdx - 1) % 7);
            boolean isHoliday = false;
            String worker;

            if (day.equals("토") || day.equals("일") || holidays.get(month).contains(date)) {
                isHoliday = true;
            }

            if (isHoliday) {
                worker = onCallScheduleOrder.holiday().get(holidayIdx);
                if (holidaySwap != null) {
                    worker = holidaySwap;
                    holidaySwap = null;
                }
                if (!worker.equals(prevWorker)) {
                    holidaySwap = worker;
                    worker = onCallScheduleOrder.holiday().get(holidayIdx + 1);
                    prevWorker = worker;
                    holidayIdx++;
                }
            }

            worker = onCallScheduleOrder.weekday().get(weekdayIdx);
            if (weekdaySwap != null) {
                worker = weekdaySwap;
                weekdaySwap = null;
            }
            if (!worker.equals(prevWorker)) {
                weekdaySwap = worker;
                worker = onCallScheduleOrder.weekday().get(weekdayIdx + 1);
                prevWorker = worker;
                weekdayIdx++;
            }

            schedules.add(new OnCallSchedule(month, date, day, isHoliday, worker));
        }

        return schedules;
    }

    private void init() {
        for (int i = 0; i <= 12; i++) {
            holidays.add(new ArrayList<>());
        }

        holidays.get(1).add(1);
        holidays.get(3).add(1);
        holidays.get(5).add(5);
        holidays.get(6).add(6);
        holidays.get(8).add(15);
        holidays.get(10).add(3);
        holidays.get(10).add(9);
        holidays.get(12).add(25);
    }
}
