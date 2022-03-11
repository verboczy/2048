package game;

import java.util.Arrays;
import java.util.Objects;

public class GameField {

    private final int size;
    private final int[][] field;

    public GameField(final int size) {
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
        final int row = position.getRow();
        final int column = position.getColumn();
        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(String.format("Invalid row: %d", row));
        } else if (column < 0 || column >= size) {
            throw new IllegalArgumentException(String.format("Invalid column: %d", column));
        } else {
            return field[row][column];
        }
    }

    public boolean isCellEmpty(final Position position) {
        return getCell(position) == 0;
    }

    public void setCell(final Cell cell) {
        final Position position = cell.getPosition();
        final int row = position.getRow();
        final int column = position.getColumn();
        final int newValue = cell.getValue();

        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(String.format("Invalid row: %d", row));
        } else if (column < 0 || column >= size) {
            throw new IllegalArgumentException(String.format("Invalid column: %d", column));
        } else {
            field[row][column] = newValue;
        }
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
