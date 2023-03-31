package view;

import controller.Controller;

public interface IViewable
{

    void update();

    char LaunchListener();

    void showHP();

    void setController(Controller controller);

    void InitGamePanel();

    void showWin();

    void showLose();
}
