package app;

import logic.GameEngine;
import map.GameFieldBuilder;
import ui.ConsoleInputHandler;
import ui.InputHandler;

public class Main {

    public static void main(final String[] args) {
        final InputHandler inputHandler = ConsoleInputHandler.getInstance();
        final GameFieldBuilder gameFieldBuilder = new GameFieldBuilder();

        final GameEngine gameEngine = new GameEngine(inputHandler, gameFieldBuilder);
        gameEngine.runGame();
    }
}
