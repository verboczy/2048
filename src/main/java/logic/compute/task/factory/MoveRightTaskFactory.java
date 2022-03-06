package logic.compute.task.factory;

import game.Game;
import logic.compute.task.MoveRightTask;
import logic.compute.task.MoveTask;

import java.util.concurrent.CountDownLatch;

public class MoveRightTaskFactory implements MoveTaskFactory {

    @Override
    public MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position) {
        return new MoveRightTask(game, countDownLatch, position);
    }
}
