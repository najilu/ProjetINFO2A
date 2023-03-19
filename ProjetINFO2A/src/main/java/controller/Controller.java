package controller;

import model.Player;
import model.Sprite;
import view.Color;
import view.Viewable;

public class Controller
{
    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase
    }

    //element du model pas encore pret
    private Player player;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    private Viewable view;

    public Controller(Viewable view)
    {
        player = new Player(10,10,15,new Sprite('P', Color.CYAN), 1);
        this.gameState = GameState.GameInitialisation;
        this.view = view;
        view.setController(this);
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
        }
        run();
    }

    public void run()
    {
        switch (gameState){
            case GameInitialisation -> view.InitGamePanel(); //modifier quelque appels a la logique
            case Update -> view.update();
            case PlayerAction -> view.LaunchListener();
            case Checking -> view.verif();
            case DommagePhase -> {
                player.setHP(player.getHP()-1);
                nextStep();
            }
        }
    }

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

    public Sprite getSprite(){
        return player.getSprite();
    }


}
