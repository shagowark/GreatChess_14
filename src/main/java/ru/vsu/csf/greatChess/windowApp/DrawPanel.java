package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class DrawPanel extends JPanel implements MouseListener {
    private static final int FIELD_SIZE = 50;
    private Game game;
    private List<ChessBoardField> reachable = null;
    private List<ChessBoardField> killable = null;
    private ChessBoardField chosenField = null;

    public DrawPanel(Game game) {
        this.game = game;
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintChessBoard((Graphics2D) g);
        paintCoords(g);
    }

    private void paintChessBoard(Graphics2D g) {
        ChessBoard chessBoard = game.getChessBoard();
        if (game.isGameEnded()){
            JOptionPane.showMessageDialog(this, "End of game!");
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < chessBoard.getSIZE_OF_BOARD(); i++) {
            for (int j = 0; j < chessBoard.getSIZE_OF_BOARD(); j++) {
                Color color;
                if (chessBoard.getBoardField(i, j).getColor() == Color.WHITE) {
                    color = new Color(240,217,181);

                } else {
                    color = new Color(181,136,99);
                }
                g.setColor(color);
                g.fillRect((j + 1) * FIELD_SIZE, (i + 1) * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);

                g.setStroke(new BasicStroke(3));
                if (reachable != null) {
                    if (reachable.contains(chessBoard.getBoardField(i, j))) {
                        g.setColor(Color.YELLOW);
                        g.fillRect((j + 1) * FIELD_SIZE + FIELD_SIZE /4, (i + 1) * FIELD_SIZE + FIELD_SIZE /4, FIELD_SIZE /2, FIELD_SIZE /2);
                    }
                }

                if (killable != null) {
                    if (killable.contains(chessBoard.getBoardField(i, j))) {
                        g.setColor(Color.RED);
                        g.fillRect((j + 1) * FIELD_SIZE, (i + 1) * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                    }
                }

                if (chosenField == chessBoard.getBoardField(i, j)) {
                    g.setColor(Color.ORANGE);
                    g.drawRect((j + 1) * FIELD_SIZE, (i + 1) * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                }

                if (chessBoard.getBoardField(i, j).hasFigure()) {
                    g.drawImage(ImageManager.getImage(chessBoard.getBoardField(i, j).getFigure()), (j + 1) * FIELD_SIZE + 5, (i + 1) * FIELD_SIZE + 5, 40, 40, null);
                }
            }
        }
    }

    private void paintCoords(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, FIELD_SIZE / 2));
        String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        int j = 10;
        for (int i = 0; i < 10; i++) {
            g.drawString(String.valueOf(j), FIELD_SIZE / 3, (i + 1) * FIELD_SIZE + FIELD_SIZE / 4 * 3);
            g.drawString(String.valueOf(j), 11 * FIELD_SIZE + FIELD_SIZE / 3, (i + 1) * FIELD_SIZE + FIELD_SIZE / 4 * 3);
            g.drawString(letters[i], (i + 1) * FIELD_SIZE + FIELD_SIZE / 2, FIELD_SIZE / 4 * 3);
            g.drawString(letters[i], (i + 1) * FIELD_SIZE + FIELD_SIZE / 2, 11 * FIELD_SIZE + FIELD_SIZE / 4 * 3);
            j--;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int j = e.getX() / FIELD_SIZE - 1;
        int i = e.getY() / FIELD_SIZE - 1;
        if (!game.coordinatesAreRight(i, j)){
            chosenField = null;
            reachable = null;
            killable = null;
            repaint();
        } else {
            ChessBoardField currentField = game.getChessBoard().getBoardField(i, j);
            if (chosenField == null) {
                switch (game.tryChooseFigure(i, j)) {
                    case CORRECT -> {
                        chosenField = currentField;
                        reachable = currentField.getFigure().getReachableFields();
                        killable = currentField.getFigure().getKillableFields();
                        repaint();
                    }
                    case FIELD_EMPTY -> {
                        chosenField = null;
                        reachable = null;
                        killable = null;
                        repaint();}
                }
            } else {
                switch (game.tryMoveFigureTo(i, j)) {
                    case KING_UNDER_ATTACK -> {
                        chosenField = null;
                        reachable = null;
                        killable = null;
                        JOptionPane.showMessageDialog(this, "King is checked!");
                        repaint();
                    }
                    case WHITE_MATED -> {
                        chosenField = null;
                        reachable = null;
                        killable = null;
                        JOptionPane.showMessageDialog(this, "Black win!");
                        repaint();
                    }
                    case BLACK_MATED -> {
                        chosenField = null;
                        reachable = null;
                        killable = null;
                        JOptionPane.showMessageDialog(this, "White win!");
                        repaint();
                    }
                    default -> {
                        chosenField = null;
                        reachable = null;
                        killable = null;
                        repaint();
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
