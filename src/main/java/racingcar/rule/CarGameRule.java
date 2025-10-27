package racingcar.rule;

public enum CarGameRule {
    MIN_LENGTH(1),
    MAX_LENGTH(5),
    MIN_SIZE(2),
    MAX_SIZE(10),
    MIN_ROUND(1),
    MAX_ROUND(10),
    MIN_MOVE_NUMBER(4);

    private final int value;

    CarGameRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
