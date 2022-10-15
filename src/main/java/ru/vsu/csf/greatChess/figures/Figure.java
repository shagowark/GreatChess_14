package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract public class Figure {
    protected final Color color;
    protected ChessBoardField position;
    protected final ChessBoard chessBoard;

    public Figure(Color color, ChessBoardField position){
        this.color = color;
        this.position = position;
        this.chessBoard = position.getChessBoard();
    }

    public Color getColor() {
        return color;
    }

    // разделить на более мелкие, убитые фигуры.
    public boolean moveTo(ChessBoardField wantedField) throws Exception{
        if (canMoveTo(wantedField)) {
            if (wantedField.hasFigure()) {
                    killOnField(wantedField);
            }
            setOnField(wantedField);
            return true;
        } else {
            return false;
        }
    }

    public abstract boolean canMoveTo(ChessBoardField wantedField) throws Exception;
    protected void killOnField(ChessBoardField wantedField){
        wantedField.getFigure().position = null;
        chessBoard.addDeadFigure(wantedField.getFigure());
    }
    protected void setOnField(ChessBoardField wantedField){
        wantedField.setFigure(this);
        this.position.setFigure(null);
        this.position = wantedField;
    }

    public ChessBoardField getPosition() {
        return position;
    }
}
