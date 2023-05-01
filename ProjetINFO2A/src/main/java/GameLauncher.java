import controller.RuntimeController;
import settings.Setting;
import view.GamePanel;

import java.util.Map;

public class GameLauncher
{

    public static void main(String[] args)
    {
        RuntimeController runtimeController = new RuntimeController(new GamePanel(), 25,25);
        RuntimeController.settings.add(new Setting("WallHack", false));
        RuntimeController.settings.add(new Setting("GodMod", false));
        runtimeController.run();
    }
}