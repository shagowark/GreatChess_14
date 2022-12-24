package ru.vsu.csf.greatChess.game;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.Coordinates;
import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;

public class Game {
    private ChessBoard chessBoard = new ChessBoard(this);
    private CheckMateOperator checkMateOperator = new CheckMateOperator(chessBoard);
    private boolean isWhiteMove = true;
    private boolean gameIsEnded = false;
    private Figure currentFigure = null;

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isGameEnded() {
        return gameIsEnded;
    }

    public boolean isWhiteMove() {
        return isWhiteMove;
    }

    public boolean coordinatesAreRight(Coordinates coord) {
        return coordinatesAreRight(coord.getI(), coord.getJ());
    }

    public boolean coordinatesAreRight(int i, int j) {
        return i >= 0 && i <= chessBoard.getSIZE_OF_BOARD() && j >= 0 && j <= chessBoard.getSIZE_OF_BOARD();
    }

    private boolean fieldHasFigure(int i, int j) {
        return chessBoard.getBoardField(i, j).hasFigure();
    }

    private boolean fieldHasFigure(Coordinates coord) {
        return fieldHasFigure(coord.getI(), coord.getJ());
    }

    public GameStatus tryChooseFigure(Coordinates coord) {
        return tryChooseFigure(coord.getI(), coord.getJ());
    }
    public GameStatus tryChooseField(int i, int j){
        if (currentFigure == null){
            return tryChooseFigure(i, j);
        } else{
            return tryMoveFigureTo(i, j);
        }
    }
    private GameStatus tryChooseFigure(int i, int j) {
        if (!coordinatesAreRight(i, j)){
            return GameStatus.INVALID_COORD;
        }
        if (!fieldHasFigure(i, j)){
            return GameStatus.FIELD_EMPTY;
        }
        if (isWhiteMove){
            if (chessBoard.getBoardField(i, j).getFigure().getColor() == Color.BLACK){
                return GameStatus.WRONG_COLOR;
            }
        } else {
            if (chessBoard.getBoardField(i, j).getFigure().getColor() == Color.WHITE){
                return GameStatus.WRONG_COLOR;
            }
        }
        currentFigure = chessBoard.getBoardField(i, j).getFigure();
        return GameStatus.FIGURE_CHOSEN;
    }


    private GameStatus tryMoveFigureTo(int i, int j){ // передавать сразу field, проверку для координат
        if (!coordinatesAreRight(i, j)){
            currentFigure = null;
            return GameStatus.INVALID_COORD;
        }
        if (!currentFigure.canMoveTo(chessBoard.getBoardField(i, j))){
            currentFigure = null;
            return GameStatus.CANT_MOVE;
        }
        if (checkMateOperator.kingIsUnderAttackIfFigureIsMovedTo(currentFigure, chessBoard.getBoardField(i, j))){
            currentFigure = null;
            return GameStatus.KING_UNDER_ATTACK;
        }
        currentFigure.moveTo(chessBoard.getBoardField(i, j));
        currentFigure = chessBoard.getBoardField(i, j).getFigure(); // на случай, если пешка превратилась в ферзя
        GameStatus status = checkMateOperator.checkGameStatus(currentFigure);
        if (status == GameStatus.WHITE_MATED || status == GameStatus.BLACK_MATED){
            gameIsEnded = true;
        }
        isWhiteMove = !isWhiteMove;
        currentFigure = null;
        return status;
    }

    public GameStatus tryMoveFigureTo(Coordinates coord){
        return tryMoveFigureTo(coord.getI(), coord.getJ());
    }
}
