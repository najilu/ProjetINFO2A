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
                if(canMove(controller.getX_player(), controller.getY_player()-controller.getPlayerSpeed()))controller.setY_player(-1);
            }
            case 'q' -> {
                if(canMove(controller.getX_player()-controller.getPlayerSpeed(), controller.getY_player()))controller.setX_player(-1);
            }
            case 'd' -> {
                if(canMove(controller.getX_player()+controller.getPlayerSpeed(), controller.getY_player()))controller.setX_player(1);
            }
            case 's' -> {
                if(canMove(controller.getX_player(), controller.getY_player()+controller.getPlayerSpeed()))controller.setY_player(1);
            }
        }
    }

    public boolean canMove(int newX, int newY){
        if(newX < controller.getColMax() && newX >= 0 && newY < controller.getRowMax() && newY >= 0){
            return !(controller.getMap().getEntity(newX, newY) instanceof BigStone);
        }
        return false;
    }
}
