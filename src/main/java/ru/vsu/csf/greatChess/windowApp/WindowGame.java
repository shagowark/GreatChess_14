package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.game.Game;

import javax.swing.*;
import java.awt.*;

public class WindowGame extends JFrame {
    private Game game = new Game();
    private final DrawPanel dp;

    public WindowGame() throws HeadlessException {
        dp = new DrawPanel(game);
        this.add(dp);
    }

}
