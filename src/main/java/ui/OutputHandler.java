package ui;

import map.GameField;

public class OutputHandler {

    public static void print(GameField gameField) {
        for (int row = 0; row < gameField.getSize(); row++) {
            for (int column = 0; column < gameField.getSize(); column++) {
                System.out.printf("%4d ", gameField.getCell(row, column));
            }
            System.out.println();
        }
    }
}
