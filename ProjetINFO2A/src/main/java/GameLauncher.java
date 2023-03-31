import controller.Controller;
import view.GamePanel;

public class GameLauncher
{
    public static void main(String[] args)
    {
        Controller controller = new Controller(new GamePanel(), 25,25);
        controller.run();
    }
}