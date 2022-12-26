package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.chessBoard.Coordinates;
import ru.vsu.csf.greatChess.figures.Figure;
import ru.vsu.csf.greatChess.game.CheckMateOperator;
import ru.vsu.csf.greatChess.game.Game;

import static ru.vsu.csf.greatChess.game.GameStatus.FIGURE_CHOSEN;
import static ru.vsu.csf.greatChess.game.GameStatus.FIGURE_MOVED;

public class SimpleBot {
    final Game game;

    public SimpleBot(Game game){
        this.game = game;
    }

    public Coordinates generateCoord(int sizeOfBoard){
        int i = (int) (Math.random() * (sizeOfBoard - 1)) + 1;
        int j = (int) (Math.random() * (sizeOfBoard - 1)) + 1;
        return new Coordinates(i, j);
    }

    public BotMove generateMove(){
        BotMove resultMove = new BotMove();
        while (true) {
            Coordinates botMove = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());
            while (game.tryChooseField(botMove) != FIGURE_CHOSEN) {
                botMove = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());
            }
            Figure chosenFigure = game.getChessBoard().getBoardField(botMove).getFigure();
            CheckMateOperator operator = game.getCheckMateOperator();
            if (!operator.figureCanMoveSomewhere(chosenFigure)) {
                continue; //todo криво работает figurecanmovesomewhere
            }
            resultMove.setChosenField(botMove);

            while (game.tryChooseField(botMove) != FIGURE_MOVED) {
                game.setCurrentFigure(chosenFigure);
                botMove = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());

            }
            resultMove.setWantedField(botMove);
            break;
        }
        return resultMove;
    }
}
