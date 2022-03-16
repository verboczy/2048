package logic.compute.slide;

import game.Game;
import logic.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SlideTaskFactory {

    public static List<SlideTask> createSlideTasks(final Game game, final Command direction, final CountDownLatch countDownLatch) {
        switch (direction) {
            case RIGHT:
                return createSlideRightTasks(game, countDownLatch);
            case LEFT:
                return createSlideLeftTasks(game, countDownLatch);
            case UP:
                return createSlideUpTasks(game, countDownLatch);
            case DOWN:
                return createSlideDownTasks(game, countDownLatch);
            default:
                throw new IllegalArgumentException(String.format("%s is not a valid direction.", direction));
        }
    }

    private static List<SlideTask> createSlideRightTasks(final Game game, final CountDownLatch countDownLatch) {
        final List<SlideTask> slideRightTasks = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < game.getFieldSize(); rowIndex++) {
            slideRightTasks.add(new SlideRightTask(game, countDownLatch, rowIndex));
        }
        return slideRightTasks;
    }

    private static List<SlideTask> createSlideLeftTasks(final Game game, final CountDownLatch countDownLatch) {
        final List<SlideTask> slideLeftTasks = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < game.getFieldSize(); rowIndex++) {
            slideLeftTasks.add(new SlideLeftTask(game, countDownLatch, rowIndex));
        }
        return slideLeftTasks;
    }

    private static List<SlideTask> createSlideUpTasks(final Game game, final CountDownLatch countDownLatch) {
        final List<SlideTask> slideUpTasks = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < game.getFieldSize(); columnIndex++) {
            slideUpTasks.add(new SlideUpTask(game, countDownLatch, columnIndex));
        }
        return slideUpTasks;
    }

    private static List<SlideTask> createSlideDownTasks(final Game game, final CountDownLatch countDownLatch) {
        final List<SlideTask> slideDownTasks = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < game.getFieldSize(); columnIndex++) {
            slideDownTasks.add(new SlideDownTask(game, countDownLatch, columnIndex));
        }
        return slideDownTasks;
    }
}
