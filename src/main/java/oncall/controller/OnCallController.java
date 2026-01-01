package oncall.controller;

import java.util.List;
import oncall.service.OnCallSchedule;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OnCallMonthAndDay;
import oncall.view.OnCallScheduleOrder;
import oncall.view.OutputView;

public class OnCallController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OnCallService onCallService;

    public OnCallController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.onCallService = new OnCallService();
    }

    public void run() {
        OnCallMonthAndDay monthAndDay = inputView.readMonthAndDay();
        OnCallScheduleOrder order = inputView.readSchedule();

        List<OnCallSchedule> schedules =
                onCallService.calculateOnCall(monthAndDay.month(), monthAndDay.day(), order);

        outputView.printSchedule(schedules);
    }
}
