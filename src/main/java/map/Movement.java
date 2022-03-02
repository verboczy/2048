package map;

public class Movement {
    private final boolean fieldChanged;
    private final int movementScore;

    public Movement(final boolean fieldChanged, final int movementScore) {
        this.fieldChanged = fieldChanged;
        this.movementScore = movementScore;
    }

    public boolean isFieldChanged() {
        return fieldChanged;
    }

    public int getMovementScore() {
        return movementScore;
    }
}
