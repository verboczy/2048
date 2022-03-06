package logic.random.position;

import game.Cell;
import game.Game;
import game.GameField;

public interface CellRandomizer {

    Cell getRandomNewCell(final Game game);

}
