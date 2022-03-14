package logic.compute.movement.factory;

import game.Game;
import logic.compute.movement.MoveDownTask;
import logic.compute.movement.MoveTask;

import java.util.concurrent.CountDownLatch;

public class MoveDownTaskFactory implements MoveTaskFactory {

    @Override
    public MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position) {
        return new MoveDownTask(game, countDownLatch, position);
    }
}
