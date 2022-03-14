package logic.compute.movement;

import game.Game;

public interface MovementComputer {

    void moveUp(final Game game);

    void moveDown(final Game game);

    void moveRight(final Game game);

    void moveLeft(final Game game);
}
