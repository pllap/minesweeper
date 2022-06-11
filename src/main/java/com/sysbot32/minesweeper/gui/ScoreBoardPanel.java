package com.sysbot32.minesweeper.gui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ScoreBoardPanel extends JPanel {
    private JComponent left;
    private JComponent center;
    private JComponent right;

    public ScoreBoardPanel(JComponent left, JComponent center, JComponent right) {
        setLayout(new BorderLayout());

        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }
}
