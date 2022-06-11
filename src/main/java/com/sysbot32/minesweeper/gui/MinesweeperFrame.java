package com.sysbot32.minesweeper.gui;

import com.sysbot32.minesweeper.game.MinesweeperGame;
import com.sysbot32.minesweeper.game.Preset;

import javax.swing.*;
import java.awt.*;

public class MinesweeperFrame extends JFrame {
    private MinesweeperGame minesweeperGame;

    private ScoreBoardPanel scoreBoardPanel;
    private GameBoardPanel gameBoardPanel;

    public MinesweeperFrame() {
        super("지뢰 찾기");

        minesweeperGame = new MinesweeperGame(Preset.EASY);

        scoreBoardPanel = new ScoreBoardPanel(new ScorePanel(0), new FaceButton(0), new ScorePanel(0));
        gameBoardPanel = new GameBoardPanel(minesweeperGame.getBoard());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(scoreBoardPanel, BorderLayout.NORTH);
        add(gameBoardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new MinesweeperFrame().setVisible(true);
    }
}
