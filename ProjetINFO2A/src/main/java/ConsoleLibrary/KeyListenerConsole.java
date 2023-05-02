package ConsoleLibrary;

import org.jline.terminal.Terminal;

import java.io.IOException;

public class KeyListenerConsole {
    Terminal terminal;

    public KeyListenerConsole(Terminal terminal) {
        this.terminal = terminal;
    }

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
