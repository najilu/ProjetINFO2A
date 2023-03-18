package model;

public class Player {
    private int x;
    private int oldX;
    private int y;
    private int oldY;
    private int HP;
    private Sprite sprite;
    private int speed;

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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

    public Player(int x, int y, int HP, Sprite sprite, int speed) {
        this.x = x;
        this.oldY = y;
        this.oldX = x;
        this.y = y;
        this.HP = HP;
        this.sprite = sprite;
        this.speed = speed;
    }
}
