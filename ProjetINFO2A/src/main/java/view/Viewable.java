package view;

import controller.Controller;

public interface Viewable
{

    void update();

    void LaunchListener();

    void verif();

    void setController(Controller controller);

    void InitGamePanel();
}
