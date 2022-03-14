package logic.compute.gameover;

import game.Game;
import logic.compute.ComputerWithExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TaskBasedGameOverComputer extends ComputerWithExecutor implements GameOverComputer {

    public TaskBasedGameOverComputer(ExecutorService executor) {
        super(executor);
    }

    @Override
    public boolean isGameOver(final Game game) {
        return noMoreEmptyCell(game) && cannotMoveHorizontally(game) && cannotMoveVertically(game);
    }

    private boolean noMoreEmptyCell(final Game game) {
        return !game.hasEmptyCell();
    }

    private boolean cannotMoveHorizontally(final Game game) {
        final List<Future<Boolean>> results = createHorizontalTask(game);
        return isAllResultGameOver(results);
    }

    private List<Future<Boolean>> createHorizontalTask(final Game game) {
        final List<Future<Boolean>> results = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < game.getFieldSize(); rowIndex++) {
            final Future<Boolean> gameOver = executor.submit(new GameOverCheckTask(game.getRow(rowIndex)));
            results.add(gameOver);
        }

        return results;
    }

    private boolean cannotMoveVertically(final Game game) {
        final List<Future<Boolean>> results = createVerticalTask(game);
        return isAllResultGameOver(results);
    }

    private List<Future<Boolean>> createVerticalTask(final Game game) {
        final List<Future<Boolean>> results = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < game.getFieldSize(); columnIndex++) {
            final Future<Boolean> gameOver = executor.submit(new GameOverCheckTask(game.getColumn(columnIndex)));
            results.add(gameOver);
        }

        return results;
    }

    private boolean isAllResultGameOver(final List<Future<Boolean>> results) {
        return results.stream().allMatch(result -> {
            try {
                return result.get();
            } catch (InterruptedException | ExecutionException e) {
                return true;
            }
        });
    }

}
