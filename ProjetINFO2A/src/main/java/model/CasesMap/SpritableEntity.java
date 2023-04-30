package model.CasesMap;

import consoleLibrary.ColoredChar;
import consoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class SpritableEntity
{
    private final Sprite3D sprite3D;
    private final ConsoleSprite consoleSprite;
    protected boolean visible = false;
    public Sprite3D get3DSprite(){
        return sprite3D;
    }
    public ConsoleSprite getConsoleSprite(){
        if(visible)return consoleSprite;
        else return new ConsoleSprite(new ColoredChar("\uD83D\uDFE7")) ;
    }

    public ConsoleSprite getConsoleSprite(boolean wallHack){
        if(wallHack||visible)return consoleSprite;
        else return new ConsoleSprite(new ColoredChar("\uD83D\uDFE7")) ;
    }

    public SpritableEntity(Sprite3D sprite3D, ConsoleSprite consoleSprite)
    {
        this.sprite3D = sprite3D;
        this.consoleSprite = consoleSprite;
    }
    public void setVisible(boolean value){
        visible = value;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
