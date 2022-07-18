package queue;

import game.Game;

public interface OutputQueue {

    void addResult(final Game game);

    Game getResult() throws InterruptedException;

    void clear();
}
