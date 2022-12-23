package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.game.Game;

import javax.swing.*;
import java.awt.*;

public class WindowApp extends JFrame {
    private Game game = new Game();
    private final DrawPanel drawPanel;

    public WindowApp() throws HeadlessException {
        drawPanel = new DrawPanel(game);
        this.add(drawPanel);
    }

    public Game getGame() {
        return game;
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }
}
