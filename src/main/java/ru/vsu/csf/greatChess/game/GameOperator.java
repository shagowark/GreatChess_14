package ru.vsu.csf.greatChess.game;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.figures.Figure;
import ru.vsu.csf.greatChess.figures.King;

import java.awt.*;
import java.util.List;

public class GameOperator {
    ChessBoard chessBoard;

    public GameOperator(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * Подразумевается, что метод вызывается после каждого хода, т.е. проверяет,
     * поставила ли мат или шах последняя ходившая фигура
     *
     * @param figure последняя ходившая фигура
     * @return
     */
    public MoveStatus checkGameStatus(Figure figure) {
        List<Figure> figures = chessBoard.getAliveFigures();

        Figure king;
        if (figure.getColor() == Color.WHITE){
            king = getKing(figures, Color.BLACK);
        } else {
            king = getKing(figures, Color.WHITE);
        }

        if (!figure.canMoveTo(king.getPosition())){
            return MoveStatus.CORRECT;
        } else {
            if (kingCanMoveSomewhere(king)){
                if (king.getColor() == Color.WHITE){
                    return MoveStatus.WHITE_CHECKED;
                } else return MoveStatus.BLACK_CHECKED; //todo проверка на закрыть другой фигурой, убить шахующую
            } else {
                if (king.getColor() == Color.WHITE){
                    return MoveStatus.WHITE_MATED;
                } else return MoveStatus.BLACK_MATED;
            }
        }
    }

    /**
     *
     * @param field
     * @param color цвет фигур, атакующих поле
     * @return
     * @throws Exception
     */
    public boolean fieldIsUnderAttack(ChessBoardField field, Color color) { //todo возможно перекинуть в Game или ChessBoard, здесь оставить только работу с шахом/матом
        List<Figure> figures = chessBoard.getAliveFigures();
        for (Figure figure : figures) {
            if (figure.getColor() == color && figure.canMoveTo(field)) {
                return true;
            }
        }
        return false;
    }

    private Figure getKing(List<Figure> figures, Color color) {
        for (Figure figure : figures) {
            if (figure.getClass() == King.class && figure.getColor() == color) {
                return figure;
            }
        }
        return null;
    }

    private boolean kingCanMoveSomewhere(Figure king) {
        int currentI = king.getPosition().getI();
        int currentJ = king.getPosition().getJ();

        for (int moveDirectionI = -1; moveDirectionI <= 1; moveDirectionI++){
            for (int moveDirectionJ = -1; moveDirectionJ <=1; moveDirectionJ++){
                int wantedI = currentI + moveDirectionI;
                int wantedJ = currentJ + moveDirectionJ;
                if (wantedI >= 0 && wantedI < chessBoard.getSIZE_OF_BOARD()
                        && wantedJ >= 0 && wantedJ < chessBoard.getSIZE_OF_BOARD()) {

                    if (king.canMoveTo(chessBoard.getBoardField(wantedI, wantedJ))){
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public boolean kingIsUnderAttackIfFigureIsMoved(Figure figure) {
        List<Figure> figures = chessBoard.getAliveFigures();
        Color enemyColor;
        if (figure.getColor() == Color.WHITE){
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        figure.getPosition().setFigure(null);
        Figure king = getKing(figures, figure.getColor());
        if(fieldIsUnderAttack(king.getPosition(), enemyColor)){
            figure.getPosition().setFigure(figure);
            return true;
        } else {
            figure.getPosition().setFigure(figure);
            return false;
        }
    }


}
