package logic.compute.slide.task;

import game.Game;
import game.Position;
import logic.compute.slide.dto.SlideResult;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SlideDownTask extends SlideVerticalTask {

    protected SlideDownTask(final Game game, final CountDownLatch countDownLatch, final int columnIndex) {
        super(game, countDownLatch, columnIndex);
    }

    @Override
    protected List<Integer> extractElementsFromGame() {
        final List<Integer> column = game.getColumn(columnIndex);
        Collections.reverse(column);
        return column;
    }

    @Override
    protected void updateField(final SlideResult slideResult) {
        final List<Integer> elementsAfterSlide = slideResult.getElementsAfterSlide();
        Collections.reverse(elementsAfterSlide);

        for (int rowIndex = 0; rowIndex < game.getFieldSize(); rowIndex++) {
            final Position position = new Position(rowIndex, columnIndex);
            final int newValue = elementsAfterSlide.get(rowIndex);

            updateCell(position, newValue);
        }
    }

}
