import controller.RuntimeController;
import view.GamePanel;

public class GameLauncher
{
    public static void main(String[] args)
    {
        RuntimeController runtimeController = new RuntimeController(new GamePanel(), 25,25);
        runtimeController.run();
    }
}