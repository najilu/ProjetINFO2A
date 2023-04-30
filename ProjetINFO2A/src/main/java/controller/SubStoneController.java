package controller;

import model.Movable.Cursor;
import model.GameEvaluator;
import model.InputManager;
import settings.SettingsListName;
import view.IViewable;

public class SubStoneController implements IController{
    public enum GameState{
        Update, UserChoice, Randomisation, Hit, End
    }

    private GameState gameState = GameState.Update;
    private RuntimeController runtimeController;
    private Cursor cursor;
    private IViewable view;
    private InputManager inputManager;

    public SubStoneController(RuntimeController runtimeController, Cursor cursor, IViewable view){
        this.runtimeController = runtimeController;
        this.cursor = cursor;
        this.view = view;
        inputManager = new InputManager(runtimeController, cursor);
    }

    public void nextStep()
    {
        switch (gameState){
            case Update -> {
                gameState = GameState.UserChoice;
            }
            case UserChoice -> {
                gameState = GameState.Update;
            }
            case Randomisation->{
                gameState = GameState.Hit;
            }
            case Hit->{
                gameState = GameState.End;
            }
        }
        run();
    }

    public void run(){
        switch (gameState){
            case Update -> {
                if(!runtimeController.isHaveFocus()){
                    view.update(cursor);
                    if(cursor.getOldX() == runtimeController.getPlayer().getX() && cursor.getOldY() == runtimeController.getPlayer().getY()){
                        view.update(runtimeController.getPlayer());
                    }
                    nextStep();
                }
                else{
                    view.replace(cursor, runtimeController.getMap().getEntity(cursor.getX(), cursor.getY()).getConsoleSprite().getColoredChar());
                }
            }
            case UserChoice -> {
                if(!cursor.isLocked()){
                    inputManager.inputRead(view.LaunchListener());
                    if(cursor.isOpenMenu()){
                        swapController(new SubMenuController(view, runtimeController, SettingsListName.WallHack, SettingsListName.GodMod));
                    }
                    nextStep();
                }
                else{
                    gameState=GameState.Randomisation;
                    run();
                }
            }
            case Randomisation -> {
                nextStep();//maxime qui devrait l'inserer
            }
            case Hit -> {
                runtimeController.getPlayer().setStones(runtimeController.getPlayer().getStones() -1);
                view.showInformation();
                GameEvaluator.CheckHit(cursor, runtimeController.getMap());
                view.replace(cursor, runtimeController.getMap().getEntity(cursor.getX(), cursor.getY()).getConsoleSprite().getColoredChar());
                runtimeController.setHaveFocus(true);
                nextStep();
            }
            case End -> {

            }
        }
    }

    @Override
    public void swapController(IController controller){
        controller.run();
    }
}
