package queue;

import game.Game;

import java.util.concurrent.BlockingQueue;

public class ConsoleOutputQueue implements OutputQueue {

    final BlockingQueue<Game> gameQueue;

    public ConsoleOutputQueue(final BlockingQueue<Game> gameQueue) {
        this.gameQueue = gameQueue;
    }

    @Override
    public void addResult(final Game game) {
        gameQueue.add(game);
    }

    @Override
    public Game getResult() throws InterruptedException {
        return gameQueue.take();
    }

    @Override
    public void clear() {
        gameQueue.clear();
    }
}
