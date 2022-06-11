package com.sysbot32.minesweeper.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class FaceButton extends JButton {
    private static final String[] faces = new String[]{"🙂", "😎", "😣"};

    private int status;

    public FaceButton(int status) {
        setStatus(status);
        setBackground(Color.YELLOW);
    }

    public void setStatus(int status) {
        this.status = status;
        setText(faces[status]);
    }
}
