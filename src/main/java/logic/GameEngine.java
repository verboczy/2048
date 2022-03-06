package logic;

import game.*;
import logic.compute.Computer;
import logic.random.position.CellRandomizer;
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

        Game game = createNewGame();
        outputHandler.displayField(game);

        // Game loop
        while (!endGame) {
            final Command command = inputHandler.getCommand();
            switch (command) {
                case UP:
                    computer.moveUp(game);
                    updateMap(game);
                    break;
                case DOWN:
                    computer.moveDown(game);
                    updateMap(game);
                    break;
                case LEFT:
                    computer.moveLeft(game);
                    updateMap(game);
                    break;
                case RIGHT:
                    computer.moveRight(game);
                    updateMap(game);
                    break;
                case NEW_GAME:
                    outputHandler.displayScore(game.getScore());
                    game = createNewGame();
                    break;
                case NO_OPERATION:
                    continue;
                case EXIT:
                    endGame = true;
                    outputHandler.displayScore(game.getScore());
                    break;
            }
        }
    }

    private void updateMap(final Game game) {
        if (game.isChanged()) {
            addRandomField(game);
            game.setChanged(false);
        }
        outputHandler.displayScore(game.getScore());
        outputHandler.displayField(game);
    }

    private void addRandomField(final Game game) {
        final Cell randomNewCell = cellRandomizer.getRandomNewCell(game);
        game.setCell(new Cell(randomNewCell.getRow(), randomNewCell.getColumn(), randomNewCell.getValue()));
    }

    private Game createNewGame() {
        final GameField gameField = gameFieldBuilder.build(4);
        final Game game = new Game(gameField);

        // Start game with two random value on the map.
        for (int i = 0; i < 2; i++) {
            addRandomField(game);
        }

        return game;
    }
}
