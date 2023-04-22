package model;

public class EntityMovable extends Entity {
    private int oldX;
    private int oldY;
    private int x;
    private int y;

    private int speed;

    public EntityMovable(Sprite3D sprite3D, ConsoleSprite consoleSprite, int x, int y, int speed) {
        super(sprite3D, consoleSprite);
        oldX = -1;
        oldY = -1;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public EntityMovable(ConsoleSprite consoleSprite, int x, int y, int speed) {
        super(new Sprite3D(),consoleSprite);
        oldX = -1;
        oldY = -1;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getOldX() {
        return oldX;
    }

    public int getSpeed() {
        return speed;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
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

    public void saveCoordonate(int x, int y){
        oldY = this.y;
        oldX = this.x;
        this.x = x;
        this.y = y;
    }
}
