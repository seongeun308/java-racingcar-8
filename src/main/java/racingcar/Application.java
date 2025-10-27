package racingcar;

import racingcar.app.CarApp;
import racingcar.parser.InputParser;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputValidator inputValidator = new InputValidator();
        InputParser inputParser = new InputParser();

        CarApp carApp = new CarApp(inputView, inputValidator, inputParser);
        carApp.run();
    }
}
