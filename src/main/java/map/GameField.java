package map;

public class GameField {

    private final int size;
    private final int[][] field;

    public GameField(final int size) {
        this.size = size;

        field = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = 0;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getCell(final int row, final int column) {
        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(String.format("Invalid row: %d", row));
        } else if (column < 0 || column >= size) {
            throw new IllegalArgumentException(String.format("Invalid column: %d", column));
        } else {
            return field[row][column];
        }
    }

    public void setCell(final Cell cell) {
        final int row = cell.getRow();
        final int column = cell.getColumn();
        final int newValue = cell.getValue();

        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(String.format("Invalid row: %d", row));
        } else if (column < 0 || column >= size) {
            throw new IllegalArgumentException(String.format("Invalid column: %d", column));
        } else {
            field[row][column] = newValue;
        }
    }

}
