package logic.compute.task.factory;

import game.Game;
import logic.compute.task.MoveUpTask;

import java.util.concurrent.CountDownLatch;

public class MoveUpTaskFactory implements MoveTaskFactory {

    @Override
    public MoveUpTask build(final Game game, final CountDownLatch countDownLatch, final int column) {
        return new MoveUpTask(game, countDownLatch, column);
    }
}
