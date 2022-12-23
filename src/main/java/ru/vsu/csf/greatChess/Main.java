package ru.vsu.csf.greatChess;

import ru.vsu.csf.greatChess.console.ConsoleGame;
import ru.vsu.csf.greatChess.windowApp.WindowGame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {


        if ("-console".equals(args[0])) {
            ConsoleGame consoleGame = new ConsoleGame();
            consoleGame.startConsoleGame();
        } else if ("-window".equals(args[0])) {
            WindowGame wg = new WindowGame();
            wg.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            wg.setSize(650, 650);
            wg.setVisible(true);

        }
    }
}
