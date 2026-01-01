package oncall.view;

import java.util.List;

public record OnCallSchedule(
        List<String> weekday,
        List<String> holiday
) {
}
