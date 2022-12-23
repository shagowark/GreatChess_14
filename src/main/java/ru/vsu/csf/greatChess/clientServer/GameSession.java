package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameSession implements Runnable{

    private Game game;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameSession(Socket socket){
        game = new Game();
        this.socket = socket;

    }

    @Override
    public void run() {
        while (!game.isGameEnded()){
            serverMove();
        }
        System.out.println("Game ended");
    }

    public void serverMove(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String command;
            while((command = in.readLine())==null){
            }
            System.out.println("From client: " + command);
            String[] coordinate = command.split(" ");
            String answer = "";
            switch (game.tryChooseField(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]))){
                case INVALID_COORD -> answer = "INVALID COORDS";
                case WRONG_COLOR -> answer = "ANOTHER PLAYER'S TURN";
                case FIELD_EMPTY -> answer = "CAN'T CHOOSE FIGURE ON EMPTY FIELD";
                case KING_UNDER_ATTACK -> answer = "CAN'T MOVE, OPENS KING TO ATTACK";
                case BLACK_MATED -> answer = "WHITE WIN";
                case WHITE_MATED -> answer = "BLACK WIN";
                case CANT_MOVE -> answer = "CAN'T MAKE THAT MOVE";
                case FIGURE_CHOSEN -> answer = "FIGURE IS SELECTED";
                case FIGURE_MOVED -> answer = "FIGURE IS MOVED";
                case WHITE_CHECKED -> answer = "WHITE CHECKED";
                case BLACK_CHECKED -> answer = "BLACK CHECKED";
            }
            System.out.println("To client: " + answer);
            out.println(answer);
        } catch (IOException e) {
            throw new IllegalStateException("No connection with client", e);
        }
    }
}
