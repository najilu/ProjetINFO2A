package model;

import controller.Controller;

public class InputManager {
    Controller controller;

    public InputManager(Controller controller) {
        this.controller = controller;
    }

    public void move(char input){
        Player player = controller.getPlayer();
        controller.getPlayer().setOldX(controller.getPlayer().getX());
        controller.getPlayer().setOldY(controller.getPlayer().getY());
        switch (input){
            case 'z' -> {
                if(canMove(player.getX(), player.getY()-player.getSpeed()))player.setY(player.getY()-1);
            }
            case 'q' -> {
                if(canMove(player.getX()-player.getSpeed(), player.getY()))player.setX(player.getX()-1);
            }
            case 'd' -> {
                if(canMove(player.getX()+player.getSpeed(), player.getY()))player.setX(player.getX()+1);
            }
            case 's' -> {
                if(canMove(player.getX(), player.getY()+player.getSpeed()))player.setY(player.getY()+1);
            }
        }
    }

    private boolean canMove(int newX, int newY){
        if(newX < controller.getMap().getColMax() && newX >= 0 && newY < controller.getMap().getRowMax() && newY >= 0){
            return !(controller.getMap().getEntity(newX, newY) instanceof BigStone);
        }
        return false;
    }

    public void teleportePlayer(){
        Player player = controller.getPlayer();
        player.setOldX(player.getX());
        player.setOldY(player.getY());
        player.setX(player.getCurrentTeleportation().getTarget()[0]);
        player.setY(player.getCurrentTeleportation().getTarget()[1]);
    }
}
