package logic;

import game.Cell;
import game.Game;
import game.GameField;
import game.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class TestUtil {

    public static File createFile(final String directory, final String fileName, final String fileExtension) {
        return new File(String.format("%s%s%s", directory, fileName, fileExtension));
    }

    public static Game readInputGameFromFile(final File file) {
        try {
            final Scanner scanner = new Scanner(file);
            final int size = scanner.nextInt();
            final GameField gameField = new GameField(size);
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    final int value = scanner.nextInt();
                    gameField.setCell(new Cell(new Position(row, column), value));
                }
            }
            return new Game(gameField);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        return null;
    }

    public static Game readExpectedOutputGameFromFile(final File file) {
        try {
            final Scanner scanner = new Scanner(file);
            final int size = scanner.nextInt();
            final boolean changed = scanner.nextInt() == 1;
            final int score = scanner.nextInt();
            final GameField gameField = new GameField(size);
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    final int value = scanner.nextInt();
                    gameField.setCell(new Cell(new Position(row, column), value));
                }
            }
            final Game game = new Game(gameField);
            game.setChanged(changed);
            game.addScore(score);
            return game;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
        return null;
    }
}
