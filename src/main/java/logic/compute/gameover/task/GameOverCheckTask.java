package logic.compute.gameover.task;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class GameOverCheckTask implements Callable<Boolean> {

    private final List<Integer> elements;

    public GameOverCheckTask(final List<Integer> elements) {
        this.elements = Collections.unmodifiableList(elements);
    }

    @Override
    public Boolean call() {
        return isGameOver();
    }

    private boolean isGameOver() {
        return !canMove();
    }

    private boolean canMove() {
        return hasEmptyCell() || hasSameNeighbours();
    }

    private boolean hasEmptyCell() {
        return elements.contains(0);
    }

    private boolean hasSameNeighbours() {
        for (int index = 0; index < elements.size() - 1; index++) {
            final int element = elements.get(index);
            final int nextElement = elements.get(index + 1);

            if (element == nextElement) {
                return true;
            }
        }
        return false;
    }
}
