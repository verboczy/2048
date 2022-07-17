package logic.compute.slide.task;

import game.Cell;
import game.Game;
import game.Position;
import logic.compute.slide.SlideElementsComputer;
import logic.compute.slide.dto.SlideResult;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class SlideTask implements Runnable {

    protected final Game game;
    private final CountDownLatch countDownLatch;

    protected SlideTask(final Game game, final CountDownLatch countDownLatch) {
        this.game = game;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        final List<Integer> elements = extractElementsFromGame();
        final SlideResult slideResult = slide(elements);
        updateGameBySlideResult(slideResult);
        countDownLatch.countDown();
    }

    protected abstract List<Integer> extractElementsFromGame();

    private SlideResult slide(final List<Integer> elements) {
        final SlideElementsComputer slideElementsComputer = new SlideElementsComputer(elements);
        return slideElementsComputer.compute();
    }

    protected void updateGameBySlideResult(final SlideResult slideResult) {
        updateField(slideResult);
        updateScore(slideResult);
        updateIsGameChanged(slideResult);
    }

    protected abstract void updateField(final SlideResult slideResult);

    protected void updateCell(final Position position, final int value) {
        game.setCell(new Cell(position, value));
    }

    private void updateScore(final SlideResult slideResult) {
        final int scoreOfSlide = slideResult.getScoreOfSlide();
        game.addScore(scoreOfSlide);
    }

    private void updateIsGameChanged(final SlideResult slideResult) {
        if (slideResult.hasChanged()) {
            game.setChanged(true);
        }
    }
}
