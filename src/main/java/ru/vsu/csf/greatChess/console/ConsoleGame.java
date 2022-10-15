package ru.vsu.csf.greatChess.console;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.GameOperator;
import ru.vsu.csf.greatChess.chessBoard.GameStatus;
import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;
import java.util.Scanner;

public class ConsoleGame {
    private static boolean nowIsWhiteMove = true;
    private static boolean gameIsEnded = false;
    public static void startConsoleGame() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        drawChessBoard(chessBoard);
        Scanner scanner = new Scanner(System.in);

        while (!gameIsEnded){
            System.out.println("Введите номер клетки, на которой стоит фигура, которой хотите походить");
            int j = scanner.next().charAt(0) - 97;
            int i = Math.abs(scanner.nextInt() - 10);
            if (i < 0 || i > chessBoard.getSIZE_OF_BOARD() || j < 0 || j > chessBoard.getSIZE_OF_BOARD()){
                System.out.println("Неверные координаты клетки!");
                continue;
            }

            if (!chessBoard.getBoardField(i, j).hasFigure()){
                System.out.println("Это пустая клетка!");
                continue;
            }

            if (nowIsWhiteMove){
                if (chessBoard.getBoardField(i, j).getFigure().getColor() != Color.WHITE){
                    System.out.println("Сейчас ходит другая сторона!");
                    continue;
                } else {
                    nowIsWhiteMove = !nowIsWhiteMove;
                }
            } else {
                if (chessBoard.getBoardField(i, j).getFigure().getColor() != Color.BLACK){
                    System.out.println("Сейчас ходит другая сторона!");
                    continue;
                } else{
                    nowIsWhiteMove = !nowIsWhiteMove;
                }
            }

            System.out.println("Введите номер клетки, куда хотите походить");
            int wantedJ = scanner.next().charAt(0) - 97;
            int wantedI = Math.abs(scanner.nextInt() - 10);
            if (wantedI < 0 || wantedI > chessBoard.getSIZE_OF_BOARD() || wantedJ < 0 || wantedJ > chessBoard.getSIZE_OF_BOARD()){
                System.out.println("Неверные координаты клетки!");
                continue;
            }

            if(chessBoard.getBoardField(i, j).getFigure().moveTo(chessBoard.getBoardField(wantedI, wantedJ))){
                drawChessBoard(chessBoard);
                GameStatus status = GameOperator.checkGameStatus(chessBoard.getBoardField(i, j).getFigure());
                if (status == GameStatus.BLACK_MATED){
                    gameIsEnded = true;
                    System.out.println("Победа белых!");
                    break;
                } else if (status == GameStatus.WHITE_MATED) {
                    gameIsEnded = true;
                    System.out.println("Победа черных!");
                    break;
                } else if (status == GameStatus.BLACK_CHECKED) {
                    System.out.println("Шах черным!");
                }else if (status == GameStatus.WHITE_CHECKED) {
                    System.out.println("Шах белым!");
                }
            } else{
                System.out.println("Невозможно сделать такой ход!");
            }
            System.out.println();
        }
    }

    private static void drawChessBoard(ChessBoard chessBoard) {
        for (int i = -1; i < chessBoard.getSIZE_OF_BOARD(); i++) {
            if (i == -1){
                System.out.print("   ");
                for (char k = 'a'; k <= 'j'; k++){
                    System.out.print(k + " ");
                }
                System.out.println();
                continue;
            }

            for (int j = -1; j < chessBoard.getSIZE_OF_BOARD(); j++) {
                if (j == -1){
                    if (Math.abs(i - 10) != 10){
                        System.out.print(" ");
                    }
                    System.out.print(Math.abs(i - 10) + " ");
                    continue;
                }

                if (chessBoard.getBoardField(i, j).getFigure() == null) {
                    System.out.print("# ");
                } else {
                    drawFigure(chessBoard.getBoardField(i, j).getFigure());
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.print("Битые фигуры: ");
        for (Figure figure : chessBoard.getDeadFigures()){
            drawFigure(figure);
        }
        System.out.println();

    }

    private static void drawFigure(Figure figure){
        if (figure.getColor() == Color.WHITE) {
            if (figure.getClass() == Rook.class) {
                System.out.print("R ");
            } else if (figure.getClass() == Knight.class) {
                System.out.print("N ");
            } else if (figure.getClass() == Bishop.class) {
                System.out.print("B ");
            } else if (figure.getClass() == Vizier.class) {
                System.out.print("V ");
            } else if (figure.getClass() == Giraffe.class) {
                System.out.print("G ");
            } else if (figure.getClass() == King.class) {
                System.out.print("K ");
            } else if (figure.getClass() == Queen.class) {
                System.out.print("Q ");
            } else if (figure.getClass() == CombatMachine.class) {
                System.out.print("C ");
            } else if (figure.getClass() == Pawn.class) {
                System.out.print("p ");
            }
        } else {
            if (figure.getClass() == Rook.class) {
                System.out.print("\033[3mR \033[0m");
            } else if (figure.getClass() == Knight.class) {
                System.out.print("\033[3mN \033[0m");
            } else if (figure.getClass() == Bishop.class) {
                System.out.print("\033[3mB \033[0m");
            } else if (figure.getClass() == Vizier.class) {
                System.out.print("\033[3mV \033[0m");
            } else if (figure.getClass() == Giraffe.class) {
                System.out.print("\033[3mG \033[0m");
            } else if (figure.getClass() == King.class) {
                System.out.print("\033[3mK \033[0m");
            } else if (figure.getClass() == Queen.class) {
                System.out.print("\033[3mQ \033[0m");
            } else if (figure.getClass() == CombatMachine.class) {
                System.out.print("\033[3mC \033[0m");
            } else if (figure.getClass() == Pawn.class) {
                System.out.print("\033[3mp \033[0m");
            }
        }
    }
}
