package queue;

import logic.Command;

import java.util.concurrent.BlockingQueue;

public class ConsoleInputQueue implements InputQueue {

    final BlockingQueue<Command> commandQueue;

    public ConsoleInputQueue(final BlockingQueue<Command> commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    @Override
    public Command getNextCommand() throws InterruptedException {
        return commandQueue.take();
    }

    @Override
    public void clear() {
        commandQueue.clear();
    }
}
