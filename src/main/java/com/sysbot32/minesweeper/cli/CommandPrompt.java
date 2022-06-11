package com.sysbot32.minesweeper.cli;

import com.sysbot32.minesweeper.game.FailureException;
import com.sysbot32.minesweeper.game.MinesweeperGame;
import com.sysbot32.minesweeper.game.Preset;

import java.util.Scanner;

public class CommandPrompt {
    private static final Scanner scanner = new Scanner(System.in);

    private MinesweeperGame minesweeperGame;

    public void run() {
        int selected = startMenu();
        if (selected < 4) {
            Preset preset = Preset.values()[selected - 1];
            startPreset(preset);
        } else {
            int[] values = startCustomMenu();
            startCustom(values[0], values[1], values[2]);
        }
        playing();
    }

    public int startMenu() {
        System.out.println("지뢰 찾기");
        System.out.println("---------");
        System.out.println("1. 초급 (9*9, 10)");
        System.out.println("2. 중급 (16*16, 40)");
        System.out.println("3. 고급 (30*16, 99)");
        System.out.println("4. 사용자 지정 게임");
        int selected;
        do {
            System.out.print(">");
            selected = scanner.nextInt();
        } while (!(0 < selected && selected <= 4));
        return selected;
    }

    public void startPreset(Preset preset) {
        minesweeperGame = new MinesweeperGame(preset);
    }

    public int[] startCustomMenu() {
        System.out.println("사용자 지정 게임");
        System.out.println("----------------");
        System.out.print("가로 세로 지뢰>");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int mines = scanner.nextInt();
        return new int[]{width, height, mines};
    }

    public void startCustom(int width, int height, int mines) {
        minesweeperGame = new MinesweeperGame(width, height, mines);
    }

    public void playing() {
        while (true) {
            System.out.println(minesweeperGame.toString());
            System.out.println("O. 열기");
            System.out.println("F. 표시");
            System.out.print(">");
            String command = scanner.next().strip().toUpperCase();
            System.out.print("행 열>");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (command.equals("O")) {
                try {
                    minesweeperGame.open(row, col);
                } catch (FailureException e) {
                    System.err.println(minesweeperGame.toString());
                    System.err.println("실패");
                    return;
                }
                if (minesweeperGame.checkSuccess()) {
                    System.out.println(minesweeperGame.toString());
                    System.out.println("성공");
                    return;
                }
            } else if (command.equals("F")) {
                minesweeperGame.flag(row, col);
            }
        }
    }

    public static void main(String[] args) {
        new CommandPrompt().run();
    }
}
