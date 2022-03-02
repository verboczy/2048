package app;

import logic.GameEngine;
import logic.compute.Computer;
import logic.compute.SingleThreadComputer;
import logic.random.position.CellListRandomizer;
import logic.random.position.CellRandomizer;
import logic.random.value.BasicValueRandomizer;
import map.GameFieldBuilder;
import ui.ConsoleInputHandler;
import ui.InputHandler;

import java.util.Random;

public class Main {

    public static void main(final String[] args) {
        final InputHandler inputHandler = ConsoleInputHandler.getInstance();
        final GameFieldBuilder gameFieldBuilder = new GameFieldBuilder();
        final Computer computer = new SingleThreadComputer();
        final CellRandomizer cellRandomizer = new CellListRandomizer(new Random(), new BasicValueRandomizer(new Random()));

        final GameEngine gameEngine = new GameEngine(inputHandler, gameFieldBuilder, computer, cellRandomizer);
        gameEngine.runGame();
    }
}
