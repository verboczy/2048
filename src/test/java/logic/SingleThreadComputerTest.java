package logic;

import logic.compute.SingleThreadComputer;
import map.Cell;
import map.GameField;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleThreadComputerTest {

    private static Stream<Arguments> parameters() {
        final BiConsumer<SingleThreadComputer, GameField> up = SingleThreadComputer::moveUp;
        final BiConsumer<SingleThreadComputer, GameField> down = SingleThreadComputer::moveDown;
        final BiConsumer<SingleThreadComputer, GameField> right = SingleThreadComputer::moveRight;
        final BiConsumer<SingleThreadComputer, GameField> left = SingleThreadComputer::moveLeft;

        return Stream.of(
                Arguments.of("upMove", up),
                Arguments.of("upMoveMoreElements", up),
                Arguments.of("upMerging", up),
                Arguments.of("upCornerCases", up),

                Arguments.of("downMove", down),
                Arguments.of("downMoveMoreElements", down),
                Arguments.of("downMerging", down),
                Arguments.of("downCornerCases", down),

                Arguments.of("rightMove", right),
                Arguments.of("rightMoveMoreElements", right),
                Arguments.of("rightMerging", right),
                Arguments.of("rightCornerCases", right),

                Arguments.of("leftMove", left),
                Arguments.of("leftMoveMoreElements", left),
                Arguments.of("leftMerging", left),
                Arguments.of("leftCornerCases", left)
        );
    }

    @MethodSource("parameters")
    @ParameterizedTest(name = "Case {index}: {0}")
    void movementLogicTest(String fileName, BiConsumer<SingleThreadComputer, GameField> consumer) throws FileNotFoundException {
        // Given
        final SingleThreadComputer computer = new SingleThreadComputer();

        final Scanner scanner = new Scanner(new File(String.format("src/test/resources/logic/%s.txt", fileName)));

        // 1. Read input
        final int size = scanner.nextInt();
        final GameField gameField = new GameField(size);
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                final int value = scanner.nextInt();
                gameField.setCell(new Cell(row, column, value));
            }
        }

        // 2. Read separator blank line
        scanner.nextLine();

        // 3. Read expected result
        final GameField expectedGameField = new GameField(size);
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                final int value = scanner.nextInt();
                expectedGameField.setCell(new Cell(row, column, value));
            }
        }

        // When
        consumer.accept(computer, gameField);

        // Then
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                assertEquals(expectedGameField.getCell(row, column), gameField.getCell(row, column));
            }
        }
    }
}
