package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class Knight extends Figure{
    public Knight(Color color, ChessBoardField position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(ChessBoardField wantedField){

        if (position == wantedField) {
            return false;
        }


        int currentI = position.getI();
        int currentJ = position.getJ();
        int wantedI = wantedField.getI();
        int wantedJ = wantedField.getJ();

        if ((Math.abs(Math.abs(currentI - wantedI) - Math.abs(currentJ - wantedJ)) != 1) ||
                (wantedI == currentI) || (wantedJ == currentJ) || Math.abs(currentI - wantedI) > 2 ||
                        Math.abs(currentJ - wantedJ) > 2) {

            return false;
        }

        if (wantedField.hasFigure()) {
            return wantedField.getFigure().getColor() != this.color;
        } else{
            return true;
        }
    }
}
