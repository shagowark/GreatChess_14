package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.chessBoard.ChessBoard;
import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.chessBoard.Coordinates;
import ru.vsu.csf.greatChess.figures.Figure;
import ru.vsu.csf.greatChess.game.CheckMateOperator;
import ru.vsu.csf.greatChess.game.Game;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static ru.vsu.csf.greatChess.game.GameStatus.FIGURE_CHOSEN;
import static ru.vsu.csf.greatChess.game.GameStatus.FIGURE_MOVED;

public class SimpleBot {
    final Game game;
    private final Random random = new Random();

    public SimpleBot(Game game){
        this.game = game;
    }

    public Coordinates generateCoord(int sizeOfBoard){
        int i = random.nextInt(sizeOfBoard);
        int j = random.nextInt(sizeOfBoard);
        return new Coordinates(i, j);
    }


    public BotMove generateMove(){
        BotMove resultMove = new BotMove();
        while (true) {
            Coordinates randomCoords = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());
            while (game.tryChooseField(randomCoords) != FIGURE_CHOSEN) {
                randomCoords = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());
            }
            Figure chosenFigure = game.getChessBoard().getBoardField(randomCoords).getFigure();
            CheckMateOperator operator = game.getCheckMateOperator();
            if (!operator.figureCanMoveSomewhere(chosenFigure)) {
                continue;
            }
            resultMove.setChosenField(randomCoords);

            while (game.tryChooseField(randomCoords) != FIGURE_MOVED) {
                game.setCurrentFigure(chosenFigure);
                randomCoords = generateCoord(game.getChessBoard().getSIZE_OF_BOARD());

            }
            resultMove.setWantedField(randomCoords);
            break;
        }
        return resultMove;
    }
}
