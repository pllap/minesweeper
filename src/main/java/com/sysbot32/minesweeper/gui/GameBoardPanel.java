package com.sysbot32.minesweeper.gui;

import com.sysbot32.minesweeper.game.Board;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class GameBoardPanel extends JPanel {
    private final Board board;

    public GameBoardPanel(Board board, int width, int height) {
        this.board = board;

        setLayout(new GridLayout(width, height));
    }
}
