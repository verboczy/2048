package app;

import game.GameFieldBuilder;
import logic.GameEngine;
import logic.compute.movement.MovementComputer;
import logic.compute.gameover.TaskBasedGameOverComputer;
import logic.compute.movement.MultiThreadMovementComputer;
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
        final MovementComputer movementComputer = new MultiThreadMovementComputer(executorService);
        final TaskBasedGameOverComputer gameOverComputer = new TaskBasedGameOverComputer(executorService);
        final CellRandomizer cellRandomizer = new CellListRandomizer(new Random(), new BasicValueRandomizer(new Random()));

        final GameEngine gameEngine = new GameEngine(
                inputHandler,
                outputHandler,
                gameFieldBuilder,
                movementComputer,
                gameOverComputer,
                cellRandomizer
        );

        gameEngine.playGame();
        executorService.shutdown();
    }
}
