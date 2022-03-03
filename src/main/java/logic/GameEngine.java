package logic;

import logic.compute.Computer;
import logic.random.position.CellRandomizer;
import map.Cell;
import map.GameField;
import map.GameFieldBuilder;
import map.Movement;
import ui.input.InputHandler;
import ui.output.OutputHandler;

public class GameEngine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final GameFieldBuilder gameFieldBuilder;
    private final Computer computer;
    private final CellRandomizer cellRandomizer;

    public GameEngine(final InputHandler inputHandler, final OutputHandler outputHandler, final GameFieldBuilder gameFieldBuilder, final Computer computer, final CellRandomizer cellRandomizer) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.gameFieldBuilder = gameFieldBuilder;
        this.computer = computer;
        this.cellRandomizer = cellRandomizer;
    }

    public void runGame() {
        boolean endGame = false;
        int score = 0;

        GameField gameField = createNewGame();
        outputHandler.displayField(gameField);

        // Game loop
        while (!endGame) {
            final Command command = inputHandler.getCommand();
            switch (command) {
                case UP:
                    final Movement upMovement = computer.moveUp(gameField);
                    score += upMovement.getMovementScore();
                    updateMap(upMovement.isFieldChanged(), gameField, score);
                    break;
                case DOWN:
                    final Movement downMovement = computer.moveDown(gameField);
                    score += downMovement.getMovementScore();
                    updateMap(downMovement.isFieldChanged(), gameField, score);
                    break;
                case LEFT:
                    final Movement leftMovement = computer.moveLeft(gameField);
                    score += leftMovement.getMovementScore();
                    updateMap(leftMovement.isFieldChanged(), gameField, score);
                    break;
                case RIGHT:
                    final Movement rightMovement = computer.moveRight(gameField);
                    score += rightMovement.getMovementScore();
                    updateMap(rightMovement.isFieldChanged(), gameField, score);
                    break;
                case NEW_GAME:
                    gameField = createNewGame();
                    outputHandler.displayScore(score);
                    break;
                case NO_OPERATION:
                    continue;
                case EXIT:
                    endGame = true;
                    outputHandler.displayScore(score);
                    break;
            }
        }
    }

    private void updateMap(final boolean isFieldChanged, final GameField gameField, final int score) {
        if (isFieldChanged) {
            addRandomField(gameField);
        }
        outputHandler.displayScore(score);
        outputHandler.displayField(gameField);
    }

    private void addRandomField(final GameField gameField) {
        final Cell randomNewCell = cellRandomizer.getRandomNewCell(gameField);
        gameField.setCell(new Cell(randomNewCell.getRow(), randomNewCell.getColumn(), randomNewCell.getValue()));
    }

    private GameField createNewGame() {
        final GameField gameField = gameFieldBuilder.build(4);

        // Start game with two random value on the map.
        for (int i = 0; i < 2; i++) {
            addRandomField(gameField);
        }

        return gameField;
    }
}
