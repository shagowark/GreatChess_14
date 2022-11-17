package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class Pawn extends Figure{
    public Pawn(Color color, ChessBoardField position) {
        super(color, position);
    }

    @Override
    public boolean moveTo(ChessBoardField wantedField)  {
        ChessBoard chessBoard = position.getChessBoard();

       if (super.moveTo(wantedField)){
           if (wantedField.getI() == 0 || wantedField.getI() == chessBoard.getSIZE_OF_BOARD() - 1){
               spawnQueen(wantedField);
           }
           return true;
       } else return false;
    }

    @Override
    public boolean canMoveTo(ChessBoardField wantedField) {
        if (position == wantedField){
            return false;
        }


        int currentI = position.getI();
        int currentJ = position.getJ();
        int wantedI = wantedField.getI();
        int wantedJ = wantedField.getJ();

        if (Math.abs(wantedJ - currentJ) > 1){
            return false;
        }
        if (color == Color.WHITE){
            if (wantedI - currentI != -1){
                return false;
            }
        } else {
            if (wantedI - currentI != 1){
                return false;
            }
        }
        if (currentJ == wantedJ){
            return !wantedField.hasFigure();
        } else {
            if (wantedField.hasFigure()) {
                return wantedField.getFigure().getColor() != this.color;
            } else {
                return false;
            }
        }
    }

    private void spawnQueen(ChessBoardField wantedField){
        wantedField.getFigure().position = null;
        wantedField.setFigure(new Queen(color, wantedField));
    }
}
