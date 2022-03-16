package logic.compute.slide;

import game.Game;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class SlideVerticalTask extends SlideTask {

    protected final int columnIndex;

    protected SlideVerticalTask(final Game game, final CountDownLatch countDownLatch, final int columnIndex) {
        super(game, countDownLatch);
        this.columnIndex = columnIndex;
    }

    @Override
    protected List<Integer> extractElementsFromGame() {
        return game.getColumn(columnIndex);
    }
}
