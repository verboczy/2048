package logic.random.position;

import logic.random.value.ValueRandomizer;
import map.Cell;
import map.GameField;

import java.util.ArrayList;
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
    public Cell getRandomNewCell(final GameField gameField) {
        final List<Cell> emptyCells = new ArrayList<>();

        for (int row = 0; row < gameField.getSize(); row++) {
            for (int column = 0; column < gameField.getSize(); column++) {
                if (gameField.getCell(row, column) == 0) {
                    emptyCells.add(new Cell(row, column, valueRandomizer.getRandomValue()));
                }
            }
        }

        if (emptyCells.isEmpty()) {
            throw new IllegalStateException("No more empty cells.");
        } else {
            final int randomIndex = random.nextInt(emptyCells.size());
            return emptyCells.get(randomIndex);
        }
    }
}
