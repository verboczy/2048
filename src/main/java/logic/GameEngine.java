package logic;

import map.GameField;
import map.GameFieldBuilder;
import ui.InputHandler;
import ui.OutputHandler;

public class GameEngine {

    private final InputHandler inputHandler;
    private final GameFieldBuilder gameFieldBuilder;

    public GameEngine(final InputHandler inputHandler, final GameFieldBuilder gameFieldBuilder) {
        this.inputHandler = inputHandler;
        this.gameFieldBuilder = gameFieldBuilder;
    }

    public void runGame() {
        boolean endGame = false;

        Computer computer = new Computer();

        GameField gameField = gameFieldBuilder.build(4);

        // Game loop
        while (!endGame) {
            Command command = inputHandler.getCommand();
            switch (command) {
                case UP:
                    computer.up(gameField);
                    break;
                case DOWN:
                    computer.down(gameField);
                    break;
                case LEFT:
                    computer.left(gameField);
                    break;
                case RIGHT:
                    computer.right(gameField);
                    break;
                case NEW_GAME:
                    gameField = gameFieldBuilder.build(4);
                    break;
                case EXIT:
                    endGame = true;
                    break;
                case NO_OPERATION:
                    break;
                default:
                    break;
            }
            OutputHandler.print(gameField);

        }
    }
}
