package logic.compute.slide;

import game.Game;
import logic.Command;
import logic.compute.ComputerWithExecutor;
import logic.compute.slide.task.SlideTask;
import logic.compute.slide.task.SlideTaskFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class TaskBasedSlideComputer extends ComputerWithExecutor implements SlideComputer {

    public TaskBasedSlideComputer(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    public void slide(final Game game, final Command command) {
        final CountDownLatch countDownLatch = new CountDownLatch(game.getFieldSize());
        final List<SlideTask> slideTasks = SlideTaskFactory.createSlideTasks(game, command, countDownLatch);
        executeTasks(slideTasks);
        waitForTasksToFinish(countDownLatch);
    }

    private void executeTasks(final List<SlideTask> slideTasks) {
        slideTasks.forEach(executorService::execute);
    }

    private void waitForTasksToFinish(final CountDownLatch countDownLatch) {
        try {
            countDownLatch.await();
        } catch (final InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
