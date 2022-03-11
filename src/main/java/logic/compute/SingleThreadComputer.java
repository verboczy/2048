package logic.compute;

import game.Cell;
import game.Game;
import game.Position;

public class SingleThreadComputer implements Computer {

    @Override
    public void moveUp(final Game game) {
        boolean changed = false;
        int score = 0;
        for (int column = 0; column < game.getFieldSize(); column++) {
            boolean merged = false;
            for (int row = 1; row < game.getFieldSize(); row++) {
                final int currentCell = game.getCell(new Position(row, column));
                if (currentCell == 0) {
                    continue;
                }

                int upperRow = row - 1;
                // Move up the value if there is no value above it.
                while (upperRow >= 0 && game.getCell(new Position(upperRow, column)) == 0) {
                    game.setCell(new Cell(new Position(upperRow, column), currentCell));
                    game.setCell(new Cell(new Position(upperRow + 1, column), 0));
                    upperRow--;
                    changed = true;
                }
                // There is an element above in the column.
                if (upperRow >= 0) {
                    // Merge it, if it is possible.
                    final int upperCell = game.getCell(new Position(upperRow, column));
                    if (!merged && upperCell == currentCell) {
                        final int newValue = upperCell * 2;
                        game.setCell(new Cell(new Position(upperRow, column), newValue));
                        game.setCell(new Cell(new Position(upperRow + 1, column), 0));
                        merged = true;
                        changed = true;
                        score += newValue;
                    } else {
                        merged = false;
                    }
                }
            }
        }

        updateGame(game, changed, score);
    }

    @Override
    public void moveDown(final Game game) {
        boolean changed = false;
        int score = 0;
        for (int column = 0; column < game.getFieldSize(); column++) {
            boolean merged = false;
            for (int row = game.getFieldSize() - 2; row >= 0; row--) {
                final int currentCell = game.getCell(new Position(row, column));
                if (currentCell == 0) {
                    continue;
                }

                int lowerRow = row + 1;
                // Move up the value if there is no value above it.
                while (lowerRow <= game.getFieldSize() - 1 && game.getCell(new Position(lowerRow, column)) == 0) {
                    game.setCell(new Cell(new Position(lowerRow, column), currentCell));
                    game.setCell(new Cell(new Position(lowerRow - 1, column), 0));
                    lowerRow++;
                    changed = true;
                }
                // There is an element below in the column.
                if (lowerRow <= game.getFieldSize() - 1) {
                    // Merge it, if it is possible.
                    final int lowerCell = game.getCell(new Position(lowerRow, column));
                    if (!merged && lowerCell == currentCell) {
                        final int newValue = lowerCell * 2;
                        game.setCell(new Cell(new Position(lowerRow, column), newValue));
                        game.setCell(new Cell(new Position(lowerRow - 1, column), 0));
                        merged = true;
                        changed = true;
                        score += newValue;
                    } else {
                        merged = false;
                    }
                }
            }
        }

        updateGame(game, changed, score);
    }

    @Override
    public void moveRight(final Game game) {
        boolean changed = false;
        int score = 0;
        for (int row = 0; row < game.getFieldSize(); row++) {
            boolean merged = false;
            for (int column = game.getFieldSize() - 2; column >= 0; column--) {
                final int currentCell = game.getCell(new Position(row, column));
                if (currentCell == 0) {
                    continue;
                }

                int atRightColumn = column + 1;
                // Move up the value if there is no value above it.
                while (atRightColumn <= game.getFieldSize() - 1 && game.getCell(new Position(row, atRightColumn)) == 0) {
                    game.setCell(new Cell(new Position(row, atRightColumn), currentCell));
                    game.setCell(new Cell(new Position(row, atRightColumn - 1), 0));
                    atRightColumn++;
                    changed = true;
                }
                // There is an element to the right in the row.
                if (atRightColumn <= game.getFieldSize() - 1) {
                    // Merge it, if it is possible.
                    final int cellAtRight = game.getCell(new Position(row, atRightColumn));
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        game.setCell(new Cell(new Position(row, atRightColumn), newValue));
                        game.setCell(new Cell(new Position(row, atRightColumn - 1), 0));
                        merged = true;
                        changed = true;
                        score += newValue;
                    } else {
                        merged = false;
                    }
                }
            }
        }

        updateGame(game, changed, score);
    }

    @Override
    public void moveLeft(final Game game) {
        boolean changed = false;
        int score = 0;
        for (int row = 0; row < game.getFieldSize(); row++) {
            boolean merged = false;
            for (int column = 1; column < game.getFieldSize(); column++) {
                final int currentCell = game.getCell(new Position(row, column));
                if (currentCell == 0) {
                    continue;
                }

                int atLeftColumn = column - 1;
                // Move up the value if there is no value above it.
                while (atLeftColumn >= 0 && game.getCell(new Position(row, atLeftColumn)) == 0) {
                    game.setCell(new Cell(new Position(row, atLeftColumn), currentCell));
                    game.setCell(new Cell(new Position(row, atLeftColumn + 1), 0));
                    atLeftColumn--;
                    changed = true;
                }
                // There is an element to the right in the row.
                if (atLeftColumn >= 0) {
                    // Merge it, if it is possible.
                    final int cellAtRight = game.getCell(new Position(row, atLeftColumn));
                    if (!merged && cellAtRight == currentCell) {
                        final int newValue = cellAtRight * 2;
                        game.setCell(new Cell(new Position(row, atLeftColumn), newValue));
                        game.setCell(new Cell(new Position(row, atLeftColumn + 1), 0));
                        merged = true;
                        changed = true;
                        score += newValue;
                    } else {
                        merged = false;
                    }
                }
            }
        }

        updateGame(game, changed, score);
    }

    private void updateGame(final Game game, final boolean changed, final int score) {
        if (changed) {
            game.addScore(score);
            game.setChanged(true);
        }
    }
}
