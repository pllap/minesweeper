package com.sysbot32.minesweeper.game;

import lombok.Getter;

import java.util.Objects;
import java.util.Random;

@Getter
public class Board {
    private final int width;
    private final int height;
    private final int mines;

    private final Cell[][] cells;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;

        cells = new Cell[height][width];

        int m = 0;
        Random random = new Random();
        while (m < mines) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);
            if (Objects.isNull(cells[row][col])) {
                cells[row][col] = new Cell(true);
                m++;
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (Objects.isNull(cells[row][col])) {
                    cells[row][col] = new Cell(getNearbyMines(row, col));
                }
            }
        }
    }

    private int getNearbyMines(int row, int col) {
        int nearbyMines = 0;
        if (row > 1 && Objects.nonNull(cells[row - 1][col]) && cells[row - 1][col].isHasMine()) { // ↑
            nearbyMines++;
        }
        if (row < cells.length - 1 && Objects.nonNull(cells[row + 1][col]) && cells[row + 1][col].isHasMine()) { // ↓
            nearbyMines++;
        }
        if (col > 1 && Objects.nonNull(cells[row][col - 1]) && cells[row][col - 1].isHasMine()) { // ←
            nearbyMines++;
        }
        if (col < cells[row].length - 1 && Objects.nonNull(cells[row][col + 1]) && cells[row][col + 1].isHasMine()) { // →
            nearbyMines++;
        }
        if (row > 1 && col > 1 && Objects.nonNull(cells[row - 1][col - 1]) && cells[row - 1][col - 1].isHasMine()) { // ↖
            nearbyMines++;
        }
        if (row < cells.length - 1 && col > 1 && Objects.nonNull(cells[row + 1][col - 1]) && cells[row + 1][col - 1].isHasMine()) { // ↙
            nearbyMines++;
        }
        if (row > 1 && col < cells[row].length - 1 && Objects.nonNull(cells[row - 1][col + 1]) && cells[row - 1][col + 1].isHasMine()) { // ↗
            nearbyMines++;
        }
        if (row < cells.length - 1 && col < cells[row].length - 1 && Objects.nonNull(cells[row + 1][col + 1]) && cells[row + 1][col + 1].isHasMine()) { // ↘
            nearbyMines++;
        }
        return nearbyMines;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (Objects.nonNull(cell)) {
                    stringBuilder.append(cell);
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}