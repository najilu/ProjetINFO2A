package model;

import java.awt.image.BufferedImage;

public class Sprite3D
{
    BufferedImage[] images;
    public Sprite3D()
    {
        images = null;
    }

    public Sprite3D(BufferedImage[] images){
        this.images = images;
    }
}
