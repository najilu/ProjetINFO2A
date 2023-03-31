package model;

import java.io.Console;

public class Entity {
    private final Sprite3D sprite3D;
    private final ConsoleSprite consoleSprite;
    public Sprite3D get3DSprite(){
        return sprite3D;
    }
    public ConsoleSprite getConsoleSprite(){return consoleSprite;}

    public Entity(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        this.sprite3D = sprite3D;
        this.consoleSprite = consoleSprite;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
