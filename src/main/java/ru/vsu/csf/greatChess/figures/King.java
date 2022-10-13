package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class King extends Figure {
    public King(Color color, ChessBoardField position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(ChessBoardField wantedField) throws Exception {
        if (position == wantedField) {
            return false;
        }

        int currentI = position.getI();
        int currentJ = position.getJ();
        int wantedI = wantedField.getI();
        int wantedJ = wantedField.getJ();

        if (Math.abs(currentI - wantedI) > 1 || Math.abs(currentJ - wantedJ) > 1){
            return false;
        }
        //todo check, mate
        if (wantedField.hasFigure()) {
            return wantedField.getFigure().getColor() != this.color;
        } else{
            return true;
        }
    }
}
