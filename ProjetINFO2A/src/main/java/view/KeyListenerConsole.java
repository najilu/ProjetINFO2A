package view;

import org.jline.terminal.Terminal;

import java.io.IOException;

public class KeyListenerConsole {
    Terminal terminal;

    public KeyListenerConsole(Terminal terminal) {
        this.terminal = terminal;
    }

    //faire un nouveau thread

    public char listenInput(){
        terminal.enterRawMode();
        var reader = terminal.reader();
        char c = 0;
        try
        {
            c = (char) reader.read();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return c;
    }
}
