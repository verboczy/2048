package ui.output;

import map.GameField;

public class ConsoleOutputHandler implements OutputHandler {

    private static ConsoleOutputHandler instance;

    private ConsoleOutputHandler() {
    }

    public static ConsoleOutputHandler getInstance() {
        if (instance == null) {
            instance = new ConsoleOutputHandler();
        }
        return instance;
    }

    public void displayField(final GameField gameField) {
        for (int row = 0; row < gameField.getSize(); row++) {
            for (int column = 0; column < gameField.getSize(); column++) {
                System.out.printf("%4d ", gameField.getCell(row, column));
            }
            System.out.println();
        }
    }

    public void displayScore(final int score) {
        System.out.printf("Score: %d%n", score);
    }
}
