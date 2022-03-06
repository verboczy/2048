package logic;

import game.*;
import logic.compute.Computer;
import logic.random.Result;
import logic.random.position.CellRandomizer;
import ui.input.InputHandler;
import ui.output.OutputHandler;

import static logic.random.Result.PLAY_AGAIN;
import static logic.random.Result.QUIT;

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

    public void runGameEngine() {
        Result result = startGame();

        while (result == PLAY_AGAIN) {
            result = startGame();
        }
    }

    private Result startGame() {
        final Game game = createNewGame();
        outputHandler.displayField(game);
        return playGame(game);
    }

    private Game createNewGame() {
        final GameField gameField = gameFieldBuilder.build(4);
        final Game game = new Game(gameField);

        // Start game with two random value on the field.
        for (int i = 0; i < 2; i++) {
            addRandomField(game);
        }

        return game;
    }

    private void addRandomField(final Game game) {
        final Cell randomNewCell = cellRandomizer.getRandomNewCell(game);
        game.setCell(new Cell(randomNewCell.getRow(), randomNewCell.getColumn(), randomNewCell.getValue()));
    }

    private Result playGame(final Game game) {
        // TODO - while (hasMove)
        while (true) {
            final Command command = inputHandler.getCommand();
            switch (command) {
                case UP:
                    computer.moveUp(game);
                    break;
                case DOWN:
                    computer.moveDown(game);
                    break;
                case LEFT:
                    computer.moveLeft(game);
                    break;
                case RIGHT:
                    computer.moveRight(game);
                    break;
                case NEW_GAME:
                    return PLAY_AGAIN;
                case EXIT:
                    return QUIT;
            }
            updateGameField(game);
            displayScoreAndUpdatedField(game);
        }
    }

    private void updateGameField(final Game game) {
        if (game.isChanged()) {
            addRandomField(game);
            game.setChanged(false);
        }
    }

    private void displayScoreAndUpdatedField(final Game game) {
        outputHandler.displayScore(game.getScore());
        outputHandler.displayField(game);
    }
}
