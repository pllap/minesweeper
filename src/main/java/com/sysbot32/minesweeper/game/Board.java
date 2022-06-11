package com.sysbot32.minesweeper.game;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
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

    public void open(int row, int col) {
        if (!cells[row][col].isOpened()) {
            bfs(row, col);
        }
    }

    private void bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];
            Cell cell = cells[r][c];
            if (cell.getNearbyMines() == 0) {
                if (r > 1 && Objects.nonNull(cells[r - 1][c]) && !cells[r - 1][c].isOpened()) { // ↑
                    queue.add(new int[]{r - 1, c});
                }
                if (r < cells.length - 1 && Objects.nonNull(cells[r + 1][c]) && !cells[r + 1][c].isOpened()) { // ↓
                    queue.add(new int[]{r + 1, c});
                }
                if (c > 1 && Objects.nonNull(cells[r][c - 1]) && !cells[r][c - 1].isOpened()) { // ←
                    queue.add(new int[]{r, c - 1});
                }
                if (c < cells[r].length - 1 && Objects.nonNull(cells[r][c + 1]) && !cells[r][c + 1].isOpened()) { // →
                    queue.add(new int[]{r, c + 1});
                }
                if (r > 1 && c > 1 && Objects.nonNull(cells[r - 1][c - 1]) && !cells[r - 1][c - 1].isOpened()) { // ↖
                    queue.add(new int[]{r - 1, c - 1});
                }
                if (r < cells.length - 1 && c > 1 && Objects.nonNull(cells[r + 1][c - 1]) && !cells[r + 1][c - 1].isOpened()) { // ↙
                    queue.add(new int[]{r + 1, c - 1});
                }
                if (r > 1 && c < cells[r].length - 1 && Objects.nonNull(cells[r - 1][c + 1]) && !cells[r - 1][c + 1].isOpened()) { // ↗
                    queue.add(new int[]{r - 1, c + 1});
                }
                if (r < cells.length - 1 && c < cells[r].length - 1 && Objects.nonNull(cells[r + 1][c + 1]) && !cells[r + 1][c + 1].isOpened()) { // ↘
                    queue.add(new int[]{r + 1, c + 1});
                }
            }
            cell.open();
        }
    }

    public void flag(int row, int col) {
        cells[row][col].flag();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(" \t");
        for (int col = 0; col < width; col++) {
            stringBuilder.append(String.format("%-3d", col));
        }
        stringBuilder.append('\n');
        for (int row = 0; row < height; row++) {
            stringBuilder.append(String.format("%-3d", row));
            for (int col = 0; col < width; col++) {
                Cell cell = cells[row][col];
                if (Objects.nonNull(cell)) {
                    stringBuilder.append(cell);
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
