package controller;

import model.InputManager;
import settings.Setting;
import settings.SettingsListName;
import view.IViewable;

public class SubMenuController implements IController
{
    private IViewable view;
    private boolean wantCloseMenu;
    public enum GameState{
        update, userInput, close
    }
    private GameState gameState;
    private RuntimeController runtimeController;
    private InputManager inputManager;
    private int indexOfFocus;
    private SettingsListName[] settingsListNames;

    public SubMenuController(IViewable view, RuntimeController runtimeController, SettingsListName... settings)
    {
        this.view = view;
        this.runtimeController = runtimeController;
        inputManager = new InputManager(runtimeController, runtimeController.getPlayer());
        this.settingsListNames = settings;
        indexOfFocus=0;
        gameState = GameState.update;
    }

    @Override
    public void nextStep()
    {
        switch (gameState){
            case update -> gameState = GameState.userInput;
            case userInput -> gameState = GameState.update;
        }
        run();
    }

    @Override
    public void run()
    {
        switch (gameState){
            case update -> {
                view.showMenu(RuntimeController.Param, settingsListNames);
                nextStep();
            }
            case userInput -> {
                inputManager.inputMenu(view.LaunchListener(), this);
                if(wantCloseMenu) gameState=GameState.close;
                nextStep();
            }
            case close -> {
            }
        }
    }


    public int getIndexOfFocus()
    {
        return indexOfFocus;
    }
    public void setIndexOfFocus(int value)
    {
        indexOfFocus=value;
    }
    public void setWantCloseMenu(boolean value){
        wantCloseMenu=value;
    }

    @Override
    public void swapController(IController controller)
    {

    }
}
