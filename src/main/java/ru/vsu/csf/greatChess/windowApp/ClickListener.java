package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.game.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
    private DrawPanel drawPanel = null;
    private ChessBoardField clickedField = null;
    public ClickListener(DrawPanel drawPanel){
        this.drawPanel = drawPanel;
    }

    public ChessBoardField getClickedField() {
        return clickedField;
    }

    public void setClickedField(ChessBoardField clickedField) {
        this.clickedField = clickedField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int FIELD_SIZE = drawPanel.getFieldSize();
        int j = e.getX() / FIELD_SIZE - 1;
        int i = e.getY() / FIELD_SIZE - 1;
        clickedField = drawPanel.getGame().getChessBoard().getBoardField(i, j);
        drawMove(i, j);
    }

    public void drawMove(int i, int j){
        Game game = drawPanel.getGame();

        ChessBoardField currentField = game.getChessBoard().getBoardField(i, j);
        if (!game.coordinatesAreRight(i, j)) {
            drawPanel.setCurrentField(null);
            drawPanel.setReachable(null);
            drawPanel.setKillable(null);
            drawPanel.repaint();
        } else {
            switch (game.tryChooseField(i, j)) {
                case FIGURE_CHOSEN -> {
                    drawPanel.setCurrentField(currentField); // todo data-класс
                    drawPanel.setReachable(currentField.getFigure().getReachableFields());
                    drawPanel.setKillable(currentField.getFigure().getKillableFields());
                    drawPanel.repaint();
                }
                case KING_UNDER_ATTACK -> {
                    drawPanel.setCurrentField(null);
                    drawPanel.setReachable(null);
                    drawPanel.setKillable(null);
                    JOptionPane.showMessageDialog(drawPanel, "King is checked!");
                    drawPanel.repaint();
                }
                case WHITE_MATED -> {
                    drawPanel.setCurrentField(null);
                    drawPanel.setReachable(null);
                    drawPanel.setKillable(null);
                    JOptionPane.showMessageDialog(drawPanel, "Black win!");
                    drawPanel.repaint();
                }
                case BLACK_MATED -> {
                    drawPanel.setCurrentField(null);
                    drawPanel.setReachable(null);
                    drawPanel.setKillable(null);
                    JOptionPane.showMessageDialog(drawPanel, "White win!");
                    drawPanel.repaint();
                }
                default -> {
                    drawPanel.setCurrentField(null);
                    drawPanel.setReachable(null);
                    drawPanel.setKillable(null);
                    drawPanel.repaint();
                }
            }
        }
    }

}
