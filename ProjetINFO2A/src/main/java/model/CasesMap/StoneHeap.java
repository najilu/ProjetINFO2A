package model.CasesMap;

import consoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class StoneHeap extends SpritableEntity
{
    private int stoneCount;

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public StoneHeap(Sprite3D sprite3D, ConsoleSprite consoleSprite, int stoneCount)
    {
        super(sprite3D, consoleSprite);
        this.stoneCount = stoneCount;
    }

    public StoneHeap(ConsoleSprite consoleSprite, int stoneCount){
        this(new Sprite3D(), consoleSprite, stoneCount);
    }
}
