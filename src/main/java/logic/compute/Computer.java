package logic.compute;

import game.Game;

public interface Computer {

    void moveUp(final Game game);

    void moveDown(final Game game);

    void moveRight(final Game game);

    void moveLeft(final Game game);
}
