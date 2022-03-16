package ui.output;

import game.Game;
import game.Position;

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

    public void displayField(final Game game) {
        for (int row = 0; row < game.getFieldSize(); row++) {
            for (int column = 0; column < game.getFieldSize(); column++) {
                System.out.printf("%4d ", game.getCell(new Position(row, column)));
            }
            System.out.println();
        }
    }

    public void displayScore(final int score) {
        System.out.printf("Score: %d%n", score);
    }

    @Override
    public void displayGameOver(int score) {
        System.out.printf("GAME OVER%n");
        System.out.printf("Final score: %d%n", score);
    }
}
