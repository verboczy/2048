package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @DisplayName("Position(1, 2) equals Position(1, 2)")
    @Test
    void positionsEqualByCoordinate() {
        final Position position = alwaysCreateSamePosition();
        final Position samePosition = alwaysCreateSamePosition();

        assertEquals(position, samePosition);
    }

    @DisplayName("Position(1, 2) has same hash as Position(1, 2)")
    @Test
    void hashCodesEqualForSameCoordinates() {
        final Position position = alwaysCreateSamePosition();
        final Position samePosition = alwaysCreateSamePosition();

        assertEquals(position.hashCode(), samePosition.hashCode());
    }

    private Position alwaysCreateSamePosition() {
        return new Position(1, 2);
    }
}
