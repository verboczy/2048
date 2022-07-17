package game;

public class GameSizeLimit {
    public static final int MINIMUM_SIZE = 2;
    public static final int MAXIMUM_SIZE = 10;

    public static void validateSize(final int size) {
        if (size < MINIMUM_SIZE) {
            throw new IllegalArgumentException(String.format("The minimum size is %d, so %d is not valid.", MINIMUM_SIZE, size));
        }
        if (size > MAXIMUM_SIZE) {
            throw new IllegalArgumentException(String.format("The maximum size is %d, so %d is not valid.", MAXIMUM_SIZE, size));
        }
    }
}
