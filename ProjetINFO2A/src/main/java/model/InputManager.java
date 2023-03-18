package model;

import controller.Controller;

public class InputManager {
    Controller controller;

    public InputManager(Controller controller) {
        this.controller = controller;
    }

    public void move(char input){
        switch (input){
            case 'z' -> {
                controller.setYOld_Player();
                controller.setY_player(-1);
            }
            case 'q' -> {
                controller.setXOld_Player();
                controller.setX_player(-1);
            }
            case 'd' -> {
                controller.setXOld_Player();
                controller.setX_player(1);
            }
            case 's' -> {
                controller.setYOld_Player();
                controller.setY_player(1);
            }
        }
    }
}
