package logic.compute.movement.factory;

import game.Game;
import logic.compute.movement.MoveTask;

import java.util.concurrent.CountDownLatch;

public interface MoveTaskFactory {

    MoveTask build(final Game game, final CountDownLatch countDownLatch, final int position);
}
