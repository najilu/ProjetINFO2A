package controller;
import ConsoleLibrary.ColoredChar;
import ConsoleLibrary.ConsoleSprite;
import ConsoleLibrary.ConsoleSprites;
import model.*;
import model.Movable.Cursor;
import model.Movable.Player;
import settings.Setting;
import settings.Skills;
import view.IViewable;
import java.util.ArrayList;

/**
 * Controlleur principale, permettant d'orchestrer le jeu et ses différents états
 */
public class RuntimeController implements IController
{

    /**
     * Liste de settings en static permettant d'avoir accès aux paramètres partout dans les classes,
     * les settings étant utilisé dans des classes diverses
     */
    public static ArrayList<Setting> settings = new ArrayList<>();


    /**
     * GameState est l'état du jeu, explication des états possibles :
     * GameInitialisation : le début du jeux, permet d'initialiser les variables importantes
     * Update : état souvent appelée, qui actualise le visuel du joueur
     * PlayerAction : état qui permet d'attendre l'appuie du joueur sur une touche
     * Checking : état qui vérifie l'état globale du jeux
     * DommagePhase : état qui inflige au joueur les dégâts et vérifie la défaite
     * WinPhase, LosePhase : état qui signifie la fin du jeu et bloque le controller
     * TeleportationInProgress : état spécial qui permet de sauter d'autre état pour que le joueur puisse se téléporter
     * StoneLaunch : état qui permet de spécifier que le joueur va lancer la pierre, cela permet aussi de passer le contrôle au contrôleur de la pierre
     */
    public enum GameState{
        GameInitialisation ,Update, PlayerAction, Checking, DommagePhase, WinPhase, TeleportationInProgress, LosePhase,
        StoneLaunch;
    }

    //element du model pas encore pret

    /**
     * l'attribut player permet d'obtenir une référence au joueur contenue dans le model, afin que le reste du model puisse y accéder facilement
     */
    private Player player;

    /**
     * référence l'état du jeu actuel
     */
    private GameState gameState;

    /**
     * permet de savoir si le contrôleur possède actuellement le focus, ou si c'est un des autres contrôleurs qui le possède
     */
    private boolean haveFocus;

    /**
     * permet d'obtenir l'état du jeu
     * @return retourne une instance de GameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * attribut qui référence et dicte la view, en effet on créer ici un attribut de type IViewable afin de rendre l'intégration de view différente plus simple
     */
    private final IViewable view;


    /**
     * Cet attribut permet d'utiliser la classe inputManager qui va gérer les effets des différentes entrées utilisateur
     */
    private InputManager inputManager;

    /**
     * Attribut qui gère le plateau de jeu
     */
    private Field field;


    /**
     * Constructeur du contrôleur principal, il permet d'initaliser la view ainsi que le de donner son instance à la view.
     * @param view Instance de IViewable qui fixe qu'elle view a été choisie
     */
    public RuntimeController(IViewable view)
    {
        this.gameState = GameState.GameInitialisation;
        this.view = view;
        view.setController(this);
    }

    /**
     * méthode très simple qui permet de lancer le jeu
     */

    public void startGame(){
        run();
    }

    /**
     * fonction déjà décrite dans l'interface
     */
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

    /**
     * fonction déjà décrite dans l'interface
     */
    public void run()
    {
        switch (gameState){
            /*
             *Etape qui instancie les settings restant, qui affiche le menu grâce au controleur associée puis qui créer le joueur ainsi que la carte
             */
            case GameInitialisation -> {

                settings.add(new Setting("Nombre de ligne", 5, 25, 5));
                settings.add(new Setting("Nombre de colonne", 5, 25, 5));
                settings.add(new Setting("Nombre de pierre", 1, 25, 0));
                Setting[] settings1 = new Setting[]{settings.get(5), settings.get(6), settings.get(7), settings.get(8), settings.get(9)};
                swapController(new SubMenuController(view, this,settings1));

                player = new Player(0,0,3,new ConsoleSprite(new ColoredChar("\uD83E\uDDDD")), 1, settings.get(9).getIntValue(), new Skills());

                swapController(new SkillController(view, this, settings.get(0), settings.get(1), settings.get(2), settings.get(3), settings.get(4)));

                player.setHP(player.getSkills().getHp());

                field = new Field(settings.get(7).getIntValue(),settings.get(8).getIntValue(), player);
                inputManager = new InputManager(this, player);


                view.InitGamePanel();
                nextStep();
            }

            /*
            met a jour la vu du joueur, en prenant en compte la téléportation
             */
            case Update -> {
                view.update(player);
                if(player.isTeleported())  {
                    gameState = GameState.TeleportationInProgress;
                    inputManager.teleportePlayer();
                    player.setTeleported(false);
                }
                nextStep();
            }

            /*
            Recupere les entrées utilisateur et gère l'ouverture du menu ou bien du lancer de la pierre
             */
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

            /*
            permet de verifier ou est le joueur, et qu'elle effet cela doit il avoir
             */
            case Checking -> {
                GameEvaluator.CheckNewCase(this.player, this.field, this);
                if(player.isWin()) gameState = GameState.WinPhase;
                nextStep();
            }

            /*
            gestion des pertes d'hp du joueur et verification de la défaite
             */
            case DommagePhase -> {
                view.showInformation();
                if(player.getHP() == 0) gameState = GameState.LosePhase;
                nextStep();
            }

            /*
            affiche la victoir du joueur
             */
            case WinPhase -> {
                view.showWin();
            }

            /*
            affiche la défaite du joueur
             */
            case LosePhase -> {
                view.showLose();
            }

            /*
            permet la gestion du lancer de la pierre en passant le contrôle au contrôleur associée.
             */
            case StoneLaunch -> {
                swapController(new SubStoneController(this, new Cursor(ConsoleSprites.CURSORCASE.getValue(), player.getX(), player.getY(), 1), view));
                player.setStoneLaunched(false);
                player.setOpenMenu(false);
                nextStep();
            }
        }
    }

    /**
     * fonction déjà décrite dans l'interface
     */
    public void swapController(IController controller){
        controller.run();
    }
    //region setter/getters

    /**
     * permet de récupérer le joueur
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * permet de récupérer le terrain
     */
    public Field getMap(){
        return field;
    }

    /**
     * permet de récupérer l'information sur le focus
     */
    public boolean isHaveFocus(){
        return haveFocus;
    }


    /**
     * permet de changer la valeur de focus
     * @param value valeur de focus sans condition
     */
    public void setHaveFocus(boolean value){
        haveFocus = value;
    }

    /**
     * permet de récupérer la zone maximum où le joueur peut lancer, la fonction ce trouve ici pour éviter de devoir passer des références à d'autres fonctions
     * @return int qui est la valeur adaptée de l'attribut force dans skills
     */
    public int getRange(){
        return player.getSkills().getStrenght();
    }
    //endregion
}
