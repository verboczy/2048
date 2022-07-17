package logic.compute.slide;

import logic.compute.slide.dto.SlideResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlideElementsComputerTest {

    private static final boolean hasChanged = true;
    private static final boolean hasNotChanged = false;

    private static final int score_0 = 0;
    private static final int score_4 = 4;
    private static final int score_8 = 8;
    private static final int score_20 = 20;


    private static Stream<Arguments> slideParameters() {
        return Stream.of(
                // No movement
                Arguments.of(List.of(0, 0, 0, 0), new SlideResult(List.of(0, 0, 0, 0), score_0, hasNotChanged)),
                Arguments.of(List.of(2, 4, 8, 16), new SlideResult(List.of(2, 4, 8, 16), score_0, hasNotChanged)),
                // Moves, but doesn't merge
                Arguments.of(List.of(0, 0, 0, 2), new SlideResult(List.of(2, 0, 0, 0), score_0, hasChanged)),
                Arguments.of(List.of(0, 2, 4, 2), new SlideResult(List.of(2, 4, 2, 0), score_0, hasChanged)),
                Arguments.of(List.of(0, 2, 4, 8), new SlideResult(List.of(2, 4, 8, 0), score_0, hasChanged)),
                Arguments.of(List.of(0, 4, 0, 2), new SlideResult(List.of(4, 2, 0, 0), score_0, hasChanged)),
                // Moves and merges
                Arguments.of(List.of(0, 0, 2, 2), new SlideResult(List.of(4, 0, 0, 0), score_4, hasChanged)),
                Arguments.of(List.of(0, 4, 2, 2), new SlideResult(List.of(4, 4, 0, 0), score_4, hasChanged)),
                Arguments.of(List.of(2, 2, 2, 2), new SlideResult(List.of(4, 4, 0, 0), score_8, hasChanged)),
                Arguments.of(List.of(8, 4, 2, 2), new SlideResult(List.of(8, 4, 4, 0), score_4, hasChanged)),
                Arguments.of(List.of(2, 2, 4, 0, 0), new SlideResult(List.of(4, 4, 0, 0, 0), score_4, hasChanged)),
                Arguments.of(List.of(8, 8, 2, 2, 0), new SlideResult(List.of(16, 4, 0, 0, 0), score_20, hasChanged)),
                Arguments.of(List.of(0, 2, 2, 2, 0), new SlideResult(List.of(4, 2, 0, 0, 0), score_4, hasChanged)),
                Arguments.of(List.of(4, 0, 4, 8, 0), new SlideResult(List.of(8, 8, 0, 0, 0), score_8, hasChanged))
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
