package consoleLibrary;

import model.ConsoleSprite;

public enum ConsoleSprites
{
    NORMALCASE (new ColoredChar(Draw.BLOCK, Color.GREEN)),
    BOMBECASE (new ColoredChar(Draw.BLOCK, Color.RED)),
    TELEPORTATIONCASE (new ColoredChar(Draw.BLOCK, Color.BLUE)),
    BIGSTONECASE (new ColoredChar(Draw.BLOCK, Color.BLACK)),
    HEAPOFSTONE (new ColoredChar(Draw.BLOCK, Color.GREY)),
    VICTORYCASE (new ColoredChar(Draw.BLOCK, Color.YELLOW));

    final ColoredChar coloredChar;
    ConsoleSprites(ColoredChar coloredChar)
    {
        this.coloredChar = coloredChar;
    }

    public ConsoleSprite getValue(){
        return new ConsoleSprite(this.coloredChar);
    }

    @Override
    public String toString()
    {
        return coloredChar.toString();
    }
}
