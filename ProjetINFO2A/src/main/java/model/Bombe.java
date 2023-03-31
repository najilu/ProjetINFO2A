package model;

public class Bombe extends Entity
{

    public Bombe(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        super(sprite3D, consoleSprite);
    }

    public Bombe(ConsoleSprite consoleSprite)
    {
        super(new Sprite3D(), consoleSprite);
    }

}
