package logic.random.position;

import game.Cell;
import game.Game;

public interface CellRandomizer {

    Cell getRandomNewCell(final Game game);

}
