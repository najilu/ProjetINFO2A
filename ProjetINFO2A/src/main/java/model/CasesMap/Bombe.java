package model.CasesMap;

import ConsoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class Bombe extends SpritableEntity
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
