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

    public boolean isWin(){
        return win;
    }

    public void setWin(boolean value){
        win = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
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
