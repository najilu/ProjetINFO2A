package view;

public class Paint
{
    private int x,y;
    private int sizeX, sizeY;
    private ColoredChar[][] ColoredChars;

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public ColoredChar[][] getColoredChars()
    {
        return ColoredChars;
    }

    public ColoredChar[] getColoredLineChars(int index){
        return ColoredChars[index];
    }

    public ColoredChar getColoredLineChars(int indexRow, int indexCol){
        return ColoredChars[indexRow][indexCol];
    }

    public Paint(int x, int y, int sizeX, ColoredChar[][] ColoredChars)
    {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = ColoredChars.length;
        this.ColoredChars = ColoredChars;
    }

    public Paint(int sizeX, int sizeY)
    {
        this.x = 0;
        this.y = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        ColoredChars = new ColoredChar[sizeY][sizeX];
    }

    public void modifyPaint(Paint paint){
        if (paint.sizeX>this.sizeX || paint.sizeY>this.sizeY) throw new IllegalArgumentException("La taille du paint est trop grande pour être modifier");
        for (int y = 0; y<this.sizeY; y++){
            ColoredChar[] coloredLineChar = new ColoredChar[this.sizeX];
            if(y >= paint.getY() && y < paint.getY()+paint.getSizeY()){
                for (int x = 0; x<this.sizeX; x++){
                    coloredLineChar[x] = ColoredChars[y][x];
                    if(x >= paint.getX() && x < paint.getX()+paint.getSizeX()){
                        coloredLineChar[x] = (paint.getColoredChars()[y-paint.y][x-paint.x]);
                    }
                }
                this.ColoredChars[y] = coloredLineChar;
            }
        }
    }
}
