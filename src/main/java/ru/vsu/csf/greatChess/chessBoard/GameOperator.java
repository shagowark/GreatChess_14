package ru.vsu.csf.greatChess.chessBoard;

import ru.vsu.csf.greatChess.figures.Figure;
import ru.vsu.csf.greatChess.figures.King;

import java.awt.*;
import java.util.List;

public class GameOperator {
    /**
     * Подразумевается, что метод вызывается после каждого хода, т.е. проверяет,
     * поставила ли мат или шах последняя ходившая фигура
     *
     * @param figure последняя ходившая фигура
     * @return
     */
    public static GameStatus checkGameStatus(Figure figure) throws Exception {
        ChessBoard chessBoard = figure.getPosition().getChessBoard();
        List<Figure> figures = chessBoard.getAliveFigures();
        if (getKing(figures, Color.WHITE) == null || getKing(figures, Color.BLACK) == null){
            throw new Exception("Нет короля!");
        }

        Figure king;
        if (figure.getColor() == Color.WHITE){
            king = getKing(figures, Color.BLACK);
        } else {
            king = getKing(figures, Color.WHITE);
        }

        if (!figure.canMoveTo(king.getPosition())){
            return GameStatus.FREE_GAME;
        } else {
            if (kingCanMoveSomewhere(king)){
                if (king.getColor() == Color.WHITE){
                    return GameStatus.WHITE_CHECKED;
                } else return GameStatus.BLACK_CHECKED;
            } else {
                if (fieldIsUnderAttack(figure.getPosition(), king.getColor())){
                    if (king.getColor() == Color.WHITE){
                        return GameStatus.WHITE_CHECKED;
                    } else return GameStatus.BLACK_CHECKED;
                } else {
                    if (king.getColor() == Color.WHITE){
                        return GameStatus.WHITE_MATED;
                    } else return GameStatus.BLACK_MATED;
                }
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
    public static boolean fieldIsUnderAttack(ChessBoardField field, Color color) throws Exception {
        ChessBoard chessBoard = field.getChessBoard();
        List<Figure> figures = chessBoard.getAliveFigures();
        for (Figure figure : figures) {
            if (figure.getColor() == color && figure.canMoveTo(field)) {
                return true;
            }
        }
        return false;
    }

    private static Figure getKing(List<Figure> figures, Color color) {
        for (Figure figure : figures) {
            if (figure.getClass() == King.class && figure.getColor() == color) {
                return figure;
            }
        }
        return null;
    }

    private static boolean kingCanMoveSomewhere(Figure king) throws Exception {
        int currentI = king.getPosition().getI();
        int currentJ = king.getPosition().getJ();
        ChessBoard chessBoard = king.getPosition().getChessBoard();

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
}
