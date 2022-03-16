package logic.compute.slide.task;

import game.Game;
import game.Position;
import logic.compute.slide.dto.SlideResult;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SlideRightTask extends SlideHorizontalTask {

    public SlideRightTask(final Game game, final CountDownLatch countDownLatch, final int rowIndex) {
        super(game, countDownLatch, rowIndex);
    }

    @Override
    protected List<Integer> extractElementsFromGame() {
        final List<Integer> row = game.getRow(rowIndex);
        Collections.reverse(row);
        return row;
    }

    @Override
    protected void updateField(final SlideResult slideResult) {
        final List<Integer> elementsAfterSlide = slideResult.getElementsAfterSlide();
        Collections.reverse(elementsAfterSlide);

        for (int columnIndex = 0; columnIndex < game.getFieldSize(); columnIndex++) {
            final Position position = new Position(rowIndex, columnIndex);
            final int newValue = slideResult.getElementsAfterSlide().get(columnIndex);

            updateCell(position, newValue);
        }
    }

}
