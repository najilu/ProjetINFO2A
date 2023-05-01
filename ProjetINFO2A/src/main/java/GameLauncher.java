import controller.RuntimeController;
import settings.Setting;
import view.GamePanel;

import java.util.Map;

public class GameLauncher
{

    public static void main(String[] args)
    {
        RuntimeController runtimeController = new RuntimeController(new GamePanel());

        RuntimeController.settings.add(new Setting("Force", 0, 100, 0));
        RuntimeController.settings.add(new Setting("Agilité", 0, 100, 0));
        RuntimeController.settings.add(new Setting("Précision", 0, 100, 0));
        RuntimeController.settings.add(new Setting("Chance", 0, 100, 0));
        RuntimeController.settings.add(new Setting("Santé", 0, 100, 0));

        RuntimeController.settings.add(new Setting("WallHack", false));
        RuntimeController.settings.add(new Setting("GodMod", false));
        runtimeController.run();
    }
}