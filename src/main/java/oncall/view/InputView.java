package oncall.view;

import static oncall.common.Validator.validateMonthAndDay;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public OnCallMonthAndDay readMonthAndDay() {
        while (true) {
            System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
            String[] input = Console.readLine().split(",");

            if (validateMonthAndDay(input)) {
                return new OnCallMonthAndDay(Integer.parseInt(input[0]), input[1]);
            }
        }
    }



}
