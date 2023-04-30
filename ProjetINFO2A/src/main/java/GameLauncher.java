import controller.RuntimeController;
import settings.BooleanSetting;
import settings.Setting;
import settings.SettingsListName;
import view.GamePanel;

import java.util.Map;

public class GameLauncher
{

    public static void main(String[] args)
    {
        RuntimeController runtimeController = new RuntimeController(new GamePanel(), 25,25);

        RuntimeController.Param.put("WallHack",new BooleanSetting(false, SettingsListName.WallHack));
        RuntimeController.Param.put("GodMod",new BooleanSetting(false, SettingsListName.GodMod));
        runtimeController.run();
    }
}