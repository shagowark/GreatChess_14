package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class Knight extends Figure{
    public Knight(Color color, ChessBoardField position) {
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

        if ((Math.abs(Math.abs(currentI - wantedI) - Math.abs(currentJ - wantedJ)) != 1) ||
                (wantedI == currentI) || (wantedJ == currentJ) || Math.abs(currentI - wantedI) > 2 ||
                        Math.abs(currentJ - wantedJ) > 2) {

            return false;
        }

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
