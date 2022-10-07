package ru.vsu.csf.greatChess.chessBoard;

import ru.vsu.csf.greatChess.figures.Figure;

import java.awt.*;

public class ChessBoardField {
    private Color color;
    private Figure figureOnThisField;
    private final ChessBoard chessBoard;

    public ChessBoardField(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
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
}
