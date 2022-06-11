package com.sysbot32.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;

    public ScorePanel(int score) {
        setLayout(new BorderLayout());

        scoreLabel = new JLabel(String.format("%03d", score));

        add(scoreLabel, BorderLayout.CENTER);
    }
}
