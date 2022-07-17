package logic.compute.slide.task;

import game.Game;
import game.Position;
import logic.compute.slide.dto.SlideResult;

import java.util.concurrent.CountDownLatch;

public class SlideLeftTask extends SlideHorizontalTask {

    protected SlideLeftTask(final Game game, final CountDownLatch countDownLatch, final int rowIndex) {
        super(game, countDownLatch, rowIndex);
    }

    @Override
    protected void updateField(final SlideResult slideResult) {
        for (int columnIndex = 0; columnIndex < game.getFieldSize(); columnIndex++) {
            final Position position = new Position(rowIndex, columnIndex);
            final int newValue = slideResult.getElementsAfterSlide().get(columnIndex);

            updateCell(position, newValue);
        }
    }
}
