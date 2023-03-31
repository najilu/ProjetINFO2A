package model;

public class NormalCase extends Entity
{
    private final boolean victoryCase;
    public boolean isVictoryCase(){
        return victoryCase;
    }

    public NormalCase(Sprite3D sprite3D, ConsoleSprite consoleSprite, boolean victoryCase){
        super(sprite3D, consoleSprite);
        this.victoryCase = victoryCase;
    }

    public NormalCase(ConsoleSprite consoleSprite, boolean victoryCase){
        this(new Sprite3D(), consoleSprite, victoryCase);
    }

    public NormalCase(ConsoleSprite consoleSprite){
        this(new Sprite3D(), consoleSprite, false);
    }

}
