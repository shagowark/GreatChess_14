package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class Queen extends Figure{
    public Queen(Color color, ChessBoardField position) {
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

        if (currentI != wantedI){
            if (currentJ != wantedJ){ //rook pattern
                if (Math.abs(currentI - wantedI) != Math.abs(currentJ - wantedJ)) { // bishop pattern
                    return false;
                }
            }
        }

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
        if (wantedField.hasFigure()) {
            return wantedField.getFigure().getColor() != this.color;
        } else{
            return true;
        }
    }
}
