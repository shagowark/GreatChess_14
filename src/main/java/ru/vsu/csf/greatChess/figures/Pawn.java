package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;
import java.util.List;

public class Pawn extends Figure{
    public Pawn(Color color, ChessBoardField position) {
        super(color, position);
    }


    @Override
    public boolean moveTo(ChessBoardField wantedField) throws Exception {
        if (position == wantedField){
            return false;
        }

        int currentI = chessBoard.getBoardFieldI(position);
        int currentJ = chessBoard.getBoardFieldJ(position);
        int wantedI = chessBoard.getBoardFieldI(wantedField);
        int wantedJ = chessBoard.getBoardFieldJ(wantedField);
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
            if (wantedField.hasFigure()){
                return false;
            } else{
                if (wantedI == 0 || wantedI == chessBoard.getSIZE_OF_BOARD() - 1){
                    wantedField.setFigure(new Queen(color, wantedField));
                    this.position.setFigure(null);
                    this.position = null;
                } else {
                    wantedField.setFigure(this);
                    this.position.setFigure(null);
                    this.position = wantedField;
                }
                return true;
            }
        } else {
            if (wantedField.hasFigure()) {
                if (wantedField.getFigure().getColor() != this.color) {
                    if (wantedI == 0 || wantedI == chessBoard.getSIZE_OF_BOARD() - 1){
                        wantedField.getFigure().position = null;
                        wantedField.setFigure(new Queen(color, wantedField));
                        this.position.setFigure(null);
                        this.position = null;
                    } else {
                        wantedField.getFigure().position = null;
                        wantedField.setFigure(this);
                        this.position.setFigure(null);
                        this.position = wantedField;
                    }
                    return true;
                } else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
