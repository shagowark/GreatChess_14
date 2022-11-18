package ru.vsu.csf.greatChess.game;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.Coordinates;
import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;

public class Game {
    private ChessBoard chessBoard = new ChessBoard();
    private GameOperator gameOperator = new GameOperator(chessBoard);
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

    public MoveStatus tryChooseFigure(Coordinates coord) {
        return tryChooseFigure(coord.getI(), coord.getJ());
    }

    public MoveStatus tryChooseFigure(int i, int j) {
        if (!coordinatesAreRight(i, j)){
            return MoveStatus.WRONG_COORD;
        }
        if (!fieldHasFigure(i, j)){
            return MoveStatus.FIELD_EMPTY;
        }
        if (isWhiteMove){
            if (chessBoard.getBoardField(i, j).getFigure().getColor() == Color.BLACK){
                return MoveStatus.WRONG_COLOR;
            }
        } else {
            if (chessBoard.getBoardField(i, j).getFigure().getColor() == Color.WHITE){
                return MoveStatus.WRONG_COLOR;
            }
        }
        currentFigure = chessBoard.getBoardField(i, j).getFigure();
        return MoveStatus.CORRECT;
    }


    public MoveStatus tryMoveFigureTo(int i, int j){
        if (!coordinatesAreRight(i, j)){
            return MoveStatus.WRONG_COORD;
        }
        if (!currentFigure.canMoveTo(chessBoard.getBoardField(i, j))){
            return MoveStatus.CANT_MOVE;
        }
        if (gameOperator.kingIsUnderAttackIfFigureIsMovedTo(currentFigure, chessBoard.getBoardField(i, j))){
            return MoveStatus.KING_UNDER_ATTACK;
        }
        currentFigure.moveTo(chessBoard.getBoardField(i, j)); // todo не бьют пешки??
        MoveStatus status = gameOperator.checkGameStatus(currentFigure);
        if (status == MoveStatus.WHITE_MATED || status == MoveStatus.BLACK_MATED){
            gameIsEnded = true;
        }
        isWhiteMove = !isWhiteMove;
        return status;
    }

    public MoveStatus tryMoveFigureTo(Coordinates coord){
        return tryMoveFigureTo(coord.getI(), coord.getJ());
    }
}
