package logic.compute.task;

import game.Cell;
import game.Game;
import game.Position;

import java.util.concurrent.CountDownLatch;

public class MoveDownTask extends MoveTask {

    private final int column;

    public MoveDownTask(final Game game, final CountDownLatch countDownLatch, final int column) {
        super(game, countDownLatch);
        this.column = column;
    }

    @Override
    protected void compute() {
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
}
