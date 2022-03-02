package ui;

import logic.Command;

import java.util.Scanner;

import static logic.Command.*;

public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner;
    private static ConsoleInputHandler consoleInputHandler;

    private ConsoleInputHandler() {
        scanner = new Scanner(System.in);
    }

    public static ConsoleInputHandler getInstance() {
        if (consoleInputHandler == null) {
            consoleInputHandler = new ConsoleInputHandler();
        }
        return consoleInputHandler;
    }

    @Override
    public Command getCommand() {
        while (true) {
            final String input = scanner.next();

            switch (input) {
                case "w":
                    return UP;
                case "s":
                    return DOWN;
                case "d":
                    return RIGHT;
                case "a":
                    return LEFT;
                case "n":
                    return NEW_GAME;
                case "x":
                    return EXIT;
            }
        }
    }
}
