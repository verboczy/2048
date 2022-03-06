package ui.output;

import game.Game;

public interface OutputHandler {

    void displayField(final Game game);

    void displayScore(final int score);
}
