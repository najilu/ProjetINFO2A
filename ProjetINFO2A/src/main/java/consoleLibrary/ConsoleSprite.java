package consoleLibrary;

import consoleLibrary.Color;
import consoleLibrary.ColoredChar;

public class ConsoleSprite
{
    ColoredChar coloredChar;

    public ConsoleSprite(char c, Color color) {
        coloredChar = new ColoredChar(c, color);
    }

    public ConsoleSprite(char c, char c2, Color color){
        coloredChar = new ColoredChar(c, c2, color);
    }

    public ConsoleSprite(ColoredChar coloredChar)
    {
        this.coloredChar = coloredChar;
    }

    public ColoredChar getColoredChar(){
        return coloredChar;
    }
}
