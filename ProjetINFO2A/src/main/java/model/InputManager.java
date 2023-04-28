package model;

import controller.RuntimeController;

public class InputManager {
    private final RuntimeController runtimeController;
    private final EntityMovable entity;

    public InputManager(RuntimeController runtimeController, EntityMovable entity) {
        this.runtimeController = runtimeController;
        this.entity = entity;
    }

    public void inputRead(char input){
        switch (input){
            case 'z' -> {
                if(canMove(entity.getX(), entity.getY()-entity.getSpeed()))
                    entity.saveCoordonate(entity.getX(), entity.getY()-1);
            }
            case 'q' -> {
                if(canMove(entity.getX()-entity.getSpeed(), entity.getY()))
                    entity.saveCoordonate(entity.getX()-1, entity.getY());
            }
            case 'd' -> {
                if(canMove(entity.getX()+entity.getSpeed(), entity.getY()))
                    entity.saveCoordonate(entity.getX()+1, entity.getY());
            }
            case 's' -> {
                if(canMove(entity.getX(), entity.getY()+entity.getSpeed()))
                    entity.saveCoordonate(entity.getX(), entity.getY()+1);
            }
            case 'a' -> {
                if(entity instanceof Player player){
                    player.setStoneLaunched(true);
                    runtimeController.setHaveFocus(false);
                }
                else if(entity instanceof Cursor){
                    runtimeController.setHaveFocus(true);
                }
            }
            case '5' -> { // regarder comment recuperer l'input entrée
                if(entity instanceof Cursor cursor){
                    cursor.setLocked(true);
                }
            }
        }
    }

    private boolean canMove(int newX, int newY){
        if(newX < runtimeController.getMap().getColMax() && newX >= 0 && newY < runtimeController.getMap().getRowMax() && newY >= 0){
            // ici 3 est la range il faudra l'adapter quand maxime aura fait le système de compétence
            if(entity instanceof Cursor)return (Math.abs(newX - runtimeController.getPlayer().getX()) <= 3 && Math.abs(newY - runtimeController.getPlayer().getY()) <= 3 );
            if(entity instanceof Player)return !(runtimeController.getMap().getEntity(newX, newY) instanceof BigStone);
        }
        return false;
    }

    public void teleportePlayer(){
        Player player = runtimeController.getPlayer();
        player.saveCoordonate(player.getCurrentTeleportation().getTarget()[0],player.getCurrentTeleportation().getTarget()[1]);
    }
}
