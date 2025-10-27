package racingcar.app;

import racingcar.domain.Car;
import racingcar.parser.InputParser;
import racingcar.rule.CarGameRule;
import racingcar.util.CarUtils;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class CarGameApp {

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final InputParser inputParser;
    private final OutputView outputView;

    public CarGameApp(
            InputView inputView,
            InputValidator inputValidator,
            InputParser inputParser,
            OutputView outputView) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
        this.outputView = outputView;
    }

    public void run() {
        String[] names = readAndParseNames();
        int round = readAndParseRound();

        List<Car> cars = generateCars(names);
        playGame(cars, round);

        List<Car> winners = determineWinners(cars);
        outputView.printWinner(winners);
    }

    private List<Car> determineWinners(List<Car> cars) {
        int maxDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .toList();
    }

    private void playGame(List<Car> cars, int round) {
        for (int i = 1; i <= round; i++) {
            outputView.printRound(i);
            cars.forEach(car -> {
                int number = CarUtils.generateNumber();
                if (number >= CarGameRule.MIN_MOVE_NUMBER.getValue()) {
                    car.move();
                }
                outputView.printMove(car.getName(), car.getDistance());
            });
        }
    }

    private int readAndParseRound() {
        String roundInput = inputView.readLine("시도할 횟수는 몇 회인가요?");
        inputValidator.validateNotBlank(roundInput);
        int round = inputParser.parseRound(roundInput);
        inputValidator.validateRange(round, CarGameRule.MIN_ROUND.getValue(), CarGameRule.MAX_ROUND.getValue());
        return round;
    }

    private String[] readAndParseNames() {
        String namesInput = inputView.readLine("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        inputValidator.validateNotBlank(namesInput);
        String[] names = inputParser.parseNames(namesInput);

        inputValidator.validateRange(names.length, CarGameRule.MIN_SIZE.getValue(), CarGameRule.MAX_SIZE.getValue());
        validateLengthNames(names);
        inputValidator.validateUniqueNames(names);
        return names;
    }

    private List<Car> generateCars(String[] names) {
        return Arrays.stream(names)
                .map(Car::new)
                .toList();
    }

    private void validateLengthNames(String[] names) {
        Arrays.stream(names)
                .forEach(input -> inputValidator.validateLength(input, CarGameRule.MIN_LENGTH.getValue(), CarGameRule.MAX_LENGTH.getValue()));
    }
}
