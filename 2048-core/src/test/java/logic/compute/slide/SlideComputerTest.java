package logic.compute.slide;

import game.Game;
import logic.Command;
import logic.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static logic.Command.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlideComputerTest {

    enum ComputerType {
        SINGLE_THREAD, FIXED_THREAD_POOL
    }

    private static final String BASE_DIRECTORY = "src/test/resources/logic/compute/slide/";
    private static final String EXPECTED_SUFFIX = "Expected";
    private static final String FILE_EXTENSION = ".txt";

    private static final ExecutorService singleThreadedExecutor = Executors.newSingleThreadExecutor();
    private static final ExecutorService fixedThreadPoolExecutor = Executors.newFixedThreadPool(4);

    private static Stream<Arguments> parameters() {

        final List<Command> directions = List.of(Command.UP, Command.DOWN, Command.RIGHT, Command.LEFT);
        final List<String> names = List.of("Slide", "SlideMoreElements", "Merging", "CornerCases", "NoChange");

        return allCombinationOfDirectionsNamesComputers(directions, names).stream();
    }

    private static List<Arguments> allCombinationOfDirectionsNamesComputers(
            final List<Command> directions,
            final List<String> names
    ) {
        final List<Arguments> parameters = new ArrayList<>();
        for (final Command direction : directions) {
            for (final String name : names) {
                for (final ComputerType computerType : ComputerType.values()) {
                    final Arguments arguments = Arguments.of(
                            getTestCaseName(direction, name, computerType),
                            getPathToInputFile(direction, name),
                            getPathToExpectedOutputFIle(direction, name),
                            direction,
                            getComputerByType(computerType)
                    );
                    parameters.add(arguments);
                }
            }
        }
        return parameters;
    }

    private static String getTestCaseName(final Command direction, final String name, final ComputerType computerType) {
        final String fileName = getFileName(direction, name);
        return String.format("%s - %s", fileName, computerType);
    }

    private static String getPathToInputFile(final Command direction, final String name) {
        return String.format("%s%s/%s%s", BASE_DIRECTORY, direction.getLowerCaseName(), getFileName(direction, name), FILE_EXTENSION);
    }

    private static String getPathToExpectedOutputFIle(final Command direction, final String name) {
        return String.format("%s%s/%s%s%s", BASE_DIRECTORY, direction.getLowerCaseName(), getFileName(direction, name), EXPECTED_SUFFIX, FILE_EXTENSION);
    }

    private static String getFileName(final Command direction, final String name) {
        return String.format("%s%s", direction.getLowerCaseName(), name);
    }

    private static SlideComputer getComputerByType(final ComputerType computerType) {
        if (computerType == ComputerType.FIXED_THREAD_POOL) {
            return new TaskBasedSlideComputer(singleThreadedExecutor);
        } else {
            return new TaskBasedSlideComputer(fixedThreadPoolExecutor);
        }
    }


    @DisplayName("Slide test")
    @MethodSource("parameters")
    @ParameterizedTest(name = "Case{index}: {0}")
    void slideTest(
            @SuppressWarnings("unused") final String testCaseName,
            final String fileName,
            final String expectedFileName,
            final Command direction,
            final SlideComputer computer
    ) {
        // Given
        final Game game = TestUtil.readInputGame(fileName);
        final Game expectedGame = TestUtil.readExpectedOutputGame(expectedFileName);

        // When
        computer.slide(game, direction);

        // Then
        assertEquals(expectedGame, game);
    }
}
