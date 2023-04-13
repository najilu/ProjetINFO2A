package model;

public class Player extends Entity{
    private int x;
    private int oldX;
    private int y;
    private int oldY;
    private int HP;
    private int speed;
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

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public int getHP() {
        return HP;
    }

    protected void setHP(int HP) {
        this.HP = HP;
    }

    public int getSpeed() {
        return speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOldX() {
        return oldX;
    }

    protected void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    protected void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getStones()
    {
        return stones;
    }

    public void setStones(int stones)
    {
        this.stones = stones;
    }


    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, Sprite3D sprite3D , int speed) {
        super(sprite3D, consoleSprite);
        this.x = x;
        this.oldY = -1;
        this.oldX = -1;
        this.y = y;
        this.HP = HP;
        this.speed = speed;
    }


    public Player(int x, int y, int HP, ConsoleSprite consoleSprite, int speed) {
        super(new Sprite3D(), consoleSprite);
        this.x = x;
        this.oldY = -1;
        this.oldX = -1;
        this.y = y;
        this.HP = HP;
        this.speed = speed;
    }
}
