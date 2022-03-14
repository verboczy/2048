package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private int score;
    private boolean changed;
    private final GameField gameField;

    public Game(GameField gameField) {
        this.score = 0;
        this.changed = false;
        this.gameField = gameField;
    }

    public synchronized int getScore() {
        return this.score;
    }

    public synchronized void addScore(final int plusScore) {
        this.score += plusScore;
    }

    public synchronized boolean isChanged() {
        return this.changed;
    }

    public synchronized void setChanged(final boolean changed) {
        this.changed = changed;
    }

    public int getCell(final Position position) {
        return gameField.getCell(position);
    }

    public void setCell(final Cell cell) {
        gameField.setCell(cell);
    }

    public int getFieldSize() {
        return gameField.getSize();
    }

    public List<Position> getEmptyCellPositions() {
        final List<Position> emptyCells = new ArrayList<>();

        for (int row = 0; row < gameField.getSize(); row++) {
            for (int column = 0; column < gameField.getSize(); column++) {
                final Position position = new Position(row, column);
                if (gameField.isCellEmpty(position)) {
                    emptyCells.add(position);
                }
            }
        }

        return emptyCells;
    }

    public boolean hasEmptyCell() {
        return !getEmptyCellPositions().isEmpty();
    }

    public List<Integer> getRow(final int rowIndex) {
        return gameField.getRow(rowIndex);
    }

    public List<Integer> getColumn(final int columnIndex) {
        return gameField.getColumn(columnIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return score == game.score && changed == game.changed && Objects.equals(gameField, game.gameField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, changed, gameField);
    }

    @Override
    public String toString() {
        return "Game{" +
                "score=" + score +
                ", changed=" + changed +
                ", gameField=" + gameField +
                '}';
    }
}
