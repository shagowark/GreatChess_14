package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract public class Figure {
    protected final Color color;
    protected ChessBoardField position;

    public Figure(Color color, ChessBoardField position){
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public boolean moveTo(ChessBoardField wantedField){
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

    public abstract boolean canMoveTo(ChessBoardField wantedField);
    public List<ChessBoardField> getReachableFields() {
        List<ChessBoardField> reachable = new ArrayList<>();
        ChessBoard chessBoard = position.getChessBoard();
        for (int i = 0; i < chessBoard.getSIZE_OF_BOARD(); i++){
            for (int j = 0; j < chessBoard.getSIZE_OF_BOARD(); j++){
                if (canMoveTo(chessBoard.getBoardField(i, j))){
                    if (!chessBoard.getBoardField(i, j).hasFigure()){
                        reachable.add(chessBoard.getBoardField(i, j));
                    }
                }
            }
        }
        return reachable;
    }

    public List<ChessBoardField> getKillableFields() {
        List<ChessBoardField> killable = new ArrayList<>();
        ChessBoard chessBoard = position.getChessBoard();
        for (int i = 0; i < chessBoard.getSIZE_OF_BOARD(); i++){
            for (int j = 0; j < chessBoard.getSIZE_OF_BOARD(); j++){
                if (canMoveTo(chessBoard.getBoardField(i, j))){
                    if (chessBoard.getBoardField(i, j).hasFigure()){
                        if (chessBoard.getBoardField(i, j).getFigure().getColor() != this.color){
                            killable.add(chessBoard.getBoardField(i, j));
                        }
                    }
                }
            }
        }
        return killable;
    }
    protected void killOnField(ChessBoardField wantedField){
        ChessBoard chessBoard = position.getChessBoard();
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
