package racingcar;

import racingcar.domain.Car;
import racingcar.parser.InputParser;
import racingcar.util.CarUtils;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String namesInput = inputView.readLine("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String roundInput = inputView.readLine("시도할 횟수는 몇 회인가요?");

        InputValidator inputValidator = new InputValidator();
        InputParser inputParser = new InputParser();

        inputValidator.validateNotBlank(namesInput);

        String[] names = inputParser.parseNames(namesInput);

        inputValidator.validateRange(names.length, 1, 5);
        Arrays.stream(names)
                .forEach(input -> inputValidator.validateLength(input, 1, 10));
        inputValidator.validateUniqueNames(names);

        inputValidator.validateNotBlank(roundInput);

        int round = inputParser.parseRound(roundInput);

        inputValidator.validateRange(round, 1, 10);

        List<Car> cars = Arrays.stream(names)
                .map(Car::new)
                .toList();

        cars.forEach(car -> {
            int step = CarUtils.generateStep();
            car.move(step);
        });
    }

}
