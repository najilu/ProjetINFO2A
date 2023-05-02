package model.Movable;

import ConsoleLibrary.ConsoleSprite;
import model.CasesMap.TeleportationCase;
import model.Sprite3D;
import settings.Skills;

public class Player extends EntityMovable
{
    private int HP;
    private int stones;
    private boolean win;
    private boolean teleported;
    private boolean stoneLaunched;
    private Skills skills;

    private TeleportationCase currentTeleportation;

    public TeleportationCase getCurrentTeleportation(){
        return currentTeleportation;
    }

    public void setCurrentTeleportation(TeleportationCase value){
        currentTeleportation = value;
    }

    public boolean isStoneLaunched(){
        return stoneLaunched;
    }

    public void setStoneLaunched(boolean value){
        stoneLaunched = value;
    }

    public boolean isTeleported(){
        return teleported;
    }

    public void setTeleported(boolean value){
        teleported = value;
    }

    public boolean isWin(){
        return win;
    }

    public void setWin(boolean value){
        win = value;
    }
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }


    public int getStones()
    {
        return stones;
    }

    public void setStones(int stones)
    {
        this.stones = stones;
    }

    public Skills getSkills()
    {
        return skills;
    }

    public void setSkills(Skills skills)
    {
        this.skills = skills;
    }

    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, Sprite3D sprite3D , int speed, int stones, Skills skills) {
        super(sprite3D, consoleSprite, x, y, speed);
        this.HP = HP;
        this.stones = stones;
        setWin(false);
        this.skills = skills;
    }


    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, int speed, int stones, Skills skills) {
        this(x, y, HP, consoleSprite, new Sprite3D(), speed, stones, skills);
    }
}
