package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameField {
    private static final String ROW_TYPE = "row";
    private static final String COLUMN_TYPE = "column";

    private final int size;
    private final int[][] field;

    public GameField(final int size) {
        GameSizeLimit.validateSize(size);
        this.size = size;

        field = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                field[row][column] = 0;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getCell(final Position position) {
        final int rowIndex = validateRowIndex(position.getRow());
        final int columnIndex = validateColumnIndex(position.getColumn());
        return field[rowIndex][columnIndex];
    }

    public boolean isCellEmpty(final Position position) {
        return getCell(position) == 0;
    }

    public void setCell(final Cell cell) {
        final Position position = cell.getPosition();

        final int rowIndex = validateRowIndex(position.getRow());
        final int columnIndex = validateColumnIndex(position.getColumn());
        final int newValue = cell.getValue();

        field[rowIndex][columnIndex] = newValue;
    }

    public List<Integer> getRow(final int rowIndex) {
        final int validatedRowIndex = validateRowIndex(rowIndex);

        final List<Integer> row = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < size; columnIndex++) {
            row.add(field[validatedRowIndex][columnIndex]);
        }

        return row;
    }

    public List<Integer> getColumn(final int columnIndex) {
        final int validatedColumnIndex = validateColumnIndex(columnIndex);

        final List<Integer> column = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            column.add(field[rowIndex][validatedColumnIndex]);
        }

        return column;
    }

    private int validateRowIndex(final int rowIndex) {
        return validateIndex(rowIndex, ROW_TYPE);
    }

    private int validateColumnIndex(final int columnIndex) {
        return validateIndex(columnIndex, COLUMN_TYPE);
    }

    private int validateIndex(final int index, final String type) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(String.format("Invalid %s index: %d", type, index));
        }
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameField gameField = (GameField) o;
        if (this.size != gameField.size) {
            return false;
        }
        for (int row = 0; row < this.size; row++) {
            for (int column = 0; column < this.size; column++) {
                if (this.field[row][column] != gameField.getCell(new Position(row, column))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.deepHashCode(field);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GameField{size=").append(size).append(", field=\n");
        for (int row = 0; row < this.size; row++) {
            for (int column = 0; column < this.size; column++) {
                stringBuilder.append(this.field[row][column]).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
