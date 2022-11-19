package ru.vsu.csf.greatChess.figures;

import ru.vsu.csf.greatChess.game.CheckMateOperator;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;

import java.awt.*;

public class King extends Figure {
    public King(Color color, ChessBoardField position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(ChessBoardField wantedField) {
        if (position == wantedField) {
            return false;
        }


        int currentI = position.getI();
        int currentJ = position.getJ();
        int wantedI = wantedField.getI();
        int wantedJ = wantedField.getJ();

        Color enemyColor;
        if (color == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }

        if (Math.abs(currentI - wantedI) > 1 || Math.abs(currentJ - wantedJ) > 1) {
            return false;
        }

        CheckMateOperator checkMateOperator = new CheckMateOperator(this.getPosition().getChessBoard());
        Figure prevFigure = null;
        if (wantedField.hasFigure()){
            prevFigure = wantedField.getFigure();
        }
        position.setFigure(null);
        wantedField.setFigure(null);
        if (checkMateOperator.fieldIsUnderAttack(wantedField, enemyColor)) {
            if (prevFigure != null){
                wantedField.setFigure(prevFigure);
            }
            position.setFigure(this);
            return false;
        }
        position.setFigure(this);
        if (prevFigure != null){
            wantedField.setFigure(prevFigure);
        }

        if (wantedField.hasFigure()) {
            return wantedField.getFigure().getColor() != this.color;
        } else {
            return true;
        }
    }
}
