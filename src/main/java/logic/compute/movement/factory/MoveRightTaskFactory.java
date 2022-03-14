package logic.compute.movement.factory;

import game.Game;
import logic.compute.movement.MoveRightTask;
import logic.compute.movement.MoveTask;

import java.util.concurrent.CountDownLatch;

public class MoveRightTaskFactory implements MoveTaskFactory {

    @Override
    public MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position) {
        return new MoveRightTask(game, countDownLatch, position);
    }
}
