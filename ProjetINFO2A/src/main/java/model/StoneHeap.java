package model;

public class StoneHeap extends Entity
{
    public StoneHeap(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        super(sprite3D, consoleSprite);
    }

    public StoneHeap(ConsoleSprite consoleSprite){
        this(new Sprite3D(), consoleSprite);
    }
}
