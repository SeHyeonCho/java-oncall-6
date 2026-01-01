package oncall.view;

import java.util.List;

public record OnCallScheduleOrder(
        List<String> weekday,
        List<String> holiday
) {
}
