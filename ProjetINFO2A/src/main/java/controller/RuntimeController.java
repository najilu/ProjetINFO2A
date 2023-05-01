package controller;
import consoleLibrary.ColoredChar;
import consoleLibrary.ConsoleSprite;
import consoleLibrary.ConsoleSprites;
import model.*;
import model.Movable.Cursor;
import model.Movable.Player;
import settings.Setting;
import view.IViewable;

import java.util.ArrayList;
import java.util.Arrays;

public class RuntimeController
{

    public static ArrayList<Setting> settings = new ArrayList<>();

    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase, WinPhase, TeleportationInProgress, LosePhase,
        StoneLaunch;
    }

    //element du model pas encore pret
    private Player player;
    private GameState gameState;
    private boolean haveFocus;
    public GameState getGameState() {
        return gameState;
    }
    private final IViewable view;
    private InputManager inputManager;
    private Field field;

    public RuntimeController(IViewable view)
    {


        //player = new Player(0,0,3,new ConsoleSprite(new ColoredChar("\uD83E\uDDDD")), 1, 3);
        //field = new Field(rowMax,colMax, player);
        this.gameState = GameState.GameInitialisation;
        this.view = view;
        view.setController(this);
        //inputManager = new InputManager(this, player);
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
                settings.add(new Setting("Nombre de ligne", 5, 25, 5));
                settings.add(new Setting("Nombre de colonne", 5, 25, 5));
                settings.add(new Setting("Nombre de pierre", 1, 0, 8));
                Setting[] settings1 = new Setting[settings.size()];
                swapController(new SubMenuController(view, this, settings.toArray(settings1)));

                player = new Player(0,0,3,new ConsoleSprite(new ColoredChar("\uD83E\uDDDD")), 1, settings.get(9).getIntValue());
                field = new Field(settings.get(7).getIntValue(),settings.get(8).getIntValue(), player);
                inputManager = new InputManager(this, player);


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
                if(player.isStoneLaunched()) {
                    gameState = GameState.StoneLaunch;
                    run();
                }
                if(player.isOpenMenu()){
                    swapController(new SubMenuController(view, this, settings.get(5), settings.get(6)));
                    view.InitGamePanel();
                    view.update(player);
                    player.setOpenMenu(false);
                    run();
                }
                else{
                    nextStep();
                }
            }
            case Checking -> {
                GameEvaluator.CheckNewCase(this.player, this.field, this);
                if(player.isWin()) gameState = GameState.WinPhase;
                nextStep();
            }
            case DommagePhase -> {
                view.showInformation();
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
                swapController(new SubStoneController(this, new Cursor(ConsoleSprites.CURSORCASE.getValue(), player.getX(), player.getY(), 1), view));
                player.setStoneLaunched(false);
                player.setOpenMenu(false);
                nextStep();
            }
        }
    }

    public void swapController(IController controller){
        controller.run();
    }
    //region setter/getters
    public Player getPlayer(){
        return player;
    }

    public Field getMap(){
        return field;
    }
    public boolean isHaveFocus(){
        return haveFocus;
    }

    public void setHaveFocus(boolean value){
        haveFocus = value;
    }
    //endregion

    //region afaire
    //ajouter le lancer des pierres fait
    //ajouter le poisson sampling fait
    //ajouter les menus contextuelles
    //region a faire

    // s'occuper dans le model et dans la view d'avoir une classe entité movable afin de pouvoir la déplacer sans trop ce faire chier
    // fait
    // une fois cela fait l'utiliser dans le controller prévu a cette effet subStoneController
    // fait
    // M'occuper de rework la générations de la map
    // fait
    // ensuite continuer en créant les controllers pour les menus (a voir comment faire)
    // Ajouter un fichir json qui gerera les paramètres de façon propre
    // Ajouter si j'ai le temps un système de loading de map
    // Ajouter des sons

    //endregion

}
