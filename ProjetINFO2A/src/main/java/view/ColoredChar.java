package view;

public class ColoredChar
{
    private char c;
    private Color color;

    public ColoredChar(char c, Color color)
    {
        this.c = c;
        this.color = color;
    }

    public ColoredChar(char c)
    {
        this.c = c;
        this.color = Color.RESET;
    }

    public char getC()
    {
        return c;
    }

    @Override
    public String toString()
    {
        return color + "" +c+c;
    }
}
