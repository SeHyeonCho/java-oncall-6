package oncall.view;

import static oncall.common.Validator.validateMonthAndDay;
import static oncall.common.Validator.validateSchedule;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    public OnCallMonthAndDay readMonthAndDay() {
        while (true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] input = Console.readLine().split(",");

            if (validateMonthAndDay(input)) {
                return new OnCallMonthAndDay(Integer.parseInt(input[0]), input[1]);
            }

            System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public OnCallScheduleOrder readSchedule() {
        while (true) {
            System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
            List<String> weekdayWorkers = Arrays.stream(Console.readLine().split(",")).toList();

            System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
            List<String> holidayWorkers = Arrays.stream(Console.readLine().split(",")).toList();

            if (validateSchedule(weekdayWorkers, holidayWorkers)) {
                return new OnCallScheduleOrder(weekdayWorkers, holidayWorkers);
            }

            System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }



}
