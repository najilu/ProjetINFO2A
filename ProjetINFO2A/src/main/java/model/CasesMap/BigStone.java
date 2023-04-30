package model.CasesMap;

import consoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class BigStone extends SpritableEntity
{

    public BigStone(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        super(sprite3D, consoleSprite);
    }

    public BigStone(ConsoleSprite consoleSprite){
        this(new Sprite3D(), consoleSprite);
    }
}
