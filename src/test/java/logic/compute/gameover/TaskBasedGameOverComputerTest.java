package logic.compute.gameover;

import game.Game;
import logic.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskBasedGameOverComputerTest {

    private static Stream<Arguments> gameOverParameters() {
        return Stream.of(
                Arguments.of("canMoveHorizontally", false),
                Arguments.of("canMoveVertically", false),
                Arguments.of("hasEmptyCell", false),
                Arguments.of("gameOver", true)
        );
    }

    @DisplayName("Game over computation checking")
    @ParameterizedTest(name = "Case{index} - {0} should be {1}")
    @MethodSource("gameOverParameters")
    void gameOverCheck(final String fileName, final boolean expectedResult) {
        final Game game = TestUtil.readInputGame(String.format("src/test/resources/logic/compute/gameover/%s.txt", fileName));
        final GameOverComputer gameOverComputer = new TaskBasedGameOverComputer(Executors.newFixedThreadPool(4));

        boolean actualResult = gameOverComputer.isGameOver(game);

        assertEquals(expectedResult, actualResult);
    }
}
