package ru.vsu.csf.greatChess.console;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.game.Game;
import ru.vsu.csf.greatChess.figures.*;

import java.awt.*;
import java.util.Scanner;

public class ConsoleGame {
    private Game game = new Game();

    public void startConsoleGame() throws Exception {
        drawChessBoard(game.getChessBoard());
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameEnded()) {
            System.out.println("Введите номер клетки, на которой стоит фигура, которой хотите походить");
            int j = scanner.next().charAt(0) - 97;
            int i = Math.abs(scanner.nextInt() - 10);

            switch (game.tryChooseFigure(i, j)) {
                case WRONG_COORD:
                    System.out.println("Неверные координаты клетки!");
                    continue;
                case FIELD_EMPTY:
                    System.out.println("Это пустая клетка!");
                    continue;
                case WRONG_COLOR:
                    System.out.println("Ход другого игрока!");
                    continue;
            }

            System.out.println("Введите номер клетки, куда хотите походить");
            int wantedJ = scanner.next().charAt(0) - 97;
            int wantedI = Math.abs(scanner.nextInt() - 10);

            switch (game.tryMoveFigureTo(wantedI, wantedJ)) {
                case WRONG_COORD:
                    System.out.println("Неверные координаты клетки!");
                    continue;
                case WRONG_COLOR:
                    System.out.println("Ход другого игрока");
                    continue;
                case CANT_MOVE:
                    System.out.println("Нельзя сделать такой ход!");
                    continue;
                case KING_UNDER_ATTACK:
                    System.out.println("Нельзя сделать такой ход, шах королю!");
                    continue;
                case CORRECT:
                    continue;
                case WHITE_CHECKED:
                    System.out.println("Шах белым!");
                case WHITE_MATED:
                    System.out.println("Победа черных!");
                case BLACK_CHECKED:
                    System.out.println("Шах черным!");
                case BLACK_MATED:
                    System.out.println("Победа белых!");
            }

            drawChessBoard(game.getChessBoard());


            System.out.println();
        }

    }

    private static void drawChessBoard(ChessBoard chessBoard) {
        for (int i = -1; i < chessBoard.getSIZE_OF_BOARD(); i++) {
            if (i == -1) {
                System.out.print("   ");
                for (char k = 'a'; k <= 'j'; k++) {
                    System.out.print(k + " ");
                }
                System.out.println();
                continue;
            }

            for (int j = -1; j < chessBoard.getSIZE_OF_BOARD(); j++) {
                if (j == -1) {
                    if (Math.abs(i - 10) != 10) {
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
        for (Figure figure : chessBoard.getDeadFigures()) {
            drawFigure(figure);
        }
        System.out.println();

    }

    private static void drawFigure(Figure figure) {
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
