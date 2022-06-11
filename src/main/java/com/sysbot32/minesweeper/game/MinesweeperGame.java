package com.sysbot32.minesweeper.game;

import lombok.Getter;

@Getter
public class MinesweeperGame {
    private final long startTime = System.currentTimeMillis();
    private int mines = 0;
    private final Board board;

    public MinesweeperGame(Preset preset) {
        board = new Board(preset.width, preset.height, preset.mines);
    }

    public MinesweeperGame(int width, int height, int mines) {
        board = new Board(width, height, mines);
    }

    public void open(int row, int col) {
        board.open(row, col);
        if (board.getCells()[row][col].isHasMine()) {
            System.out.println(getCurrentTime());
        }
    }

    public void flag(int row, int col) {
        board.flag(row, col);
        if (board.getCells()[row][col].isFlagged()) {
            mines++;
        } else {
            mines--;
        }
    }

    public long getCurrentTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean checkSuccess() {
        if (mines != board.getMines()) {
            return false;
        }
        for (Cell[] row : board.getCells()) {
            for (Cell cell : row) {
                if (cell.isOpened() && cell.isHasMine()) {
                    return false;
                }
                if (!cell.isOpened() && !(cell.isFlagged() && cell.isHasMine())) {
                    return false;
                }
            }
        }
        System.out.println(getCurrentTime());
        return true;
    }

    @Override
    public String toString() {
        return "Time: " + getCurrentTime() + "\nMines: " + (board.getMines() - mines) + "\n" + board;
    }
}
