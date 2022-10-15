package ru.vsu.csf.greatChess.chessBoard;

import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final int SIZE_OF_BOARD = 10;
    private final ChessBoardField[][] board = new ChessBoardField[SIZE_OF_BOARD][SIZE_OF_BOARD];
    private List<Figure> deadFigures = new ArrayList<>();
    public ChessBoard() {
        initializeChessBoard();
    }

    public ChessBoardField getBoardField(int i, int j) {
        return board[i][j];
    }


    public int getSIZE_OF_BOARD() {
        return SIZE_OF_BOARD;
    }

    public List<Figure> getAliveFigures(){
        List<Figure> figures = new ArrayList<>();

        for (ChessBoardField[] line : board){
            for (ChessBoardField field : line){
                if (field.hasFigure()){
                    figures.add(field.getFigure());
                }
            }
        }

        return figures;
    }

    public void addDeadFigure(Figure figure){
        deadFigures.add(figure);
    }

    public List<Figure> getDeadFigures(){
        return deadFigures;
    }
    private void initializeChessBoard(){
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                board[i][j] = new ChessBoardField(this, i, j);
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
