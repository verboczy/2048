package app;

import game.GameFieldBuilder;
import logic.GameEngine;
import logic.compute.Computer;
import logic.compute.MultiThreadComputer;
import logic.random.position.CellListRandomizer;
import logic.random.position.CellRandomizer;
import logic.random.value.BasicValueRandomizer;
import ui.input.ConsoleInputHandler;
import ui.input.InputHandler;
import ui.output.ConsoleOutputHandler;
import ui.output.OutputHandler;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(final String[] args) {
        final InputHandler inputHandler = ConsoleInputHandler.getInstance();
        final OutputHandler outputHandler = ConsoleOutputHandler.getInstance();
        final GameFieldBuilder gameFieldBuilder = new GameFieldBuilder();
        //final Computer computer = new SingleThreadComputer();
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final Computer computer = new MultiThreadComputer(executorService);
        final CellRandomizer cellRandomizer = new CellListRandomizer(new Random(), new BasicValueRandomizer(new Random()));

        final GameEngine gameEngine = new GameEngine(inputHandler, outputHandler, gameFieldBuilder, computer, cellRandomizer);
        gameEngine.playGame();
        executorService.shutdown();
    }
}
