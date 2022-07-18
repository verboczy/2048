package logic;

import game.Cell;
import game.Game;
import game.GameField;
import game.GameFieldBuilder;
import logic.compute.gameover.GameOverComputer;
import logic.compute.slide.SlideComputer;
import logic.random.position.CellRandomizer;
import queue.InputQueue;
import queue.OutputQueue;

public class GameEngine {

    private final InputQueue inputQueue;
    private final OutputQueue outputQueue;
    private final GameFieldBuilder gameFieldBuilder;
    private final SlideComputer slideComputer;
    private final GameOverComputer gameOverComputer;
    private final CellRandomizer cellRandomizer;
    private final int fieldSize;

    public GameEngine(
            final InputQueue inputQueue,
            final OutputQueue outputQueue,
            final GameFieldBuilder gameFieldBuilder,
            final SlideComputer slideComputer,
            final GameOverComputer gameOverComputer,
            final CellRandomizer cellRandomizer,
            final int fieldSize) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;

        this.gameFieldBuilder = gameFieldBuilder;
        this.slideComputer = slideComputer;
        this.gameOverComputer = gameOverComputer;
        this.cellRandomizer = cellRandomizer;
        if (fieldSize < 2) {
            throw new IllegalArgumentException("Field size cannot be less than 2, actual was " + fieldSize);
        }
        this.fieldSize = fieldSize;
    }

    public void playGame() throws InterruptedException {
        Game game = createNewGame();
        boolean exit = false;
        boolean gameOver = false;
        while (!exit) {
            final Command command = inputQueue.getNextCommand();
            if (command == Command.UNKNOWN) {
                // Do nothing
                continue;
            } else if (command == Command.EXIT) {
                exit = true;
                if (gameOver) {
                    game.setExit();
                } else {
                    game.setGameOver();
                }
                outputQueue.addResult(game);
            } else if (command == Command.NEW_GAME) {
                if (!gameOver) {
                    // Close the previous game
                    game.setGameOver();
                    outputQueue.addResult(game);
                }
                // Start a new game
                game = createNewGame();
                gameOver = false;
            } else {
                slideComputer.slide(game, command);

                updateGameField(game);

                gameOver = isGameOver(game);
                if (gameOver) {
                    game.setGameOver();
                }

                outputQueue.addResult(game);
            }
        }

    }

    private Game createNewGame() {
        final GameField gameField = gameFieldBuilder.build(fieldSize);
        final Game game = new Game(gameField);

        // Start game with two random value on the field.
        for (int i = 0; i < 2; i++) {
            addRandomField(game);
        }

        inputQueue.clear();
        outputQueue.clear();
        outputQueue.addResult(game);

        return game;
    }

    private void addRandomField(final Game game) {
        final Cell randomNewCell = cellRandomizer.getRandomNewCell(game);
        game.setCell(randomNewCell);
    }

    private void updateGameField(final Game game) {
        if (game.isChanged()) {
            addRandomField(game);
            game.setChanged(false);
        }
    }

    private boolean isGameOver(final Game game) {
        return gameOverComputer.isGameOver(game);
    }
}
