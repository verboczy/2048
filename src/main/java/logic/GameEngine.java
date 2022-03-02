package logic;

import logic.compute.Computer;
import logic.random.position.CellRandomizer;
import map.Cell;
import map.GameField;
import map.GameFieldBuilder;
import map.Movement;
import ui.InputHandler;
import ui.OutputHandler;

public class GameEngine {

    private final InputHandler inputHandler;
    private final GameFieldBuilder gameFieldBuilder;
    private final Computer computer;
    private final CellRandomizer cellRandomizer;

    public GameEngine(final InputHandler inputHandler, final GameFieldBuilder gameFieldBuilder, final Computer computer, final CellRandomizer cellRandomizer) {
        this.inputHandler = inputHandler;
        this.gameFieldBuilder = gameFieldBuilder;
        this.computer = computer;
        this.cellRandomizer = cellRandomizer;
    }

    public void runGame() {
        boolean endGame = false;

        GameField gameField = createNewGame();
        OutputHandler.print(gameField);

        // Game loop
        while (!endGame) {
            final Command command = inputHandler.getCommand();
            switch (command) {
                case UP:
                    updateMap(computer.moveUp(gameField), gameField);
                    break;
                case DOWN:
                    updateMap(computer.moveDown(gameField), gameField);
                    break;
                case LEFT:
                    updateMap(computer.moveLeft(gameField), gameField);
                    break;
                case RIGHT:
                    updateMap(computer.moveRight(gameField), gameField);
                    break;
                case NEW_GAME:
                    gameField = createNewGame();
                    break;
                case NO_OPERATION:
                    continue;
                case EXIT:
                    endGame = true;
                    break;
            }
        }
    }

    private void updateMap(final Movement movement, final GameField gameField) {
        if (movement.isFieldChanged()) {
            addRandomField(gameField);
        }
        OutputHandler.print(gameField);
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
