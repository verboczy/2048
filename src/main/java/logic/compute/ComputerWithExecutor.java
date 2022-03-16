package logic.compute;

import java.util.concurrent.ExecutorService;

public abstract class ComputerWithExecutor {

    protected final ExecutorService executorService;

    protected ComputerWithExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
