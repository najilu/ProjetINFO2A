package ConsoleLibrary;

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

    public ColoredChar(String string, Color color){
        this(string.charAt(0), string.charAt(1), color);
    }

    public ColoredChar(String string){
        this(string.charAt(0), string.charAt(1), Color.RESET);
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
        return color + "" +c+ ((optionnalChar == 0) ? "" : optionnalChar);
    }
}
