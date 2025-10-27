package racingcar.parser;

public class InputParser {
    private static final String DELIMITER = ",";

    public String[] parseNames(String input) {
        return input.split(DELIMITER);
    }

    public int parseRound(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 숫자가 아닙니다.");
        }
    }
}
