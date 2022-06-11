package com.sysbot32.minesweeper.game;

import lombok.ToString;

@ToString
public enum Preset {
    EASY(9, 9, 10), NORMAL(16, 16, 40), HARD(30, 16, 99);

    public final int width;
    public final int height;
    public final int mines;

    Preset(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
    }
}
