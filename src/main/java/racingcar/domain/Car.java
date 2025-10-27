package racingcar.domain;

import java.util.Objects;

public class Car {
    private final String name;
    private int distance;

    public Car(String name) {
        this.name = name;
    }

    public void move(int step) {
        if (step >= 4) {
            this.distance += step;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
