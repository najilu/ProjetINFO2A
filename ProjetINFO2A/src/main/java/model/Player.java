package model;

public class Player extends EntityMovable{
    private int HP;
    private int stones;
    private boolean win;
    private boolean teleported;
    private boolean stoneLaunched;

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

    protected void setWin(boolean value){
        win = value;
    }
    public int getHP() {
        return HP;
    }

    protected void setHP(int HP) {
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


    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, Sprite3D sprite3D , int speed, int stones) {
        super(sprite3D, consoleSprite, x, y, speed);
        this.HP = HP;
        this.stones = stones;
        setWin(false);
    }


    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, int speed, int stones) {
        this(x, y, HP, consoleSprite, new Sprite3D(), speed, stones);
    }
}
