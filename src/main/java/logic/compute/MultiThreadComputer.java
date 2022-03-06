package logic.compute;

import game.Game;
import logic.compute.task.factory.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class MultiThreadComputer implements Computer {

    private final ExecutorService executor;

    public MultiThreadComputer(final ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void moveUp(final Game game) {
        move(game, new MoveUpTaskFactory());
    }

    @Override
    public void moveDown(final Game game) {
        move(game, new MoveDownTaskFactory());
    }

    @Override
    public void moveRight(final Game game) {
        move(game, new MoveRightTaskFactory());
    }

    @Override
    public void moveLeft(final Game game) {
        move(game, new MoveLeftTaskFactory());
    }

    private void move(final Game game, final MoveTaskFactory moveTaskFactory) {
        final CountDownLatch countDownLatch = new CountDownLatch(game.getFieldSize());
        startTasks(game, countDownLatch, moveTaskFactory);
        waitForTasksToFinish(countDownLatch);
    }

    private void startTasks(final Game game, final CountDownLatch countDownLatch, final MoveTaskFactory moveTaskFactory) {
        for (int i = 0; i < game.getFieldSize(); i++) {
            executor.execute(moveTaskFactory.build(game, countDownLatch, i));
        }
    }

    private void waitForTasksToFinish(final CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (final InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
