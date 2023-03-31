package consoleLibrary;

import model.*;
import org.fusesource.jansi.Ansi;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;
import view.GamePanel;

public class Draw {
    private final Terminal terminal;
    public static final char BLOCK = '█';
    public Draw(Terminal terminal) {
        this.terminal = terminal;
    }

    public void drawAt(int x, int y, ColoredChar coloredChar){
        terminal.puts(InfoCmp.Capability.cursor_address,0,0);
        terminal.writer().print(Ansi.ansi().bgGreen());
        terminal.puts(InfoCmp.Capability.cursor_address,y,x*2); //changer la couleur en fonction de la case
        terminal.writer().print(coloredChar);
        terminal.writer().print(Ansi.ansi().reset());
        terminal.puts(InfoCmp.Capability.cursor_invisible);
    }

    public void showMessage(GamePanel gamePanel, String message){
        terminal.puts(InfoCmp.Capability.cursor_address, gamePanel.getRowMax()+2, 0);
        terminal.writer().print("\033[2K\r");
        terminal.writer().print(Color.RED+message);
    }

    public void drawMap(GamePanel gamePanel, Map map){
        clear();
        terminal.puts(InfoCmp.Capability.cursor_address, 0, 0);
        for (int rowIndex = 0; rowIndex<gamePanel.getRowMax(); rowIndex++){
            for(int colIndex =0; colIndex<gamePanel.getColMax();colIndex++){
                terminal.writer().print(map.getEntity(colIndex, rowIndex).getConsoleSprite().getColoredChar());
            }
            terminal.writer().println();
        }
    }

    public void cleanAt(int x, int y){
        drawAt(x,y,new ColoredChar(BLOCK, Color.GREEN));
    }

    public void showASCII(String text){
        clear();
        terminal.puts(InfoCmp.Capability.cursor_address, 5,0);
        terminal.writer().print(text);
    }

    public static void clear(){
        System.out.println("\033\143");
    }
}
