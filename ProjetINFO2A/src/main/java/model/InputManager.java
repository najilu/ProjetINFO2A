package model;

import controller.Controller;

public class InputManager {
    Controller controller;

    public InputManager(Controller controller) {
        this.controller = controller;
    }

    public void move(char input){
        controller.setYOld_Player();
        controller.setXOld_Player();
        switch (input){
            case 'z' -> {
                controller.setY_player(-1);
            }
            case 'q' -> {
                controller.setX_player(-1);
            }
            case 'd' -> {
                controller.setX_player(1);
            }
            case 's' -> {
                controller.setY_player(1);
            }
        }
    }
}
