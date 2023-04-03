package view;

import consoleLibrary.Draw;
import consoleLibrary.KeyListenerConsole;
import controller.Controller;
import model.Player;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import java.io.IOException;

public class GamePanel implements IViewable
{
    private int rowMax;
    private int colMax;
    private final Terminal terminal;
    private Controller controller;
    private final Draw draw;
    private final KeyListenerConsole keyListener;

    public int getRowMax() {
        return rowMax;
    }

    public int getColMax() {
        return colMax;
    }

    public GamePanel(int rowMax, int colMax) {
        this.rowMax = rowMax;
        this.colMax = colMax;

        try {
            this.terminal = TerminalBuilder.terminal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        draw = new Draw(terminal);
        keyListener = new KeyListenerConsole(terminal);
    }

    public GamePanel(){
        this(0,0);
    }

    @Override
    public void update() {
        Player player = controller.getPlayer();
        if((player.getOldX() != player.getX() || player.getOldY() != player.getY()) && player.getOldX() >= 0 && player.getOldY() >= 0){
            draw.drawAt(player.getOldX(), player.getOldY(), controller.getMap().getEntity(player.getOldX(), player.getOldY()).getConsoleSprite().getColoredChar());
            draw.drawAt(player.getX(), player.getY(), player.getConsoleSprite().getColoredChar());
        }
        else if((player.getOldX() != player.getX() || player.getOldY() != player.getY())){
            draw.drawAt(player.getX(), player.getY(), player.getConsoleSprite().getColoredChar());
        }
    }

    @Override
    public char LaunchListener() {
        return keyListener.listenInput();
    }

    @Override
    public void showHP() {
        draw.showMessage(this, "x :" + controller.getPlayer().getX() + " y :" + controller.getPlayer().getY() + " HP : " + controller.getPlayer().getHP());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void InitGamePanel() {
        rowMax = controller.getMap().getRowMax();
        colMax = controller.getMap().getColMax();
        terminal.puts(InfoCmp.Capability.cursor_invisible);
        draw.drawMap(this, controller.getMap());
    }

    public void showWin(){
        draw.showASCII("""

                 __     __                    _            _____  _____  __          _______    _\s
                 \\ \\   / /                   (_)          / ____|/ ____| \\ \\        / /  __ \\  | |
                  \\ \\_/ /__  _   _  __      ___ _ __     | |  __| |  __   \\ \\  /\\  / /| |__) | | |
                   \\   / _ \\| | | | \\ \\ /\\ / / | '_ \\    | | |_ | | |_ |   \\ \\/  \\/ / |  ___/  | |
                    | | (_) | |_| |  \\ V  V /| | | | |_  | |__| | |__| |    \\  /\\  /  | |      |_|
                    |_|\\___/ \\__,_|   \\_/\\_/ |_|_| |_( )  \\_____|\\_____|     \\/  \\/   |_|      (_)
                                                     |/                                          \s
                                                                                                 \s
                """);
        terminal.puts(InfoCmp.Capability.cursor_invisible);
    }

    public void showLose(){
        draw.showASCII(
                """

                         __     __           _                    _ _   _                     _   _\s
                         \\ \\   / /          | |                  (_) | ( )                   | | | |
                          \\ \\_/ /__  _   _  | | ___  ___  ___     _| |_|/ ___   ___  __ _  __| | | |
                           \\   / _ \\| | | | | |/ _ \\/ __|/ _ \\   | | __| / __| / __|/ _` |/ _` | | |
                            | | (_) | |_| | | | (_) \\__ \\  __/_  | | |_  \\__ \\ \\__ \\ (_| | (_| | |_|
                            |_|\\___/ \\__,_| |_|\\___/|___/\\___( ) |_|\\__| |___/ |___/\\__,_|\\__,_| (_)
                                                             |/                                    \s
                                                                                                   \s
                        """
        );
        terminal.puts(InfoCmp.Capability.cursor_invisible);
    }


}
