package logic.compute.gameover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOverCheckTaskTest {

    private static Stream<Arguments> gameOverTestParameters() {
        return Stream.of(
                // Has empty cell
                Arguments.of(List.of(0, 2, 4, 8), false),
                Arguments.of(List.of(2, 0, 2, 4), false),
                Arguments.of(List.of(2, 4, 0, 2), false),
                Arguments.of(List.of(2, 4, 8, 0), false),
                Arguments.of(List.of(0, 0, 0, 0), false),
                // Has same neighbour
                Arguments.of(List.of(2, 2, 4, 8), false),
                Arguments.of(List.of(2, 4, 4, 8), false),
                Arguments.of(List.of(2, 4, 8, 8), false),
                Arguments.of(List.of(2, 2, 2, 2), false),
                Arguments.of(List.of(4, 2, 2, 4), false),
                // Has empty cell and has same neighbour
                Arguments.of(List.of(2, 2, 2, 0), false),
                // No empty cell, no same neighbour
                Arguments.of(List.of(2, 4, 8, 16), true),
                Arguments.of(List.of(4, 2, 4, 2), true)
        );
    }

    @DisplayName("Checking game over for a list of values")
    @ParameterizedTest(name = "Case{index} - {0}: should be game over: {1}")
    @MethodSource("gameOverTestParameters")
    void gameOverCheck(final List<Integer> elements, final boolean expectedResult) {
        final GameOverCheckTask givenGameOverCheckTask = new GameOverCheckTask(elements);

        final boolean actualResult = givenGameOverCheckTask.call();

        assertEquals(expectedResult, actualResult);
    }
}
