package logic;

import game.Cell;
import game.Game;
import game.GameField;
import game.Position;
import logic.compute.Computer;
import logic.compute.MultiThreadComputer;
import logic.compute.SingleThreadComputer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ComputerTest {

    private static final String UP = "up/";
    private static final String DOWN = "down/";
    private static final String RIGHT = "right/";
    private static final String LEFT = "left/";

    private static final String BASE_DIRECTORY = "src/test/resources/logic/";
    private static final String EXPECTED_SUFFIX = "Expected";
    private static final String FILE_EXTENSION = ".txt";

    private static Stream<Arguments> parameters() {
        final BiConsumer<Computer, Game> up = Computer::moveUp;
        final BiConsumer<Computer, Game> down = Computer::moveDown;
        final BiConsumer<Computer, Game> right = Computer::moveRight;
        final BiConsumer<Computer, Game> left = Computer::moveLeft;

        final Computer singleThreadedComputer = new SingleThreadComputer();
        final Computer multiThreadedComputer = new MultiThreadComputer(Executors.newFixedThreadPool(4));

        return Stream.of(
                Arguments.of("up - single threaded", UP, "upMove", up, singleThreadedComputer),
                Arguments.of("up more elements - single threaded", UP, "upMoveMoreElements", up, singleThreadedComputer),
                Arguments.of("up merging - single threaded", UP, "upMerging", up, singleThreadedComputer),
                Arguments.of("up corner cases - single threaded", UP, "upCornerCases", up, singleThreadedComputer),
                Arguments.of("up no change - single threaded", UP, "upNoChange", up, singleThreadedComputer),

                Arguments.of("down - single threaded", DOWN, "downMove", down, singleThreadedComputer),
                Arguments.of("down more elements - single threaded", DOWN, "downMoveMoreElements", down, singleThreadedComputer),
                Arguments.of("down merging - single threaded", DOWN, "downMerging", down, singleThreadedComputer),
                Arguments.of("down corner cases - single threaded", DOWN, "downCornerCases", down, singleThreadedComputer),
                Arguments.of("down no change - single threaded", DOWN, "downNoChange", down, singleThreadedComputer),

                Arguments.of("right  - single threaded", RIGHT, "rightMove", right, singleThreadedComputer),
                Arguments.of("right more elements - single threaded", RIGHT, "rightMoveMoreElements", right, singleThreadedComputer),
                Arguments.of("right merging - single threaded", RIGHT, "rightMerging", right, singleThreadedComputer),
                Arguments.of("right corner cases - single threaded", RIGHT, "rightCornerCases", right, singleThreadedComputer),
                Arguments.of("right no change - single threaded", RIGHT, "rightNoChange", right, singleThreadedComputer),

                Arguments.of("left - single threaded", LEFT, "leftMove", left, singleThreadedComputer),
                Arguments.of("left more elements - single threaded", LEFT, "leftMoveMoreElements", left, singleThreadedComputer),
                Arguments.of("left merging - single threaded", LEFT, "leftMerging", left, singleThreadedComputer),
                Arguments.of("left corner cases - single threaded", LEFT, "leftCornerCases", left, singleThreadedComputer),
                Arguments.of("left no change - single threaded", LEFT, "leftNoChange", left, singleThreadedComputer),

                Arguments.of("up - multithreaded", UP, "upMove", up, multiThreadedComputer),
                Arguments.of("up more elements - multithreaded", UP, "upMoveMoreElements", up, multiThreadedComputer),
                Arguments.of("up merging - multithreaded", UP, "upMerging", up, multiThreadedComputer),
                Arguments.of("up corner cases - multithreaded", UP, "upCornerCases", up, multiThreadedComputer),
                Arguments.of("up no change - multithreaded", UP, "upNoChange", up, multiThreadedComputer),

                Arguments.of("down - multithreaded", DOWN, "downMove", down, multiThreadedComputer),
                Arguments.of("down more elements - multithreaded", DOWN, "downMoveMoreElements", down, multiThreadedComputer),
                Arguments.of("down merging - multithreaded", DOWN, "downMerging", down, multiThreadedComputer),
                Arguments.of("down corner cases - multithreaded", DOWN, "downCornerCases", down, multiThreadedComputer),
                Arguments.of("down no change - multithreaded", DOWN, "downNoChange", down, multiThreadedComputer),

                Arguments.of("right  - multithreaded", RIGHT, "rightMove", right, multiThreadedComputer),
                Arguments.of("right more elements - multithreaded", RIGHT, "rightMoveMoreElements", right, multiThreadedComputer),
                Arguments.of("right merging - multithreaded", RIGHT, "rightMerging", right, multiThreadedComputer),
                Arguments.of("right corner cases - multithreaded", RIGHT, "rightCornerCases", right, multiThreadedComputer),
                Arguments.of("right no change - multithreaded", RIGHT, "rightNoChange", right, multiThreadedComputer),

                Arguments.of("left - multithreaded", LEFT, "leftMove", left, multiThreadedComputer),
                Arguments.of("left more elements - multithreaded", LEFT, "leftMoveMoreElements", left, multiThreadedComputer),
                Arguments.of("left merging - multithreaded", LEFT, "leftMerging", left, multiThreadedComputer),
                Arguments.of("left corner cases - multithreaded", LEFT, "leftCornerCases", left, multiThreadedComputer),
                Arguments.of("left no change - multithreaded", LEFT, "leftNoChange", left, multiThreadedComputer)
        );
    }

    @SuppressWarnings("unused")
    @DisplayName("Movement test")
    @MethodSource("parameters")
    @ParameterizedTest(name = "Case {index}: {0}")
    void movementLogicTest(
            final String testCaseName,
            final String directory,
            final String fileName,
            final BiConsumer<Computer, Game> consumer,
            final Computer computer
    ) {
        // Given
        final Game game = readInputGameFromFile(new File(String.format("%s%s%s%s", BASE_DIRECTORY, directory, fileName, FILE_EXTENSION)));
        final Game expectedGame = readExpectedOutputGameFromFile(new File(String.format("%s%s%s%s%s", BASE_DIRECTORY, directory, fileName, EXPECTED_SUFFIX, FILE_EXTENSION)));

        // When
        consumer.accept(computer, game);

        // Then
        assertEquals(expectedGame, game);
    }

    private Game readInputGameFromFile(final File file) {
        try {
            final Scanner scanner = new Scanner(file);
            final int size = scanner.nextInt();
            final GameField gameField = new GameField(size);
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    final int value = scanner.nextInt();
                    gameField.setCell(new Cell(new Position(row, column), value));
                }
            }
            return new Game(gameField);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        return null;
    }

    private Game readExpectedOutputGameFromFile(final File file) {
        try {
            final Scanner scanner = new Scanner(file);
            final int size = scanner.nextInt();
            final boolean changed = scanner.nextInt() == 1;
            final int score = scanner.nextInt();
            final GameField gameField = new GameField(size);
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    final int value = scanner.nextInt();
                    gameField.setCell(new Cell(new Position(row, column), value));
                }
            }
            final Game game = new Game(gameField);
            game.setChanged(changed);
            game.addScore(score);
            return game;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        return null;
    }
}
