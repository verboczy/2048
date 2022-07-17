package game;

public class Cell {
    private final Position position;
    private final int value;

    public Cell(final Position position, final int value) {
        this.position = position;
        this.value = value;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getValue() {
        return this.value;
    }
}
