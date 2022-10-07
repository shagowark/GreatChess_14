package ru.vsu.csf.greatChess.chessBoard;

import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;

public class ChessBoard {
    private final int SIZE_OF_BOARD = 10;
    private final ChessBoardField[][] board = new ChessBoardField[SIZE_OF_BOARD][SIZE_OF_BOARD];

    public ChessBoard() {
        initializeChessBoard();
    }

    public int getBoardFieldI(ChessBoardField field) throws Exception {
        for (int i = 0; i < SIZE_OF_BOARD; i++){
            for (int j = 0; j < SIZE_OF_BOARD; j++){
                if (board[i][j] == field){
                    return i;
                }
            }
        }
        throw new Exception("Нет такого поля");
    }

    public int getBoardFieldJ(ChessBoardField field) throws Exception {
        for (int i = 0; i < SIZE_OF_BOARD; i++){
            for (int j = 0; j < SIZE_OF_BOARD; j++){
                if (board[i][j] == field){
                    return j;
                }
            }
        }
        throw new Exception("Нет такого поля");
    }
    public ChessBoardField getBoardField(int i, int j) {
        return board[i][j];
    }


    public int getSIZE_OF_BOARD() {
        return SIZE_OF_BOARD;
    }



    private void initializeChessBoard(){
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                board[i][j] = new ChessBoardField(this);
                if ((i + j) % 2 == 0) {
                    board[i][j].setColor(Color.WHITE);
                } else {
                    board[i][j].setColor(Color.BLACK);
                }
                if (i == SIZE_OF_BOARD - 1) {
                    if (j == 0 || j == SIZE_OF_BOARD - 1) {
                        board[i][j].setFigure(new Rook(Color.WHITE, board[i][j]));
                    }
                    if (j == 1 || j == SIZE_OF_BOARD - 2) {
                        board[i][j].setFigure(new Knight(Color.WHITE, board[i][j]));
                    }
                    if (j == 2 || j == SIZE_OF_BOARD - 3) {
                        board[i][j].setFigure(new Bishop(Color.WHITE, board[i][j]));
                    }
                    if (j == 3) {
                        board[i][j].setFigure(new Vizier(Color.WHITE, board[i][j]));
                    }
                    if (j == 4) {
                        board[i][j].setFigure(new Giraffe(Color.WHITE, board[i][j]));
                    }
                    if (j == 5) {
                        board[i][j].setFigure(new King(Color.WHITE, board[i][j]));
                    }
                    if (j == 6) {
                        board[i][j].setFigure(new Queen(Color.WHITE, board[i][j]));
                    }
                } else if (i == SIZE_OF_BOARD - 2) {
                    if (j == 4 || j == 5) {
                        board[i][j].setFigure(new CombatMachine(Color.WHITE, board[i][j]));
                    } else {
                        board[i][j].setFigure(new Pawn(Color.WHITE, board[i][j]));
                    }
                } else if (i == SIZE_OF_BOARD - 3) {
                    if (j == 4 || j == 5) {
                        board[i][j].setFigure(new Pawn(Color.WHITE, board[i][j]));
                    }
                } else if (i == 0) {
                    if (j == 0 || j == SIZE_OF_BOARD - 1) {
                        board[i][j].setFigure(new Rook(Color.BLACK, board[i][j]));
                    }
                    if (j == 1 || j == SIZE_OF_BOARD - 2) {
                        board[i][j].setFigure(new Knight(Color.BLACK, board[i][j]));
                    }
                    if (j == 2 || j == SIZE_OF_BOARD - 3) {
                        board[i][j].setFigure(new Bishop(Color.BLACK, board[i][j]));
                    }
                    if (j == 3) {
                        board[i][j].setFigure(new Queen(Color.BLACK, board[i][j]));
                    }
                    if (j == 4) {
                        board[i][j].setFigure(new King(Color.BLACK, board[i][j]));
                    }
                    if (j == 5) {
                        board[i][j].setFigure(new Giraffe(Color.BLACK, board[i][j]));
                    }
                    if (j == 6) {
                        board[i][j].setFigure(new Vizier(Color.BLACK, board[i][j]));
                    }
                } else if (i == 1) {
                    if (j == 4 || j == 5) {
                        board[i][j].setFigure(new CombatMachine(Color.BLACK, board[i][j]));
                    } else {
                        board[i][j].setFigure(new Pawn(Color.BLACK, board[i][j]));
                    }
                } else if (i == 2) {
                    if (j == 4 || j == 5) {
                        board[i][j].setFigure(new Pawn(Color.BLACK, board[i][j]));
                    }
                }
            }
        }
    }
}
