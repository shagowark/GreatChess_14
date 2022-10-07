package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class King extends Figure {
    public King(Color color, ChessBoardField position) {
        super(color, position);
    }

    @Override
    public boolean moveTo(ChessBoardField wantedField) throws Exception {
        if (position == wantedField) {
            return false;
        }

        int currentI = chessBoard.getBoardFieldI(position);
        int currentJ = chessBoard.getBoardFieldJ(position);
        int wantedI = chessBoard.getBoardFieldI(wantedField);
        int wantedJ = chessBoard.getBoardFieldJ(wantedField);

        if (Math.abs(currentI - wantedI) > 1 || Math.abs(currentJ - wantedJ) > 1){
            return false;
        }
        //todo check, mate
        if (wantedField.hasFigure()) {
            if (wantedField.getFigure().getColor() != this.color) {
                wantedField.getFigure().position = null;
                wantedField.setFigure(this);
                this.position.setFigure(null);
                this.position = wantedField;
                return true;
            } else {
                return false;
            }
        } else{
            wantedField.setFigure(this);
            this.position.setFigure(null);
            this.position = wantedField;
            return true;
        }
    }
}
