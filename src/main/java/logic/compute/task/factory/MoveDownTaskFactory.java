package logic.compute.task.factory;

import game.Game;
import logic.compute.task.MoveDownTask;
import logic.compute.task.MoveTask;

import java.util.concurrent.CountDownLatch;

public class MoveDownTaskFactory implements MoveTaskFactory {

    @Override
    public MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position) {
        return new MoveDownTask(game, countDownLatch, position);
    }
}
