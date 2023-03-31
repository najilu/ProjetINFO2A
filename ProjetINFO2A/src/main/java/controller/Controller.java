package controller;

import model.*;
import consoleLibrary.Color;
import view.IViewable;

public class Controller
{
    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase, WinPhase, LosePhase
    }

    //element du model pas encore pret
    private Player player;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    private IViewable view;

    private InputManager inputManager;

    private Map map;


    public Controller(IViewable view, int rowMax, int colMax)
    {
        player = new Player(0,0,3,new ConsoleSprite(' ', '♜', Color.BLACK), 1);
        map = new Map(rowMax,colMax, player);
        this.gameState = GameState.GameInitialisation;
        this.view = view;
        view.setController(this);
        inputManager = new InputManager(this);
    }

    public void startGame(){
        run();
    }

    public void nextStep()
    {
        switch (gameState){
            case GameInitialisation, DommagePhase -> gameState = GameState.Update;
            case Update -> gameState= GameState.PlayerAction;
            case PlayerAction -> gameState= GameState.Checking;
            case Checking -> gameState= GameState.DommagePhase;
            case WinPhase, LosePhase -> run();
        }
        run();
    }

    public void run()
    {
        switch (gameState){
            case GameInitialisation -> {
                view.InitGamePanel();
                nextStep();
            }
            case Update -> {
                view.update();
                nextStep();
            }
            case PlayerAction -> {
                inputManager.move(view.LaunchListener());
                // s'occuper du lancer de pierre
                nextStep();
            }
            case Checking -> {
                GameEvaluator.CheckNewCase(this.player, this.map);
                if(player.isWin()) gameState = GameState.WinPhase;
                nextStep();
            }
            case DommagePhase -> {
                view.showHP();
                if(player.getHP() == 0) gameState = GameState.LosePhase;
                nextStep();
            }
            case WinPhase -> {
                view.showWin();
            }
            case LosePhase -> {
                view.showLose();
            }
        }
    }


    //region setter/getters

    public int getHP_player() {return player.getHP();}
    public int getX_player(){
        return player.getX();
    }
    public int getOldX_player(){
        return player.getOldX();
    }
    public int getY_player(){
        return player.getY();
    }

    public int getOldY_player(){
        return player.getOldY();
    }

    public void setX_player(int value){
        player.setX(player.getX() + value * player.getSpeed());
    }

    public void setXOld_Player(){
        player.setOldX(player.getX());
    }

    public void setY_player(int value){
        player.setY(player.getY() + value * player.getSpeed());
    }

    public void setYOld_Player(){
        player.setOldY(player.getY());
    }

    public ConsoleSprite getSprite(){
        return player.getConsoleSprite();
    }

    public Map getMap(){
        return map;
    }

    public int getRowMax(){
        return map.getRowMax();
    }

    public int getColMax(){
        return map.getColMax();
    }

    public int getPlayerSpeed(){
        return player.getSpeed();
    }

    //endregion

    //region test

    //endregion

}
