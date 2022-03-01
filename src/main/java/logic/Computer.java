package logic;

import map.GameField;

public class Computer {

    public void up(final GameField gameField) {
        for (int column = 0; column < gameField.getSize(); column++) {
            boolean merged = false;
            for (int row = 1; row < gameField.getSize(); row++) {
                final int currentCell = gameField.getCell(row, column);
                if (currentCell == 0) {
                    continue;
                }

                int upperRow = row - 1;
                // Move up the value if there is no value above it.
                while (upperRow >= 0 && gameField.getCell(upperRow, column) == 0) {
                    gameField.setCell(upperRow, column, currentCell);
                    gameField.setCell(upperRow + 1, column, 0);
                    upperRow--;
                }
                // There is an element above in the column.
                if (upperRow >= 0) {
                    // Merge it, if it is possible.
                    final int upperCell = gameField.getCell(upperRow, column);
                    if (!merged && upperCell == currentCell) {
                        final int newValue = upperCell * 2;
                        gameField.setCell(upperRow, column, newValue);
                        gameField.setCell(upperRow + 1, column, 0);
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    public void down(final GameField gameField) {
        for (int column = 0; column < gameField.getSize(); column++) {
            boolean merged = false;
            for (int row = gameField.getSize() - 2; row >= 0; row--) {
                final int currentCell = gameField.getCell(row, column);
                if (currentCell == 0) {
                    continue;
                }

                int lowerRow = row + 1;
                // Move up the value if there is no value above it.
                while (lowerRow <= gameField.getSize() - 1 && gameField.getCell(lowerRow, column) == 0) {
                    gameField.setCell(lowerRow, column, currentCell);
                    gameField.setCell(lowerRow - 1, column, 0);
                    lowerRow++;
                }
                // There is an element below in the column.
                if (lowerRow <= gameField.getSize() - 1) {
                    // Merge it, if it is possible.
                    final int lowerCell = gameField.getCell(lowerRow, column);
                    if (!merged && lowerCell == currentCell) {
                        final int newValue = lowerCell * 2;
                        gameField.setCell(lowerRow, column, newValue);
                        gameField.setCell(lowerRow - 1, column, 0);
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    public void right(final GameField gameField) {
        for (int row = 0; row < gameField.getSize(); row++) {
            boolean merged = false;
            for (int column = gameField.getSize() - 2; column >= 0; column--) {
                final int currentCell = gameField.getCell(row, column);
                if (currentCell == 0) {
                    continue;
                }

                int atRightColumn = column + 1;
                // Move up the value if there is no value above it.
                while (atRightColumn <= gameField.getSize() - 1 && gameField.getCell(row, atRightColumn) == 0) {
                    gameField.setCell(row, atRightColumn, currentCell);
                    gameField.setCell(row, atRightColumn - 1, 0);
                    atRightColumn++;
                }
                // There is an element to the right in the row.
                if (atRightColumn <= gameField.getSize() - 1) {
                    // Merge it, if it is possible.
                    final int cellAtRight = gameField.getCell(row, atRightColumn);
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        gameField.setCell(row, atRightColumn, newValue);
                        gameField.setCell(row, atRightColumn - 1, 0);
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    public void left(final GameField gameField) {
        for (int row = 0; row < gameField.getSize(); row++) {
            boolean merged = false;
            for (int column = 1; column < gameField.getSize(); column++) {
                final int currentCell = gameField.getCell(row, column);
                if (currentCell == 0) {
                    continue;
                }

                int atLeftColumn = column - 1;
                // Move up the value if there is no value above it.
                while (atLeftColumn >= 0 && gameField.getCell(row, atLeftColumn) == 0) {
                    gameField.setCell(row, atLeftColumn, currentCell);
                    gameField.setCell(row, atLeftColumn + 1, 0);
                    atLeftColumn--;
                }
                // There is an element to the right in the row.
                if (atLeftColumn >= 0) {
                    // Merge it, if it is possible.
                    final int cellAtRight = gameField.getCell(row, atLeftColumn);
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        gameField.setCell(row, atLeftColumn, newValue);
                        gameField.setCell(row, atLeftColumn + 1, 0);
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

}
