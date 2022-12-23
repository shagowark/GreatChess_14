package ru.vsu.csf.greatChess.game;

public enum GameStatus {
    FIELD_EMPTY,
    WRONG_COLOR,
    INVALID_COORD,
    FIGURE_CHOSEN,
    FIGURE_MOVED,
    KING_UNDER_ATTACK,
    CANT_MOVE,
    WHITE_CHECKED,
    BLACK_CHECKED,
    WHITE_MATED,
    BLACK_MATED;
}
