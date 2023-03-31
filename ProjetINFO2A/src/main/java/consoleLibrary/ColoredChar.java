package consoleLibrary;

public class ColoredChar
{
    private char c;
    private char optionnalChar;
    private Color color;

    public ColoredChar(char c, Color color)
    {
        this.c = c;
        this.color = color;
    }

    public ColoredChar(char c, char c2, Color color){
        this(c, color);
        optionnalChar = c2;

    }


    public ColoredChar(char c)
    {
        this(c, Color.RESET);
    }

    public char getC()
    {
        return c;
    }

    @Override
    public String toString()
    {
        return color + "" +c+ ((optionnalChar == 0) ? c : optionnalChar);
    }
}
