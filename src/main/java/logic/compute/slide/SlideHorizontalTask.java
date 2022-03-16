package logic.compute.slide;

import game.Game;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class SlideHorizontalTask extends SlideTask {

    protected final int rowIndex;

    protected SlideHorizontalTask(final Game game, final CountDownLatch countDownLatch, final int rowIndex) {
        super(game, countDownLatch);
        this.rowIndex = rowIndex;
    }

    @Override
    protected List<Integer> extractElementsFromGame() {
        return game.getRow(rowIndex);
    }
}
