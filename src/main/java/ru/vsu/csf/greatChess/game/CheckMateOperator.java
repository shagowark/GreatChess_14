package ru.vsu.csf.greatChess.game;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.figures.Figure;
import ru.vsu.csf.greatChess.figures.King;
import ru.vsu.csf.greatChess.figures.Pawn;

import java.awt.*;
import java.util.List;

public class CheckMateOperator {
    ChessBoard chessBoard;

    public CheckMateOperator(ChessBoard chessBoard) {
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
        if (figure.getColor() == Color.WHITE) {
            king = getKing(figures, Color.BLACK);
        } else {
            king = getKing(figures, Color.WHITE);
        }

        if (!figure.canMoveTo(king.getPosition())) {
            return MoveStatus.CORRECT;
        } else {
            if (kingCanMoveSomewhere(king)) {
                if (king.getColor() == Color.WHITE) {
                    return MoveStatus.WHITE_CHECKED;
                } else return MoveStatus.BLACK_CHECKED;
            } else {
                for (Figure someFigure : chessBoard.getAliveFigures()) {
                    if (someFigure.getColor() != king.getColor()) {
                        continue;
                    }
                    for (ChessBoardField field : someFigure.getReachableFields()) {
                        if (!kingIsUnderAttackIfFigureIsMovedTo(someFigure, field)) {
                            if (king.getColor() == Color.WHITE) {
                                return MoveStatus.WHITE_CHECKED;
                            } else return MoveStatus.BLACK_CHECKED;
                        }
                    }
                    for (ChessBoardField field : someFigure.getKillableFields()) {
                        if (!kingIsUnderAttackIfFigureIsMovedTo(someFigure, field)) {
                            if (king.getColor() == Color.WHITE) {
                                return MoveStatus.WHITE_CHECKED;
                            } else return MoveStatus.BLACK_CHECKED;
                        }
                    }
                }
                if (king.getColor() == Color.WHITE) {
                    return MoveStatus.WHITE_MATED;
                } else return MoveStatus.BLACK_MATED;
            }
        }
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

        for (int moveDirectionI = -1; moveDirectionI <= 1; moveDirectionI++) {
            for (int moveDirectionJ = -1; moveDirectionJ <= 1; moveDirectionJ++) {
                int wantedI = currentI + moveDirectionI;
                int wantedJ = currentJ + moveDirectionJ;
                if (wantedI >= 0 && wantedI < chessBoard.getSIZE_OF_BOARD()
                        && wantedJ >= 0 && wantedJ < chessBoard.getSIZE_OF_BOARD()) {

                    if (king.canMoveTo(chessBoard.getBoardField(wantedI, wantedJ))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // подразумевается, что передается клетка, куда фигура может походить (уже проверена)
    public boolean kingIsUnderAttackIfFigureIsMovedTo(Figure figure, ChessBoardField wantedField) {
        Color enemyColor;
        if (figure.getColor() == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        if (figure.getClass() == King.class) {
            return !figure.canMoveTo(wantedField);
        }

        Figure king = getKing(chessBoard.getAliveFigures(), figure.getColor());

        ChessBoardField startPosition = figure.getPosition();
        figure.getPosition().setFigure(null);

        Figure prevFigure = null;
        if (wantedField.hasFigure()) {
            prevFigure = wantedField.getFigure();
        }
        wantedField.setFigure(figure);

        if (fieldIsUnderAttack(king.getPosition(), enemyColor)) {
            if (prevFigure != null) {
                wantedField.setFigure(prevFigure);
            }
            wantedField.setFigure(null);
            startPosition.setFigure(figure);
            return true;
        } else {
            if (prevFigure != null) {
                wantedField.setFigure(prevFigure);
            } else {
                wantedField.setFigure(null);
            }
            startPosition.setFigure(figure);
            return false;
        }
    }

    /**
     * @param field
     * @param color цвет фигур, атакующих поле
     * @return
     * @throws Exception
     */
    public boolean fieldIsUnderAttack(ChessBoardField field, Color color) {
        boolean changed = false;
        if (!field.hasFigure()) {
            changed = true;
            Color c;
            if (color == Color.WHITE) {
                c = Color.BLACK;
            } else {
                c = Color.WHITE;
            }
            field.setFigure(new Pawn(c, field)); // добавим фигуру на поле, чтобы правильно работала проверка canMoveTo для пешек
        }

        for (Figure figure : chessBoard.getAliveFigures()) {
            if (figure.getColor() == color && figure.canMoveTo(field)) {
                if (changed) {
                    field.setFigure(null);
                }

                return true;
            }
        }
        if (changed) {
            field.setFigure(null);
        }
        return false;
    }

}
