package racingcar.view;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public void printRound(int round) {
        System.out.println("Round " + round);
    }

    public void printMove(String name, int distance) {
        System.out.println(name + " : " + "-".repeat(distance));
    }

    public void printWinner(List<Car> winners) {
        if (winners.size() == 1) {
            System.out.println("최종 우승자 : " + winners.getFirst().getName());
        } else {
            String result = winners.stream()
                    .map(Car::getName)
                    .collect(Collectors.joining(", "));
            System.out.println("최종 우승자 : " + result);
        }
    }
}
