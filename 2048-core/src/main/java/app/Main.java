package app;

import game.GameFieldBuilder;
import logic.GameEngine;
import logic.compute.gameover.TaskBasedGameOverComputer;
import logic.compute.slide.SlideComputer;
import logic.compute.slide.TaskBasedSlideComputer;
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
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final SlideComputer slideComputer = new TaskBasedSlideComputer(executorService);
        final TaskBasedGameOverComputer gameOverComputer = new TaskBasedGameOverComputer(executorService);
        final CellRandomizer cellRandomizer = new CellListRandomizer(new Random(), new BasicValueRandomizer(new Random()));

        final GameEngine gameEngine = new GameEngine(
                inputHandler,
                outputHandler,
                gameFieldBuilder,
                slideComputer,
                gameOverComputer,
                cellRandomizer
        );

        gameEngine.playGame();
        executorService.shutdown();
    }
}
