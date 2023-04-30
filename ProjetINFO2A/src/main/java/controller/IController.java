package controller;

public interface IController {
    public void nextStep();
    public void run();
    public void swapController(IController controller);
}
