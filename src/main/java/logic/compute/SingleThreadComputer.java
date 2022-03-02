package logic.compute;

import map.Cell;
import map.GameField;

public class SingleThreadComputer implements Computer {

    @Override
    public void moveUp(final GameField gameField) {
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
                    gameField.setCell(new Cell(upperRow, column, currentCell));
                    gameField.setCell(new Cell(upperRow + 1, column, 0));
                    upperRow--;
                }
                // There is an element above in the column.
                if (upperRow >= 0) {
                    // Merge it, if it is possible.
                    final int upperCell = gameField.getCell(upperRow, column);
                    if (!merged && upperCell == currentCell) {
                        final int newValue = upperCell * 2;
                        gameField.setCell(new Cell(upperRow, column, newValue));
                        gameField.setCell(new Cell(upperRow + 1, column, 0));
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    @Override
    public void moveDown(final GameField gameField) {
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
                    gameField.setCell(new Cell(lowerRow, column, currentCell));
                    gameField.setCell(new Cell(lowerRow - 1, column, 0));
                    lowerRow++;
                }
                // There is an element below in the column.
                if (lowerRow <= gameField.getSize() - 1) {
                    // Merge it, if it is possible.
                    final int lowerCell = gameField.getCell(lowerRow, column);
                    if (!merged && lowerCell == currentCell) {
                        final int newValue = lowerCell * 2;
                        gameField.setCell(new Cell(lowerRow, column, newValue));
                        gameField.setCell(new Cell(lowerRow - 1, column, 0));
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    @Override
    public void moveRight(final GameField gameField) {
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
                    gameField.setCell(new Cell(row, atRightColumn, currentCell));
                    gameField.setCell(new Cell(row, atRightColumn - 1, 0));
                    atRightColumn++;
                }
                // There is an element to the right in the row.
                if (atRightColumn <= gameField.getSize() - 1) {
                    // Merge it, if it is possible.
                    final int cellAtRight = gameField.getCell(row, atRightColumn);
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        gameField.setCell(new Cell(row, atRightColumn, newValue));
                        gameField.setCell(new Cell(row, atRightColumn - 1, 0));
                        merged = true;
                        // TODO count score
                    } else {
                        merged = false;
                    }
                }
            }
        }
    }

    @Override
    public void moveLeft(final GameField gameField) {
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
                    gameField.setCell(new Cell(row, atLeftColumn, currentCell));
                    gameField.setCell(new Cell(row, atLeftColumn + 1, 0));
                    atLeftColumn--;
                }
                // There is an element to the right in the row.
                if (atLeftColumn >= 0) {
                    // Merge it, if it is possible.
                    final int cellAtRight = gameField.getCell(row, atLeftColumn);
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        gameField.setCell(new Cell(row, atLeftColumn, newValue));
                        gameField.setCell(new Cell(row, atLeftColumn + 1, 0));
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
