package racingcar.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {
    private static final String ALPHA_REGEX = "^[A-Za-z]+$";

    public void validateNotBlank(String input, String message) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
    }

    public void validateRange(int input, int min, int max, String message) {
        if (input < min || input > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public void validateRange(int input, int min, int max) {
        if (input < min || input > max) {
            throw new IllegalArgumentException("입력값이 범위를 벗어났습니다.");
        }
    }

    public void validateAlphabetic(String input, String message) {
        Pattern alphaPattern = Pattern.compile(ALPHA_REGEX);
        if (input == null || !alphaPattern.matcher(input).matches()) {
            throw new IllegalArgumentException(message);
        }
    }

    public void validateAlphabetic(String input) {
        Pattern alphaPattern = Pattern.compile(ALPHA_REGEX);
        if (input == null || !alphaPattern.matcher(input).matches()) {
            throw new IllegalArgumentException("입력값은 영문자로만 이루어져야 합니다.");
        }
    }

    public void validateLength(String input, int min, int max, String message) {
        if (input.length() < min || input.length() > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public void validateLength(String input, int min, int max) {
        if (input == null || input.length() < min || input.length() > max) {
            throw new IllegalArgumentException("입력값의 길이가 범위를 벗어났습니다.");
        }
    }

    public void validateUniqueNames(String[] names) {
        Set<String> uniqueNames = Arrays.stream(names)
                .collect(Collectors.toSet());

        if (names.length != uniqueNames.size()) {
            throw new IllegalArgumentException("입력값은 중복을 허용하지 않습니다.");
        }
    }
}

