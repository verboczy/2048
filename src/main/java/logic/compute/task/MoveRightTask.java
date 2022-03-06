package logic.compute.task;

import game.Cell;
import game.Game;
import game.Position;

import java.util.concurrent.CountDownLatch;


public class MoveRightTask extends MoveTask {

    private final int row;

    public MoveRightTask(final Game game, final CountDownLatch countDownLatch, final int row) {
        super(game, countDownLatch);
        this.row = row;
    }

    @Override
    protected void compute() {
        boolean merged = false;
        for (int column = game.getFieldSize() - 2; column >= 0; column--) {
            final int currentCell = game.getCell(new Position(row, column));
            if (currentCell == 0) {
                continue;
            }

            int atRightColumn = column + 1;
            // Move up the value if there is no value above it.
            while (atRightColumn <= game.getFieldSize() - 1 && game.getCell(new Position(row, atRightColumn)) == 0) {
                game.setCell(new Cell(row, atRightColumn, currentCell));
                game.setCell(new Cell(row, atRightColumn - 1, 0));
                atRightColumn++;
                changed = true;
            }
            // There is an element to the right in the row.
            if (atRightColumn <= game.getFieldSize() - 1) {
                // Merge it, if it is possible.
                final int cellAtRight = game.getCell(new Position(row, atRightColumn));
                if (!merged && cellAtRight == currentCell) {
                    final int newValue = cellAtRight * 2;
                    game.setCell(new Cell(row, atRightColumn, newValue));
                    game.setCell(new Cell(row, atRightColumn - 1, 0));
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
