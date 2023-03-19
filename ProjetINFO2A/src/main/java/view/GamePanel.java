package view;

import controller.Controller;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import java.io.IOException;

public class GamePanel implements Viewable {
    private final int rowMax;
    private final int colMax;
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

    @Override
    public void update() {
        draw.cleanAt(controller.getOldX_player(), controller.getOldY_player());
        draw.drawAt(controller.getX_player(), controller.getY_player(), controller.getSprite().getColoredChar());
    }

    @Override
    public char LaunchListener() {
        return keyListener.listenInput();
    }

    @Override
    public void verif() {
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void InitGamePanel() {
        terminal.puts(InfoCmp.Capability.cursor_invisible);
        draw.drawBackground(this);
    }
}
