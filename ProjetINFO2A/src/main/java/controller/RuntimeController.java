package controller;
import consoleLibrary.ColoredChar;
import consoleLibrary.ConsoleSprite;
import consoleLibrary.ConsoleSprites;
import model.*;
import model.Movable.Cursor;
import model.Movable.Player;
import settings.Setting;
import settings.SettingsListName;
import view.IViewable;

import java.util.ArrayList;

public class RuntimeController
{

    public static ArrayList<Setting> settings = new ArrayList<>();
    public static int indexOfFocus = 0;

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
    private final Field field;

    public RuntimeController(IViewable view, int rowMax, int colMax)
    {
        // le nombre de stone (ici 3) est determiner par la difficultée système incorporer par maxime normallement
        player = new Player(0,0,3,new ConsoleSprite(new ColoredChar("\uD83E\uDDDD")), 1, 3);
        field = new Field(rowMax,colMax, player);
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
                swapController(new SubMenuController(view, this, settings));
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
                    swapController(new SubMenuController(view, this, SettingsListName.WallHack, SettingsListName.GodMod));
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
