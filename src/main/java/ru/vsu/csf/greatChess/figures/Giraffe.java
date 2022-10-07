package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class Giraffe extends Figure{
    public Giraffe(Color color, ChessBoardField position) {
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

        if (currentI == wantedI || currentJ == wantedJ){ // rook pattern

            int moveDirectionI;
            int moveDirectionJ;
            moveDirectionI = Integer.compare(wantedI, currentI);
            moveDirectionJ = Integer.compare(wantedJ, currentJ);
            int i = currentI + moveDirectionI;
            int j = currentJ + moveDirectionJ;
            while (chessBoard.getBoardField(i, j) != wantedField) {
                if (chessBoard.getBoardField(i, j).hasFigure()) {
                    return false;
                }
                i += moveDirectionI;
                j += moveDirectionJ;
            }
        } else{
            if (Math.abs(currentI - wantedI) == Math.abs(currentJ - wantedJ)){ //bishop pattern
                int moveDirectionI;
                int moveDirectionJ;
                moveDirectionI = Integer.compare(wantedI, currentI);
                moveDirectionJ = Integer.compare(wantedJ, currentJ);
                int i = currentI + moveDirectionI;
                int j = currentJ + moveDirectionJ;
                while (chessBoard.getBoardField(i, j) != wantedField) {
                    if (chessBoard.getBoardField(i, j).hasFigure()) {
                        return false;
                    }
                    i += moveDirectionI;
                    j += moveDirectionJ;
                }
            } else {
                if ((Math.abs(Math.abs(currentI - wantedI) - Math.abs(currentJ - wantedJ)) != 1) || Math.abs(currentI - wantedI) > 2 ||
                        Math.abs(currentJ - wantedJ) > 2) { //knight pattern
                    return false;
                }
            }
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
