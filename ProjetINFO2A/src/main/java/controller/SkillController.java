package controller;

import model.InputManager;
import settings.Setting;
import settings.Skills;
import view.IViewable;


/**
 * Controlleur qui permet de gérer l'affichage du menu skills
 */
public class SkillController implements IController
{
    private IViewable view;
    public enum GameState{
        update, userInput, close
    }
    private GameState gameState;
    private RuntimeController runtimeController;
    private InputManager inputManager;


    // tableau de paramètre permettant de connaître exactement qu'elle paramètre afficher
    private Setting[] settings;

    public SkillController(IViewable view, RuntimeController runtimeController, Setting... settings)
    {
        this.view = view;
        this.runtimeController = runtimeController;
        inputManager = new InputManager(runtimeController, runtimeController.getPlayer());
        gameState = GameState.update;
        this.settings = settings;
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
                // fonction permettant d'afficher les skills
                view.showSkills(settings, runtimeController.getPlayer().getSkills().getSkillsPoint());
                nextStep();
            }
            case userInput -> {
                // permet de gerer les inputs dépendants des skills
                inputManager.inputSkills(view.LaunchListener(), this);
                //si le joueur ne possède pu de points de skills alors le menu ce ferme automatiquement
                if(runtimeController.getPlayer().getSkills().getSkillsPoint() == 0) gameState= GameState.close;
                nextStep();
            }
            case close -> {
            }
        }
    }

    public Setting[] getSettings(){
        return settings;
    }

    public int getSkillsPoints(){
        return runtimeController.getPlayer().getSkills().getSkillsPoint();
    }

    public Skills getSkills(){
        return runtimeController.getPlayer().getSkills();
    }
    @Override
    public void swapController(IController controller)
    {

    }
}
