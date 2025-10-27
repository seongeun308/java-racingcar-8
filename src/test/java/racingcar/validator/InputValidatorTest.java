package racingcar.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 10;
    private static final int LENGTH_MIN = 1;
    private static final int LENGTH_MAX = 5;

    private final InputValidator validator = new InputValidator();

    @Test
    void 입력값이_null_빈_값_공백이면_IllegalArgumentException_발생() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateNotBlank(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateNotBlank("")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateNotBlank(" "))
        );
    }

    @Test
    void 입력값이_한_개_이상의_문자열이라면_검증_성공() {
        assertDoesNotThrow(() -> validator.validateNotBlank("a"));
    }

    @Test
    void 입력값이_범위를_벗어나면_IllegalArgumentException_발생() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateRange(0, RANGE_MIN, RANGE_MAX)),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateRange(11, RANGE_MIN, RANGE_MAX))
        );
    }

    @Test
    void 입력값이_범위_내이면_검증_성공() {
        assertAll(
                () -> assertDoesNotThrow(() -> validator.validateRange(1, RANGE_MIN, RANGE_MAX)),
                () -> assertDoesNotThrow(() -> validator.validateRange(5, RANGE_MIN, RANGE_MAX)),
                () -> assertDoesNotThrow(() -> validator.validateRange(10, RANGE_MIN, RANGE_MAX))
        );
    }

    @Test
    void 입력값이_영문자가_아니면_IllegalArgumentException_발생() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic("1")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic("//")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic("")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic(" ")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic("A a")),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateAlphabetic(null))
        );
    }

    @Test
    void 입력값이_영문자로만_구성되어_있으면_검증_성공() {
        assertAll(
                () -> assertDoesNotThrow(() -> validator.validateAlphabetic("a")),
                () -> assertDoesNotThrow(() -> validator.validateAlphabetic("Aa"))
        );
    }

    @Test
    void 입력값_길이가_범위를_벗어나면_IllegalArgumentException_발생() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateLength("", LENGTH_MIN, LENGTH_MAX)),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateLength("aaaaaa", LENGTH_MIN, LENGTH_MAX)),
                () -> assertThrows(IllegalArgumentException.class, () -> validator.validateLength(null, LENGTH_MIN, LENGTH_MAX))
        );
    }

    @Test
    void 입력값_길이가_범위_내이면_검증_성공() {
        assertAll(
                () -> assertDoesNotThrow(() -> validator.validateLength("a", LENGTH_MIN, LENGTH_MAX)),
                () -> assertDoesNotThrow(() -> validator.validateLength("aaaaa", LENGTH_MIN, LENGTH_MAX))
        );
    }
}