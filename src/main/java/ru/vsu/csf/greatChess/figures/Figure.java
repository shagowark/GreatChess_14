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

    public abstract boolean moveTo(ChessBoardField wantedField) throws Exception;

}
