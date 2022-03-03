package ui.output;

import map.GameField;

public interface OutputHandler {

    void displayField(final GameField gameField);

    void displayScore(final int score);
}
