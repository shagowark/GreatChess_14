package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;

public class DrawPanel extends JPanel {
    private static final int FIELD_SIZE = 50;
    private Game game;
    private List<ChessBoardField> reachable = null; // todo пустой список
    private List<ChessBoardField> killable = null;
    private ChessBoardField currentField = null;
    private MouseAdapter mouseListener = new ClickListener(this);

    public DrawPanel(Game game) {
        this.game = game;
        this.addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintChessBoard((Graphics2D) g);
        paintCoords(g);
    }

    private void paintChessBoard(Graphics2D g) {
        ChessBoard chessBoard = game.getChessBoard();
        if (game.isGameEnded()) {
            JOptionPane.showMessageDialog(this, "End of game!");
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < chessBoard.getSIZE_OF_BOARD(); i++) {
            for (int j = 0; j < chessBoard.getSIZE_OF_BOARD(); j++) {
                Color color;
                if (chessBoard.getBoardField(i, j).getColor() == Color.WHITE) {
                    color = new Color(240, 217, 181);

                } else {
                    color = new Color(181, 136, 99);
                }
                g.setColor(color);
                g.fillRect((j + 1) * FIELD_SIZE, (i + 1) * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);

                g.setStroke(new BasicStroke(3));
                if (reachable != null) {
                    if (reachable.contains(chessBoard.getBoardField(i, j))) {
                        g.setColor(Color.YELLOW);
                        g.fillRect((j + 1) * FIELD_SIZE + FIELD_SIZE / 4, (i + 1) * FIELD_SIZE + FIELD_SIZE / 4, FIELD_SIZE / 2, FIELD_SIZE / 2);
                    }
                }

                if (killable != null) {
                    if (killable.contains(chessBoard.getBoardField(i, j))) {
                        g.setColor(Color.RED);
                        g.fillRect((j + 1) * FIELD_SIZE, (i + 1) * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
                    }
                }

                if (currentField == chessBoard.getBoardField(i, j)) {
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

    public MouseAdapter getMouseListener() {
        return mouseListener;
    }

    public Game getGame() {
        return game;
    }

    public List<ChessBoardField> getReachable() {
        return reachable;
    }

    public List<ChessBoardField> getKillable() {
        return killable;
    }

    public ChessBoardField getCurrentField() {
        return currentField;
    }

    public int getFieldSize(){
        return FIELD_SIZE;
    }

    public void setCurrentField(ChessBoardField currentField) {
        this.currentField = currentField;
    }

    public void setKillable(List<ChessBoardField> killable) {
        this.killable = killable;
    }

    public void setReachable(List<ChessBoardField> reachable) {
        this.reachable = reachable;
    }
    // todo Listener преобразует координаты клика в координаты доски.
    //  отрисовка клеток с фигурой в отдельный класс FieldRenderer


}
