package view;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

public class Draw {
    private final Terminal terminal;
    private int cursorX;
    private int cursorY;

    static final char BLOCK = '█';

    public Draw(Terminal terminal) {
        this.terminal = terminal;
    }

    public void drawAt(int x, int y, ColoredChar coloredChar){
        terminal.puts(InfoCmp.Capability.cursor_address, y,x);
        terminal.writer().print(coloredChar);
    }

    public void cleanAt(int x, int y){
        terminal.puts(InfoCmp.Capability.cursor_address, y,x);
        ColoredChar coloredChar = null;
        if ((y%2 == 0 && x%2 ==0) || (y%2 == 1 && x%2 ==1)) coloredChar = new ColoredChar(BLOCK, Color.WHITE);
        else{
            coloredChar = new ColoredChar(BLOCK, Color.BLACK);
        }
        terminal.writer().print(coloredChar);
    }

    public void drawBackground(GamePanel gamePanel){
        clear();
        ColoredChar whiteCase = new ColoredChar(BLOCK, Color.WHITE);
        ColoredChar blackCase = new ColoredChar(BLOCK, Color.BLACK);
        for (int i = 0; i<gamePanel.getRowMax(); i++){
            for(int j =0; j<gamePanel.getColMax();j++){
                if ((j%2 == 0 && i%2 ==0) || (j%2 == 1 && i%2 ==1)) terminal.writer().print(whiteCase);
                else{
                    terminal.writer().print(blackCase);
                }
            }
            terminal.writer().println();
        }
    }

    public static void clear(){
        System.out.println("\033\143");
    }
}
