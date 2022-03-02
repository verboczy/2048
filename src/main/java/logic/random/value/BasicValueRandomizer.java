package logic.random.value;

import java.util.Random;

public class BasicValueRandomizer implements ValueRandomizer {

    private final Random random;

    public BasicValueRandomizer(final Random random) {
        this.random = random;
    }

    /**
     * Returns 2 or 4 with different probabilities.
     *
     * @return 2 with 9/10 probability, 4 with 1/10 probability
     */
    @Override
    public int getRandomValue() {
        int value = random.nextInt(10);
        if (value == 9) {
            return 4;
        } else {
            return 2;
        }
    }
}
