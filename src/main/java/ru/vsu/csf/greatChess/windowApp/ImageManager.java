package ru.vsu.csf.greatChess.windowApp;

import ru.vsu.csf.greatChess.figures.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    public static Image getImage(Figure figure){
        StringBuilder name = new StringBuilder("figureImages/");
        if(figure.getColor() == Color.WHITE){
            name.append("White");
        } else {
            name.append("Black");
        }
        name.append(getName(figure));
        name.append(".png");
        try {
            return ImageIO.read(new File(name.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getName(Figure figure){
        if (Bishop.class.equals(figure.getClass())) {
            return "Bishop";
        }
        if (CombatMachine.class.equals(figure.getClass())) {
            return "CombatMachine";
        }
        if (Giraffe.class.equals(figure.getClass())) {
            return "Giraffe";
        }
        if (King.class.equals(figure.getClass())) {
            return "King";
        }
        if (Knight.class.equals(figure.getClass())) {
            return "Knight";
        }
        if (Pawn.class.equals(figure.getClass())) {
            return "Pawn";
        }
        if (Queen.class.equals(figure.getClass())) {
            return "Queen";
        }
        if (Rook.class.equals(figure.getClass())) {
            return "Rook";
        }
        if (Vizier.class.equals(figure.getClass())) {
            return "Vizier";
        }
        return null;
    }
}
