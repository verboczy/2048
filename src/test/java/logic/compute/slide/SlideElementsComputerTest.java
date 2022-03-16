package logic.compute.slide;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlideElementsComputerTest {

    private static Stream<Arguments> slideParameters() {
        return Stream.of(
                // No movement
                Arguments.of(List.of(0, 0, 0, 0), new SlideResult(List.of(0, 0, 0, 0), 0, false)),
                Arguments.of(List.of(2, 4, 8, 16), new SlideResult(List.of(2, 4, 8, 16), 0, false)),
                // Moves, but doesn't merge
                Arguments.of(List.of(0, 0, 0, 2), new SlideResult(List.of(2, 0, 0, 0), 0, true)),
                Arguments.of(List.of(0, 2, 4, 2), new SlideResult(List.of(2, 4, 2, 0), 0, true)),
                Arguments.of(List.of(0, 2, 4, 8), new SlideResult(List.of(2, 4, 8, 0), 0, true)),
                Arguments.of(List.of(0, 4, 0, 2), new SlideResult(List.of(4, 2, 0, 0), 0, true)),
                // Moves and merges
                Arguments.of(List.of(0, 0, 2, 2), new SlideResult(List.of(4, 0, 0, 0), 4, true)),
                Arguments.of(List.of(0, 4, 2, 2), new SlideResult(List.of(4, 4, 0, 0), 4, true)),
                Arguments.of(List.of(2, 2, 2, 2), new SlideResult(List.of(4, 4, 0, 0), 8, true)),
                Arguments.of(List.of(8, 4, 2, 2), new SlideResult(List.of(8, 4, 4, 0), 4, true)),
                Arguments.of(List.of(2, 2, 4, 0, 0), new SlideResult(List.of(4, 4, 0, 0, 0), 4, true)),
                Arguments.of(List.of(8, 8, 2, 2, 0), new SlideResult(List.of(16, 4, 0, 0, 0), 20, true)),
                Arguments.of(List.of(0, 2, 2, 2, 0), new SlideResult(List.of(4, 2, 0, 0, 0), 4, true)),
                Arguments.of(List.of(4, 0, 4, 8, 0), new SlideResult(List.of(8, 8, 0, 0, 0), 8, true))
        );
    }

    @ParameterizedTest(name = "Case{index} - input: {0} - expected: {1}")
    @MethodSource("slideParameters")
    void slideTest(final List<Integer> inputElements, final SlideResult expectedResult) {
        final SlideElementsComputer movementTaskTester = new SlideElementsComputer(inputElements);

        final SlideResult actualResult = movementTaskTester.compute();

        assertEquals(expectedResult, actualResult);
    }
}
