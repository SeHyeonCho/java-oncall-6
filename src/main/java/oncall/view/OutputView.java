package oncall.view;

import java.util.List;
import oncall.service.OnCallSchedule;

public class OutputView {

    public void printSchedule(List<OnCallSchedule> schedules) {
        schedules.stream().forEach(schedule -> {
            String holidayMark = "";
            if (schedule.isHoliday()) {
                holidayMark = "(휴일)";
            }
            System.out.printf("%d월 %s일 %s %s\n", schedule.month(), schedule.date(), schedule.day() + holidayMark,
                    schedule.worker());
        });
    }
}
