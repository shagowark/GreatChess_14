package ru.vsu.csf.greatChess.console;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;
import java.util.Scanner;

public class ConsoleGame {
    private static boolean nowIsWhiteMove = true;
    public static void startConsoleGame() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        drawChessBoard(chessBoard);
        Scanner scanner = new Scanner(System.in);
        //todo шах и мат, заграничные координаты
        while (true){
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
                } else if (chessBoard.getBoardField(i, j).getFigure().getColor() == Color.WHITE) {
                    if (chessBoard.getBoardField(i, j).getFigure().getClass() == Rook.class) {
                        System.out.print("R ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Knight.class) {
                        System.out.print("N ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Bishop.class) {
                        System.out.print("B ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Vizier.class) {
                        System.out.print("V ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Giraffe.class) {
                        System.out.print("G ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == King.class) {
                        System.out.print("K ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Queen.class) {
                        System.out.print("Q ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == CombatMachine.class) {
                        System.out.print("C ");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Pawn.class) {
                        System.out.print("p ");
                    }
                } else {
                    if (chessBoard.getBoardField(i, j).getFigure().getClass() == Rook.class) {
                        System.out.print("\033[3mR \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Knight.class) {
                        System.out.print("\033[3mN \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Bishop.class) {
                        System.out.print("\033[3mB \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Vizier.class) {
                        System.out.print("\033[3mV \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Giraffe.class) {
                        System.out.print("\033[3mG \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == King.class) {
                        System.out.print("\033[3mK \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Queen.class) {
                        System.out.print("\033[3mQ \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == CombatMachine.class) {
                        System.out.print("\033[3mC \033[0m");
                    } else if (chessBoard.getBoardField(i, j).getFigure().getClass() == Pawn.class) {
                        System.out.print("\033[3mp \033[0m");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
