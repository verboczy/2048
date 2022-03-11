package logic.compute.task;

import game.Cell;
import game.Game;
import game.Position;

import java.util.concurrent.CountDownLatch;

public class MoveLeftTask extends MoveTask {

    private final int row;

    public MoveLeftTask(final Game game, final CountDownLatch countDownLatch, final int row) {
        super(game, countDownLatch);
        this.row = row;
    }

    @Override
    protected void compute() {
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
}
