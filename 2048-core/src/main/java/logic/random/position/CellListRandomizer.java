package logic.random.position;

import game.Cell;
import game.Game;
import game.Position;
import logic.random.value.ValueRandomizer;

import java.util.List;
import java.util.Random;

public class CellListRandomizer implements CellRandomizer {

    private final Random random;
    private final ValueRandomizer valueRandomizer;

    public CellListRandomizer(final Random random, final ValueRandomizer valueRandomizer) {
        this.random = random;
        this.valueRandomizer = valueRandomizer;
    }

    @Override
    public Cell getRandomNewCell(final Game game) {
        final Position position = getEmptyCellPosition(game);
        final int value = getRandomValue();
        return new Cell(position, value);
    }

    private Position getEmptyCellPosition(final Game game) {
        final List<Position> emptyCellPositions = game.getEmptyCellPositions();
        if (emptyCellPositions.isEmpty()) {
            throw new IllegalStateException("No more empty cells.");
        } else {
            final int randomIndex = random.nextInt(emptyCellPositions.size());
            return emptyCellPositions.get(randomIndex);
        }
    }

    private int getRandomValue() {
        return valueRandomizer.getRandomValue();
    }
}
