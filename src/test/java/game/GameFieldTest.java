package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GameFieldTest {

    private static Stream<Arguments> validPositionsForSizeTwo() {
        return Stream.of(
                Arguments.of(new Position(0, 0)),
                Arguments.of(new Position(0, 1)),
                Arguments.of(new Position(1, 0)),
                Arguments.of(new Position(1, 1))
        );
    }

    private static Stream<Arguments> validSizeStreamForField() {
        final List<Arguments> validSizeList = new ArrayList<>();
        for (int size = GameSizeLimit.MINIMUM_SIZE; size <= GameSizeLimit.MAXIMUM_SIZE; size++) {
            validSizeList.add(Arguments.of(size));
        }
        return validSizeList.stream();
    }

    private static Stream<Arguments> validCells() {
        return Stream.of(
                Arguments.of(new Position(0, 0), 2),
                Arguments.of(new Position(0, 1), 4),
                Arguments.of(new Position(1, 0), 8),
                Arguments.of(new Position(1, 1), 16)
        );
    }

    private static Stream<Arguments> invalidIndexes() {
        return Stream.of(
                Arguments.of(2, new Position(-1, 2)),
                Arguments.of(2, new Position(1, -2)),
                Arguments.of(2, new Position(-1, -2)),
                Arguments.of(2, new Position(3, 2)),
                Arguments.of(2, new Position(2, 3)),
                Arguments.of(2, new Position(3, 3)),
                Arguments.of(GameSizeLimit.MAXIMUM_SIZE, new Position(GameSizeLimit.MAXIMUM_SIZE + 1, 0)),
                Arguments.of(GameSizeLimit.MAXIMUM_SIZE, new Position(0, GameSizeLimit.MAXIMUM_SIZE + 1)),
                Arguments.of(GameSizeLimit.MAXIMUM_SIZE, new Position(GameSizeLimit.MAXIMUM_SIZE + 1, GameSizeLimit.MAXIMUM_SIZE + 1))
        );
    }

    private static Stream<Arguments> rowParameters() {
        final GameField field = setUpField();
        return Stream.of(
                Arguments.of(field, 0, List.of(0, 2)),
                Arguments.of(field, 1, List.of(4, 8))
        );
    }

    private static Stream<Arguments> invalidRowParameters() {
        return Stream.of(
                Arguments.of(2, -1),
                Arguments.of(2, 2)
        );
    }

    private static Stream<Arguments> invalidColumnParameters() {
        // This time column and row behaves the same way, but I couldn't find a common name for them.
        // So, I separated them this way, to keep naming clear.
        return invalidRowParameters();
    }

    private static Stream<Arguments> columnParameters() {
        final GameField field = setUpField();
        return Stream.of(
                Arguments.of(field, 0, List.of(0, 4)),
                Arguments.of(field, 1, List.of(2, 8))
        );
    }

    private static GameField setUpField() {
        GameField field = new GameField(2);
        field.setCell(new Cell(new Position(0, 0), 0));
        field.setCell(new Cell(new Position(0, 1), 2));
        field.setCell(new Cell(new Position(1, 0), 4));
        field.setCell(new Cell(new Position(1, 1), 8));
        return field;
    }

    @DisplayName("Initialized field is empty")
    @ParameterizedTest(name = "Case{index} - {0}")
    @MethodSource("validPositionsForSizeTwo")
    void initializedGameField_allCellIsEmpty(final Position position) {
        final GameField givenGameField = new GameField(2);

        assertTrue(givenGameField.isCellEmpty(position));
    }

    @DisplayName("Field size doesn't change at creation")
    @ParameterizedTest(name = "Case{index} - size = {0}")
    @MethodSource("validSizeStreamForField")
    void initializedGameField_size(final int size) {
        final GameField givenGameField = new GameField(size);

        final int actualSize = givenGameField.getSize();

        assertEquals(size, actualSize);
    }

    @DisplayName("Can't create too small field")
    @ParameterizedTest(name = "Case{index} - size = {0}")
    @ValueSource(ints = {1, 0, -1, Integer.MIN_VALUE})
    void initializeGameField_tooLowSize(final int size) {
        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new GameField(size)
        );
        assertEquals(getTooLowSizeExceptionMessage(size), exception.getMessage());
    }

    private String getTooLowSizeExceptionMessage(final int size) {
        return String.format("The minimum size is %d, so %d is not valid.", GameSizeLimit.MINIMUM_SIZE, size);
    }

    @DisplayName("Can't create too large field")
    @ParameterizedTest(name = "Case{index} - size = {0}")
    @ValueSource(ints = {GameSizeLimit.MAXIMUM_SIZE + 1, Integer.MAX_VALUE})
    void initializeGameField_tooGreatSize(final int size) {
        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new GameField(size)
        );
        assertEquals(getTooGreatSizeExceptionMessage(size), exception.getMessage());
    }

    private String getTooGreatSizeExceptionMessage(final int size) {
        return String.format("The maximum size is %d, so %d is not valid.", GameSizeLimit.MAXIMUM_SIZE, size);
    }

    @DisplayName("Cell returns the set value")
    @ParameterizedTest(name = "Case{index} - {0} value = {1}")
    @MethodSource("validCells")
    void getCell_validIndexes(final Position position, final int value) {
        final GameField gameField = new GameField(2);
        gameField.setCell(new Cell(position, value));

        final int actualValue = gameField.getCell(position);

        assertEquals(value, actualValue);
    }

    @DisplayName("Get cell throws exception when index is invalid")
    @ParameterizedTest(name = "Case{index} - size = {0}, invalid position = {1}")
    @MethodSource("invalidIndexes")
    void getCell_invalidIndexes(final int size, final Position position) {
        final GameField gameField = new GameField(size);

        assertThrows(IllegalArgumentException.class, () -> gameField.getCell(position));
    }

    @DisplayName("Set cell throws exception when index is invalid")
    @ParameterizedTest(name = "Case{index} - size = {0}, invalid position = {1}")
    @MethodSource("invalidIndexes")
    void setCell_invalidIndexes(final int size, final Position position) {
        final GameField gameField = new GameField(size);
        final Cell cell = new Cell(position, 2);

        assertThrows(IllegalArgumentException.class, () -> gameField.setCell(cell));
    }

    @DisplayName("Can get row from field")
    @ParameterizedTest(name = "Case{index} - row{1}: {2}")
    @MethodSource("rowParameters")
    void getRow_validCases(final GameField gameField, final int rowIndex, final List<Integer> expectedRow) {
        final List<Integer> actualRow = gameField.getRow(rowIndex);
        assertEquals(expectedRow, actualRow);
    }

    @DisplayName("Get row throws exception when called with invalid index")
    @ParameterizedTest(name = "Case{index} - size = {0}, row index = {1}")
    @MethodSource("invalidRowParameters")
    void getRow_invalidCases(final int size, final int rowIndex) {
        final GameField field = new GameField(size);

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> field.getRow(rowIndex)
        );
        assertEquals(String.format("Invalid row index: %d", rowIndex), exception.getMessage());
    }

    @DisplayName("Can get column from field")
    @ParameterizedTest(name = "Case{index} - column{1}: {2}")
    @MethodSource("columnParameters")
    void getColumn_validCases(final GameField gameField, final int columnIndex, final List<Integer> expectedColumn) {
        final List<Integer> actualColumn = gameField.getColumn(columnIndex);
        assertEquals(expectedColumn, actualColumn);
    }

    @ParameterizedTest
    @MethodSource("invalidColumnParameters")
    void getColumn_invalidCases() {

    }

    @DisplayName("Get column throws exception when called with invalid index")
    @ParameterizedTest(name = "Case{index} - size = {0}, column index = {1}")
    @MethodSource("invalidColumnParameters")
    void getColumn_invalidCases(final int size, final int columnIndex) {
        final GameField field = new GameField(size);

        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> field.getColumn(columnIndex)
        );
        assertEquals(String.format("Invalid column index: %d", columnIndex), exception.getMessage());
    }
}
