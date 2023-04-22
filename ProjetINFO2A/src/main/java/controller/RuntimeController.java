package controller;

import consoleLibrary.ConsoleSprites;
import model.*;
import consoleLibrary.Color;
import view.IViewable;

public class RuntimeController
{


    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase, WinPhase, TeleportationInProgress, LosePhase,
        StoneLaunch;
    }

    //element du model pas encore pret
    private final Player player;
    private GameState gameState;

    private boolean haveFocus;
    public GameState getGameState() {
        return gameState;
    }

    private final IViewable view;

    private final InputManager inputManager;

    private final Map map;


    public RuntimeController(IViewable view, int rowMax, int colMax)
    {
        // le nombre de stone (ici 3) est determiner par la difficultée système incorporer par maxime normallement
        player = new Player(0,0,3,new ConsoleSprite(' ', '♜', Color.BLACK), 1, 3);
        map = new Map(rowMax,colMax, player);
        this.gameState = GameState.GameInitialisation;
        this.view = view;
        view.setController(this);
        inputManager = new InputManager(this, player);
    }

    public void startGame(){
        run();
    }

    public void nextStep()
    {
        switch (gameState){
            case GameInitialisation, DommagePhase, TeleportationInProgress, StoneLaunch -> gameState = GameState.Update;
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
                view.update(player);
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
                if(player.isStoneLaunched()) {
                    gameState = GameState.StoneLaunch;
                    run();//go continuer ca demain
                }
                else{
                    nextStep();
                }
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
            case StoneLaunch -> {
                SwapController(new SubStoneController(this, new Cursor(ConsoleSprites.CURSORCASE.getValue(), player.getX(), player.getY(), 1), view));
                player.setStoneLaunched(false);
                nextStep();
            }
        }
    }

    public void SwapController(IController controller){
        controller.run();
    }


    //region setter/getters
    public Player getPlayer(){
        return player;
    }

    public Map getMap(){
        return map;
    }
    public boolean isHaveFocus(){
        return haveFocus;
    }

    public void setHaveFocus(boolean value){
        haveFocus = value;
    }

    //endregion

    //region a faire

    // s'occuper dans le model et dans la view d'avoir une classe entité movable afin de pouvoir la déplacer sans trop ce faire chier
    // fait
    // une fois cela fait l'utiliser dans le controller prévu a cette effet subStoneController
    // fait
    // M'occuper de rework la générations de la map
    // pas fait a commencer !!!!
    // ensuite continuer en créant les controllers pour les menus (a voir comment faire)
    // Ajouter un fichir json qui gerera les paramètres de façon propre
    // Ajouter si j'ai le temps un système de loading de map
    // Ajouter des sons

    //endregion

}
