package controller;

/**
 * Icontroller permet de posséder un niveau d'abstraction supplémentaire pour les controllers, cela permet d'avoir plusieurs controller
 * communiquent entre eux
 */
public interface IController {


    /**
     * nextStep() permet de changer l'état, appelée gameState dans les controleurs afin de pouvoir séquencer le code
     */
    public void nextStep();

    /**
     * run() permet d'exécuter du code en fonction du gameState
     */
    public void run();

    /**
     * cette fonction permet de facilité le changement de controller
     * @param controller ce paramètre permet d'obtenir une référence au controleur qui va obtenir le focus
     */
    public void swapController(IController controller);
}
