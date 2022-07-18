package app;

import game.GameFieldBuilder;
import logic.GameEngine;
import logic.compute.gameover.TaskBasedGameOverComputer;
import logic.compute.slide.SlideComputer;
import logic.compute.slide.TaskBasedSlideComputer;
import logic.random.position.CellListRandomizer;
import logic.random.position.CellRandomizer;
import logic.random.value.BasicValueRandomizer;
import queue.ConsoleInputQueue;
import queue.ConsoleOutputQueue;
import queue.InputQueue;
import queue.OutputQueue;
import ui.InputHandler;
import ui.OutputHandler;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(final String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(6);

        final InputQueue inputQueue = new ConsoleInputQueue(new ArrayBlockingQueue<>(10));
        final OutputQueue outputQueue = new ConsoleOutputQueue(new ArrayBlockingQueue<>(10));

        final GameFieldBuilder gameFieldBuilder = new GameFieldBuilder();
        final SlideComputer slideComputer = new TaskBasedSlideComputer(executorService);
        final TaskBasedGameOverComputer gameOverComputer = new TaskBasedGameOverComputer(executorService);
        final CellRandomizer cellRandomizer = new CellListRandomizer(new Random(), new BasicValueRandomizer(new Random()));

        final GameEngine gameEngine = new GameEngine(
                inputQueue,
                outputQueue,
                gameFieldBuilder,
                slideComputer,
                gameOverComputer,
                cellRandomizer,
                2
        );

        final InputHandler inputHandler = new InputHandler(inputQueue);
        final OutputHandler outputHandler = new OutputHandler(outputQueue);

        executorService.submit(outputHandler::printGame);
        executorService.submit(inputHandler::handleConsoleInput);

        gameEngine.playGame();

        inputHandler.exit();
        outputHandler.exit();

        executorService.shutdown();
    }
}
