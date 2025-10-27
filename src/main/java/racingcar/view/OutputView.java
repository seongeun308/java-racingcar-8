package racingcar.view;

public class OutputView {
    public void printRound(int round) {
        System.out.println("Round " + round);
    }

    public void printMove(String name, int distance) {
        System.out.println(name + " : " + "-".repeat(distance));
    }
}
