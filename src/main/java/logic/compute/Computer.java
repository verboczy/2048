package logic.compute;

import map.GameField;
import map.Movement;

public interface Computer {

    Movement moveUp(final GameField gameField);

    Movement moveDown(final GameField gameField);

    Movement moveRight(final GameField gameField);

    Movement moveLeft(final GameField gameField);
}
