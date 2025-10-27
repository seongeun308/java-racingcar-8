package racingcar.app;

import racingcar.domain.Car;
import racingcar.parser.InputParser;
import racingcar.util.CarUtils;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class CarApp {

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final InputParser inputParser;
    private final OutputView outputView;

    public CarApp(InputView inputView, InputValidator inputValidator, InputParser inputParser, OutputView outputView) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
        this.outputView = outputView;
    }

    public void run() {
        String namesInput = inputView.readLine("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String roundInput = inputView.readLine("시도할 횟수는 몇 회인가요?");

        inputValidator.validateNotBlank(namesInput);
        String[] names = inputParser.parseNames(namesInput);

        inputValidator.validateRange(names.length, 1, 5);
        validateLengthNames(names);
        inputValidator.validateUniqueNames(names);

        inputValidator.validateNotBlank(roundInput);
        int round = inputParser.parseRound(roundInput);

        inputValidator.validateRange(round, 1, 10);

        List<Car> cars = generateCars(names);

        for (int i = 1; i <= round; i++) {
            outputView.printRound(i);
            cars.forEach(car -> {
                int step = CarUtils.generateStep();
                car.move(step);
                outputView.printMove(car.getName(), car.getDistance());
            });
        }

        int maxDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        List<Car> winners = cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .toList();

        outputView.printWinner(winners);
    }

    private static List<Car> generateCars(String[] names) {
        return Arrays.stream(names)
                .map(Car::new)
                .toList();
    }

    private void validateLengthNames(String[] names) {
        Arrays.stream(names)
                .forEach(input -> inputValidator.validateLength(input, 1, 10));
    }
}
