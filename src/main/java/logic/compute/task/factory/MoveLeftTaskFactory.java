package logic.compute.task.factory;

import game.Game;
import logic.compute.task.MoveLeftTask;
import logic.compute.task.MoveTask;

import java.util.concurrent.CountDownLatch;

public class MoveLeftTaskFactory implements MoveTaskFactory {

    @Override
    public MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position) {
        return new MoveLeftTask(game, countDownLatch, position);
    }
}
