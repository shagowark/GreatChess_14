package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.chessBoard.Coordinates;

public class SimpleBot {
    public Coordinates generateCoord(int sizeOfBoard){
        int i = (int) (Math.random() * (sizeOfBoard - 1)) + 1;
        int j = (int) (Math.random() * (sizeOfBoard - 1)) + 1;
        return new Coordinates(i, j);
    }
}
