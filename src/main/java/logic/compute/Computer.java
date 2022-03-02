package logic.compute;

import map.GameField;

public interface Computer {

    void moveUp(final GameField gameField);

    void moveDown(final GameField gameField);

    void moveRight(final GameField gameField);

    void moveLeft(final GameField gameField);
}
