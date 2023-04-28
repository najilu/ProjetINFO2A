package view;

import consoleLibrary.ColoredChar;
import controller.RuntimeController;
import model.EntityMovable;

public interface IViewable
{

    public void update(EntityMovable entity);

    public void replace(EntityMovable entity, ColoredChar coloredChar);

    public char LaunchListener();

    public void showHP();

    public void setController(RuntimeController runtimeController);

    public void InitGamePanel();

    public void showWin();

    public void showLose();
}
