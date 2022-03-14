package logic;

import game.Cell;
import game.Game;
import game.GameField;
import game.GameFieldBuilder;
import logic.compute.gameover.GameOverComputer;
import logic.compute.movement.MovementComputer;
import logic.random.position.CellRandomizer;
import ui.input.InputHandler;
import ui.output.OutputHandler;

public class GameEngine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final GameFieldBuilder gameFieldBuilder;
    private final MovementComputer movementComputer;
    private final GameOverComputer gameOverComputer;
    private final CellRandomizer cellRandomizer;

    public GameEngine(
            final InputHandler inputHandler,
            final OutputHandler outputHandler,
            final GameFieldBuilder gameFieldBuilder,
            final MovementComputer movementComputer,
            final GameOverComputer gameOverComputer,
            final CellRandomizer cellRandomizer) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.gameFieldBuilder = gameFieldBuilder;
        this.movementComputer = movementComputer;
        this.gameOverComputer = gameOverComputer;
        this.cellRandomizer = cellRandomizer;
    }

    public void playGame() {
        Game game = createNewGame();
        boolean endGame = false;
        while (!endGame) {
            final Command command = inputHandler.getCommand();
            if (command == Command.EXIT) {
                endGame = true;
                outputHandler.displayScore(game.getScore());
            } else if (command == Command.NEW_GAME) {
                outputHandler.displayScore(game.getScore());
                game = createNewGame();
            } else {
                switch (command) {
                    case UP:
                        movementComputer.moveUp(game);
                        break;
                    case DOWN:
                        movementComputer.moveDown(game);
                        break;
                    case LEFT:
                        movementComputer.moveLeft(game);
                        break;
                    case RIGHT:
                        movementComputer.moveRight(game);
                        break;
                }
                updateGameField(game);
                displayScoreAndField(game);
                endGame = isGameOver(game);
            }
        }
        // TODO - move to output handler
        System.out.println("Game over, total score: " + game.getScore());
    }

    private boolean isGameOver(final Game game) {
        return gameOverComputer.isGameOver(game);
    }

    private Game createNewGame() {
        final GameField gameField = gameFieldBuilder.build(4);
        final Game game = new Game(gameField);

        // Start game with two random value on the field.
        for (int i = 0; i < 2; i++) {
            addRandomField(game);
        }

        displayScoreAndField(game);

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

    private void displayScoreAndField(final Game game) {
        outputHandler.displayScore(game.getScore());
        outputHandler.displayField(game);
    }
}
