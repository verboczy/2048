package logic.compute;

import java.util.concurrent.ExecutorService;

public abstract class ComputerWithExecutor {

    protected final ExecutorService executor;

    protected ComputerWithExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}
