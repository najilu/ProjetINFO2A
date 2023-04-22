package view;

import consoleLibrary.ColoredChar;
import consoleLibrary.Draw;
import consoleLibrary.KeyListenerConsole;
import controller.RuntimeController;
import model.EntityMovable;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import java.io.IOException;

public class GamePanel implements IViewable
{
    private int rowMax;
    private int colMax;
    private final Terminal terminal;
    private RuntimeController runtimeController;
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
    public void update(EntityMovable entity) {
        if((entity.getOldX() != entity.getX() || entity.getOldY() != entity.getY()) && entity.getOldX() >= 0 && entity.getOldY() >= 0){
            draw.drawAt(entity.getOldX(), entity.getOldY(), runtimeController.getMap().getEntity(entity.getOldX(), entity.getOldY()).getConsoleSprite().getColoredChar());
            draw.drawAt(entity.getX(), entity.getY(), entity.getConsoleSprite().getColoredChar());
        }
        else if((entity.getOldX() != entity.getX() || entity.getOldY() != entity.getY())){
            draw.drawAt(entity.getX(), entity.getY(), entity.getConsoleSprite().getColoredChar());
        }
    }

    @Override
    public void replace(EntityMovable entity, ColoredChar coloredChar){
        draw.drawAt(entity.getX(), entity.getY(), coloredChar);
    }

    @Override
    public char LaunchListener() {
        return keyListener.listenInput();
    }

    @Override
    public void showHP() {
        draw.showMessage(this, "x :" + runtimeController.getPlayer().getX() + " y :" + runtimeController.getPlayer().getY() + " HP : " + runtimeController.getPlayer().getHP()
        + " stones : " + runtimeController.getPlayer().getStones())
        ;
    }

    @Override
    public void setController(RuntimeController runtimeController) {
        this.runtimeController = runtimeController;
    }

    @Override
    public void InitGamePanel() {
        rowMax = runtimeController.getMap().getRowMax();
        colMax = runtimeController.getMap().getColMax();
        terminal.puts(InfoCmp.Capability.cursor_invisible);
        draw.drawMap(this, runtimeController.getMap());
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
