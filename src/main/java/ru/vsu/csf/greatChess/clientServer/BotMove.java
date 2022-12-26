package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.chessBoard.Coordinates;

public class BotMove {
    private Coordinates chosenField;
    private Coordinates wantedField;

    public BotMove(){}

    public BotMove(Coordinates wantedField, Coordinates chosenField){
        this.chosenField = chosenField;
        this.wantedField = wantedField;
    }

    public Coordinates getChosenField() {
        return chosenField;
    }

    public void setChosenField(Coordinates chosenField) {
        this.chosenField = chosenField;
    }

    public Coordinates getWantedField() {
        return wantedField;
    }

    public void setWantedField(Coordinates wantedField) {
        this.wantedField = wantedField;
    }
}
