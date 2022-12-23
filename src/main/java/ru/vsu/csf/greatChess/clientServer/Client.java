package ru.vsu.csf.greatChess.clientServer;

import ru.vsu.csf.greatChess.chessBoard.ChessBoardField;
import ru.vsu.csf.greatChess.windowApp.ClickListener;
import ru.vsu.csf.greatChess.windowApp.WindowApp;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private WindowApp windowApp;

    public static void main(String[] args) throws IOException {
        WindowApp windowApp = new WindowApp();
        Client client = new Client("localhost", 7777, windowApp);
        client.start();
    }

    public Client(String host, int port, WindowApp windowApp){
        this.host = host;
        this.port = port;
        this.windowApp = windowApp;
    }

    private void showWindow(){
        windowApp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowApp.setSize(650, 650);
        windowApp.setVisible(true);
    }

    public void start()throws IOException{
        showWindow();
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String info;
        while(!socket.isClosed() || windowApp.isActive()){
            ClickListener listener = (ClickListener) windowApp.getDrawPanel().getMouseListener();
            ChessBoardField clickedField =  listener.getClickedField();
            if(clickedField!=null){
                System.out.println("To server: " + clickedField.getI() + " " + clickedField.getJ());
                out.println(clickedField.getI() + " " + clickedField.getJ());
                listener.setClickedField(null);

                info = in.readLine();
                System.out.println("From server: " + info);
                String[] parsed = info.split(" ");
                if(parsed[1].matches("WIN")){
                    break;
                }
            }
        }
        socket.close();
    }
}
