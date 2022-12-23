package ru.vsu.csf.greatChess;

import ru.vsu.csf.greatChess.console.ConsoleApp;
import ru.vsu.csf.greatChess.windowApp.WindowApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

        if ("-console".equals(args[0])) {
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.startConsoleApp();
        } else if ("-window".equals(args[0])) {
            WindowApp windowApp = new WindowApp();
            windowApp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            windowApp.setSize(650, 650);
            windowApp.setVisible(true);

        }
    }
}
