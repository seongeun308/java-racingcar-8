package racingcar;

import racingcar.app.CarApp;
import racingcar.parser.InputParser;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputValidator inputValidator = new InputValidator();
        InputParser inputParser = new InputParser();
        OutputView outputView = new OutputView();

        CarApp carApp = new CarApp(inputView, inputValidator, inputParser, outputView);
        carApp.run();
    }
}
