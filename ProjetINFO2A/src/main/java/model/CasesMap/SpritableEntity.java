package model.CasesMap;

import ConsoleLibrary.ColoredChar;
import ConsoleLibrary.ConsoleSprite;
import controller.RuntimeController;
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
        if(RuntimeController.settings.get(5).getBooleanValue() ||visible)return consoleSprite;
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

}
