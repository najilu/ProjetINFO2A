package model;

public class StoneHeap extends Entity
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
