package model;

import view.Color;
import view.ColoredChar;

public class Sprite {
    ColoredChar coloredChar;

    public Sprite(char c, Color color) {
        coloredChar = new ColoredChar(c, color);
    }

    public ColoredChar getColoredChar(){
        return coloredChar;
    }
}
