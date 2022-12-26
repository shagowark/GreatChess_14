package ru.vsu.csf.greatChess.chessBoard;

import ru.vsu.csf.greatChess.figures.Figure;

import java.awt.*;

public class ChessBoardField {
    private Color color;
    private Figure figureOnThisField;
    private final ChessBoard chessBoard;
    private final Coordinates coordinates;
    // coordinates

    public ChessBoardField(ChessBoard chessBoard, int i, int j) {
        this.chessBoard = chessBoard;
        this.coordinates = new Coordinates(i, j);
    }

    public boolean hasFigure(){
        return figureOnThisField != null;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Figure getFigure() {
        return figureOnThisField;
    }

    public void setFigure(Figure figureOnThisField) {
        this.figureOnThisField = figureOnThisField;
    }

    public int getI() {
        return coordinates.getI();
    }

    public int getJ() {
        return coordinates.getJ();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}

