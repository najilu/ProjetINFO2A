package model.CasesMap;

import ConsoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class TeleportationCase extends SpritableEntity
{
    private final int targetCol;
    private final int targetRow;

    public int[] getTarget(){
        return new int[]{targetCol, targetRow};
    }

    public TeleportationCase(int targetCol, int targetY, Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        super(sprite3D, consoleSprite);
        this.targetCol = targetCol;
        this.targetRow = targetY;
    }

    public TeleportationCase(int targetCol, int targetRow, ConsoleSprite consoleSprite)
    {
        super(new Sprite3D(), consoleSprite);
        this.targetCol = targetCol;
        this.targetRow = targetRow;
    }
}
