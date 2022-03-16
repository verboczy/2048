package logic.compute.slide;

import game.Game;
import game.Position;

import java.util.concurrent.CountDownLatch;

public class SlideUpTask extends SlideVerticalTask {

    protected SlideUpTask(final Game game, final CountDownLatch countDownLatch, final int columnIndex) {
        super(game, countDownLatch, columnIndex);
    }

    @Override
    protected void updateField(final SlideResult slideResult) {
        for (int rowIndex = 0; rowIndex < game.getFieldSize(); rowIndex++) {
            final Position position = new Position(rowIndex, columnIndex);
            final int newValue = slideResult.getElementsAfterSlide().get(rowIndex);

            updateCell(position, newValue);
        }
    }
}
