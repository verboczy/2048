package ui;

import game.Game;
import game.Position;
import queue.OutputQueue;

public class OutputHandler {

    final OutputQueue gameQueue;
    boolean exit;

    public OutputHandler(OutputQueue gameQueue) {
        this.gameQueue = gameQueue;
        this.exit = false;
    }

    public void exit() {
        this.exit = true;
    }

    public void printGame() {
        while (!exit) {
            try {
                final Game game = gameQueue.getResult();
                if (game.isExit()) {
                    System.out.println("Goodbye!");
                } else {
                    for (int row = 0; row < game.getFieldSize(); row++) {
                        for (int column = 0; column < game.getFieldSize(); column++) {
                            System.out.printf("%4d ", game.getCell(new Position(row, column)));
                        }
                        System.out.println();
                    }
                    if (game.isGameOver()) {
                        displayGameOver(game.getScore());
                    } else {
                        displayScore(game.getScore());
                    }
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    private void displayScore(final int score) {
        System.out.printf("Score: %d%n", score);
    }

    private void displayGameOver(int score) {
        System.out.printf("------------------%n");
        System.out.printf("GAME OVER%n");
        System.out.printf("Final score: %d%n", score);
        System.out.printf("------------------%n");
    }
}
