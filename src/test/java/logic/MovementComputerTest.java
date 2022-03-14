package logic;

import game.Game;
import logic.compute.movement.MovementComputer;
import logic.compute.movement.MultiThreadMovementComputer;
import logic.compute.movement.SingleThreadMovementComputer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementComputerTest {

    private static final String UP = "up/";
    private static final String DOWN = "down/";
    private static final String RIGHT = "right/";
    private static final String LEFT = "left/";

    private static final String BASE_DIRECTORY = "src/test/resources/logic/";
    private static final String EXPECTED_SUFFIX = "Expected";
    private static final String FILE_EXTENSION = ".txt";

    private static Stream<Arguments> parameters() {
        final BiConsumer<MovementComputer, Game> up = MovementComputer::moveUp;
        final BiConsumer<MovementComputer, Game> down = MovementComputer::moveDown;
        final BiConsumer<MovementComputer, Game> right = MovementComputer::moveRight;
        final BiConsumer<MovementComputer, Game> left = MovementComputer::moveLeft;

        final MovementComputer singleThreadedMovementComputer = new SingleThreadMovementComputer();
        final MovementComputer multiThreadedMovementComputer = new MultiThreadMovementComputer(Executors.newFixedThreadPool(4));

        return Stream.of(
                Arguments.of("up - single threaded", UP, "upMove", up, singleThreadedMovementComputer),
                Arguments.of("up more elements - single threaded", UP, "upMoveMoreElements", up, singleThreadedMovementComputer),
                Arguments.of("up merging - single threaded", UP, "upMerging", up, singleThreadedMovementComputer),
                Arguments.of("up corner cases - single threaded", UP, "upCornerCases", up, singleThreadedMovementComputer),
                Arguments.of("up no change - single threaded", UP, "upNoChange", up, singleThreadedMovementComputer),

                Arguments.of("down - single threaded", DOWN, "downMove", down, singleThreadedMovementComputer),
                Arguments.of("down more elements - single threaded", DOWN, "downMoveMoreElements", down, singleThreadedMovementComputer),
                Arguments.of("down merging - single threaded", DOWN, "downMerging", down, singleThreadedMovementComputer),
                Arguments.of("down corner cases - single threaded", DOWN, "downCornerCases", down, singleThreadedMovementComputer),
                Arguments.of("down no change - single threaded", DOWN, "downNoChange", down, singleThreadedMovementComputer),

                Arguments.of("right  - single threaded", RIGHT, "rightMove", right, singleThreadedMovementComputer),
                Arguments.of("right more elements - single threaded", RIGHT, "rightMoveMoreElements", right, singleThreadedMovementComputer),
                Arguments.of("right merging - single threaded", RIGHT, "rightMerging", right, singleThreadedMovementComputer),
                Arguments.of("right corner cases - single threaded", RIGHT, "rightCornerCases", right, singleThreadedMovementComputer),
                Arguments.of("right no change - single threaded", RIGHT, "rightNoChange", right, singleThreadedMovementComputer),

                Arguments.of("left - single threaded", LEFT, "leftMove", left, singleThreadedMovementComputer),
                Arguments.of("left more elements - single threaded", LEFT, "leftMoveMoreElements", left, singleThreadedMovementComputer),
                Arguments.of("left merging - single threaded", LEFT, "leftMerging", left, singleThreadedMovementComputer),
                Arguments.of("left corner cases - single threaded", LEFT, "leftCornerCases", left, singleThreadedMovementComputer),
                Arguments.of("left no change - single threaded", LEFT, "leftNoChange", left, singleThreadedMovementComputer),

                Arguments.of("up - multithreaded", UP, "upMove", up, multiThreadedMovementComputer),
                Arguments.of("up more elements - multithreaded", UP, "upMoveMoreElements", up, multiThreadedMovementComputer),
                Arguments.of("up merging - multithreaded", UP, "upMerging", up, multiThreadedMovementComputer),
                Arguments.of("up corner cases - multithreaded", UP, "upCornerCases", up, multiThreadedMovementComputer),
                Arguments.of("up no change - multithreaded", UP, "upNoChange", up, multiThreadedMovementComputer),

                Arguments.of("down - multithreaded", DOWN, "downMove", down, multiThreadedMovementComputer),
                Arguments.of("down more elements - multithreaded", DOWN, "downMoveMoreElements", down, multiThreadedMovementComputer),
                Arguments.of("down merging - multithreaded", DOWN, "downMerging", down, multiThreadedMovementComputer),
                Arguments.of("down corner cases - multithreaded", DOWN, "downCornerCases", down, multiThreadedMovementComputer),
                Arguments.of("down no change - multithreaded", DOWN, "downNoChange", down, multiThreadedMovementComputer),

                Arguments.of("right  - multithreaded", RIGHT, "rightMove", right, multiThreadedMovementComputer),
                Arguments.of("right more elements - multithreaded", RIGHT, "rightMoveMoreElements", right, multiThreadedMovementComputer),
                Arguments.of("right merging - multithreaded", RIGHT, "rightMerging", right, multiThreadedMovementComputer),
                Arguments.of("right corner cases - multithreaded", RIGHT, "rightCornerCases", right, multiThreadedMovementComputer),
                Arguments.of("right no change - multithreaded", RIGHT, "rightNoChange", right, multiThreadedMovementComputer),

                Arguments.of("left - multithreaded", LEFT, "leftMove", left, multiThreadedMovementComputer),
                Arguments.of("left more elements - multithreaded", LEFT, "leftMoveMoreElements", left, multiThreadedMovementComputer),
                Arguments.of("left merging - multithreaded", LEFT, "leftMerging", left, multiThreadedMovementComputer),
                Arguments.of("left corner cases - multithreaded", LEFT, "leftCornerCases", left, multiThreadedMovementComputer),
                Arguments.of("left no change - multithreaded", LEFT, "leftNoChange", left, multiThreadedMovementComputer)
        );
    }

    @SuppressWarnings("unused")
    @DisplayName("Movement test")
    @MethodSource("parameters")
    @ParameterizedTest(name = "Case{index}: {0}")
    void movementLogicTest(
            final String testCaseName,
            final String directory,
            final String fileName,
            final BiConsumer<MovementComputer, Game> consumer,
            final MovementComputer movementComputer
    ) {
        // Given
        final File inputFile = TestUtil.createFile(BASE_DIRECTORY + directory, fileName, FILE_EXTENSION);
        final Game game = TestUtil.readInputGameFromFile(inputFile);

        final File expectedOutputFile = TestUtil.createFile(BASE_DIRECTORY + directory, fileName + EXPECTED_SUFFIX, FILE_EXTENSION);
        final Game expectedGame = TestUtil.readExpectedOutputGameFromFile(expectedOutputFile);

        // When
        consumer.accept(movementComputer, game);

        // Then
        assertEquals(expectedGame, game);
    }
}
