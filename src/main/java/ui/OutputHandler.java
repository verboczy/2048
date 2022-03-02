package ui;

import map.GameField;

public class OutputHandler {

    public static void print(GameField gameField) {
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                // TODO - format for multi (4) digit numbers
                System.out.print(gameField.getCell(i, j) + " ");
            }
            System.out.println();
        }
    }
}
