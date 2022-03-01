package logic;

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

public class ComputerTest {

    private static Stream<Arguments> parameters() {
        final BiConsumer<Computer, GameField> up = Computer::up;
        final BiConsumer<Computer, GameField> down = Computer::down;
        final BiConsumer<Computer, GameField> right = Computer::right;
        final BiConsumer<Computer, GameField> left = Computer::left;

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
    void movementLogicTest(String fileName, BiConsumer<Computer, GameField> consumer) throws FileNotFoundException {
        // Given
        final Computer computer = new Computer();

        final Scanner scanner = new Scanner(new File(String.format("src/test/resources/logic/%s.txt", fileName)));

        // 1. Read input
        final int size = scanner.nextInt();
        final GameField gameField = new GameField(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int value = scanner.nextInt();
                gameField.setCell(i, j, value);
            }
        }

        // 2. Read separator blank line
        scanner.nextLine();

        // 3. Read expected result
        final GameField expectedGameField = new GameField(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int value = scanner.nextInt();
                expectedGameField.setCell(i, j, value);
            }
        }

        // When
        consumer.accept(computer, gameField);

        // Then
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(expectedGameField.getCell(i, j), gameField.getCell(i, j));
            }
        }
    }
}
