package consoleLibrary;

public enum ConsoleSprites
{
    NORMALCASE (new ColoredChar("\uD83D\uDFE9")),
    BOMBECASE (new ColoredChar("\uD83D\uDCA3")),
    TELEPORTATIONCASE (new ColoredChar("\uD83C\uDF00")),
    BIGSTONECASE (new ColoredChar("\uD83C\uDF32")),
    HEAPOFSTONE (new ColoredChar("🪨")),
    VICTORYCASE (new ColoredChar('⭐')),
    CURSORCASE (new ColoredChar("\uD83C\uDFAF"));

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
