package logic.random.position;

import map.Cell;
import map.GameField;

public interface CellRandomizer {

    Cell getRandomNewCell(final GameField gameField);

}
