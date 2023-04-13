package controller;

import model.*;
import consoleLibrary.Color;
import view.IViewable;

public class Controller
{
    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase, WinPhase, TeleportationInProgress, LosePhase,
        StoneLaunch;
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
            case GameInitialisation, DommagePhase, TeleportationInProgress -> gameState = GameState.Update;
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
                if(player.isTeleported())  {
                    gameState = GameState.TeleportationInProgress;
                    inputManager.teleportePlayer();
                    player.setTeleported(false);
                }
                nextStep();
            }
            case PlayerAction -> {
                inputManager.inputRead(view.LaunchListener());
                // s'occuper du lancer de pierre
                if(player.isStoneLaunched()) gameState = GameState.StoneLaunch;//go continuer ca demain
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
    public Player getPlayer(){
        return player;
    }

    public Map getMap(){
        return map;
    }

    //endregion

    //region afaire
    //ajouter le lancer des pierres
    //ajouter le poisson sampling
    //ajouter les menus contextuelles
    //endregion

}
