package model.Movable;

import ConsoleLibrary.ConsoleSprite;
import model.Sprite3D;

public class Cursor extends EntityMovable
{

    private boolean locked = false;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Cursor(Sprite3D sprite3D, ConsoleSprite consoleSprite, int x, int y, int speed) {
        super(sprite3D, consoleSprite, x, y, speed);
    }

    public Cursor(ConsoleSprite consoleSprite, int x, int y, int speed) {
        super(consoleSprite, x, y, speed);
    }
}
