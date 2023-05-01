package view;

import consoleLibrary.ColoredChar;
import controller.RuntimeController;
import model.Movable.EntityMovable;
import settings.Setting;

import java.util.ArrayList;
import java.util.Map;

public interface IViewable
{

    public void update(EntityMovable entity);

    public void replace(EntityMovable entity, ColoredChar coloredChar);

    public char LaunchListener();

    public void showInformation();

    public void setController(RuntimeController runtimeController);

    public void InitGamePanel();

    public void showWin();

    public void showLose();

    public void showMenu(Setting[] settings);
}
