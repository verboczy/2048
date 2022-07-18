package queue;

import logic.Command;

public interface InputQueue {

    void addCommand(final Command command);

    Command getNextCommand() throws InterruptedException;

    void clear();
}
