package com.sysbot32.minesweeper.gui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class ScoreBoardPanel extends JPanel {
    private JComponent left;
    private JComponent center;
    private JComponent right;

    public ScoreBoardPanel(JComponent left, JComponent center, JComponent right) {
        setLayout(new BorderLayout());

        if (Objects.nonNull(left)) {
            add(left, BorderLayout.WEST);
        }
        if (Objects.nonNull(center)) {
            add(center, BorderLayout.CENTER);
        }
        if (Objects.nonNull(right)) {
            add(right, BorderLayout.EAST);
        }
    }
}
