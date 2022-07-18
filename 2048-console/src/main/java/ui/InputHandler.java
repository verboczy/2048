package ui;

import logic.Command;
import queue.InputQueue;

import java.util.Scanner;

import static logic.Command.*;

public class InputHandler {
    final InputQueue commandQueue;
    boolean exit;

    public InputHandler(final InputQueue commandQueue) {
        this.commandQueue = commandQueue;
        exit = false;
    }

    public void exit() {
        this.exit = true;
    }

    public void handleConsoleInput() {
        final Scanner scanner = new Scanner(System.in);

        while (!exit) {
            final String input = scanner.next();

            final Command command;
            switch (input) {
                case "w":
                    command = UP;
                    break;
                case "s":
                    command = DOWN;
                    break;
                case "d":
                    command = RIGHT;
                    break;
                case "a":
                    command = LEFT;
                    break;
                case "n":
                    command = NEW_GAME;
                    break;
                case "x":
                    command = EXIT;
                    exit = true;
                    break;
                default:
                    command = UNKNOWN;
                    break;
            }
            commandQueue.addCommand(command);
        }
    }
}
