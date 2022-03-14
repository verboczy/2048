package logic.compute.movement;

import game.Game;

import java.util.concurrent.CountDownLatch;

public abstract class MoveTask implements Runnable {

    protected int score;
    protected boolean changed;

    protected final Game game;
    private final CountDownLatch countDownLatch;

    public MoveTask(final Game game, final CountDownLatch countDownLatch) {
        this.score = 0;
        this.changed = false;
        this.game = game;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        compute();
        updateGame();
        countDownLatch.countDown();
    }

    protected abstract void compute();

    private void updateGame() {
        if (changed) {
            game.addScore(score);
            game.setChanged(true);
        }
    }
}
