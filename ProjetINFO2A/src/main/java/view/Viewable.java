package view;

import controller.Controller;

public interface Viewable
{

    void update();

    char LaunchListener();

    void verif();

    void setController(Controller controller);

    void InitGamePanel();
}
