package model;

public class BigStone extends Entity
{

    public BigStone(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        super(sprite3D, consoleSprite);
    }

    public BigStone(ConsoleSprite consoleSprite){
        this(new Sprite3D(), consoleSprite);
    }
}
