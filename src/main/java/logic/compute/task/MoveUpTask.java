package logic.compute.task;

import game.Cell;
import game.Game;
import game.Position;

import java.util.concurrent.CountDownLatch;

public class MoveUpTask extends MoveTask {

    private final int column;

    public MoveUpTask(final Game game, final CountDownLatch countDownLatch, final int column) {
        super(game, countDownLatch);
        this.column = column;
    }

    @Override
    protected void compute() {
        boolean merged = false;
        for (int row = 1; row < game.getFieldSize(); row++) {
            final int currentCell = game.getCell(new Position(row, column));
            if (currentCell == 0) {
                continue;
            }

            int upperRow = row - 1;
            // Move up the value if there is no value above it.
            while (upperRow >= 0 && game.getCell(new Position(upperRow, column)) == 0) {
                game.setCell(new Cell(upperRow, column, currentCell));
                game.setCell(new Cell(upperRow + 1, column, 0));
                upperRow--;
                changed = true;
            }
            // There is an element above in the column.
            if (upperRow >= 0) {
                // Merge it, if it is possible.
                final int upperCell = game.getCell(new Position(upperRow, column));
                if (!merged && upperCell == currentCell) {
                    final int newValue = upperCell * 2;
                    game.setCell(new Cell(upperRow, column, newValue));
                    game.setCell(new Cell(upperRow + 1, column, 0));
                    merged = true;
                    changed = true;
                    score += newValue;
                } else {
                    merged = false;
                }
            }
        }
    }
}
