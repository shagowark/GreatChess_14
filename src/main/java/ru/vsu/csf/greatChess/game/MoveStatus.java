package ru.vsu.csf.greatChess.game;

public enum MoveStatus {
    FIELD_EMPTY,
    WRONG_COLOR,
    WRONG_COORD,
    CORRECT,
    KING_UNDER_ATTACK,
    CANT_MOVE,
    WHITE_CHECKED,
    BLACK_CHECKED,
    WHITE_MATED,
    BLACK_MATED;
}
