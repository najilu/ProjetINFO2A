package model;

import controller.RuntimeController;
import controller.SkillController;
import controller.SubMenuController;
import model.CasesMap.BigStone;
import model.Movable.Cursor;
import model.Movable.EntityMovable;
import model.Movable.Player;
import settings.Setting;

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
                if(entity instanceof Player player && player.getStones() > 0){
                    player.setStoneLaunched(true);
                    runtimeController.setHaveFocus(false);
                }
                else if(entity instanceof Cursor){
                    runtimeController.setHaveFocus(true);
                }
            }
            case 'l' -> {
                if(entity instanceof Cursor cursor){
                    cursor.setLocked(true);
                }
            }
            case 'm' -> {
                entity.setOpenMenu(true);
            }
        }
    }

    private boolean canMove(int newX, int newY){
        if(newX < runtimeController.getMap().getColMax() && newX >= 0 && newY < runtimeController.getMap().getRowMax() && newY >= 0){
            // ici 3 est la range il faudra l'adapter quand maxime aura fait le système de compétence
            if(entity instanceof Cursor)return (Math.abs(newX - runtimeController.getPlayer().getX()) <= runtimeController.getRange() && Math.abs(newY - runtimeController.getPlayer().getY()) <= runtimeController.getRange() );
            if(entity instanceof Player)
            {
                runtimeController.getMap().getEntity(newX, newY).setVisible(true);
                return (!(runtimeController.getMap().getEntity(newX, newY) instanceof BigStone) || (RuntimeController.settings.get(6).getBooleanValue()));
            }
        }
        return false;
    }

    public void teleportePlayer(){
        Player player = runtimeController.getPlayer();
        player.saveCoordonate(player.getCurrentTeleportation().getTarget()[0],player.getCurrentTeleportation().getTarget()[1]);
    }

    public void inputMenu(char key, SubMenuController controller){
        switch (key){
            case 'z' -> {
                controller.getSettings()[controller.getIndexOfFocus()].setFocus(false);
                controller.setIndexOfFocus(Setting.circulation(controller.getIndexOfFocus()-1, 0, controller.getSettings().length-1));
                controller.getSettings()[controller.getIndexOfFocus()].setFocus(true);
            }
            case 's' -> {
                controller.getSettings()[controller.getIndexOfFocus()].setFocus(false);
                controller.setIndexOfFocus(Setting.circulation(controller.getIndexOfFocus()+1, 0, controller.getSettings().length-1));
                controller.getSettings()[controller.getIndexOfFocus()].setFocus(true);
            }
            case 'q' -> {
                controller.getSettings()[controller.getIndexOfFocus()].changeValue(false);
            }
            case 'd' -> {
                controller.getSettings()[controller.getIndexOfFocus()].changeValue(true);
            }
            case 'm'->{
                controller.getSettings()[controller.getIndexOfFocus()].setFocus(false);
                controller.setWantCloseMenu(true);
            }
        }
    }

    public void inputSkills(char key, SkillController controller){
        if(controller.getSkillsPoints() < 10) key = Character.toLowerCase(key);
        if(key == 'A' || key == 'a' || key == 'F' || key=='f' || key=='R' || key=='r' || key=='P' || key=='p' || key=='C' || key=='c'){
            if(Character.isUpperCase(key)) controller.getSkills().setSkillsPoints(10);
            else{
                controller.getSkills().setSkillsPoints(1);
            }
        }
        switch (key){
            case 'A' -> {
                controller.getSkills().setAgility(10);
                controller.getSettings()[1].setValue(controller.getSettings()[1].getIntValue()+10+"");
            }
            case 'a' -> {
                controller.getSkills().setAgility(1);
                controller.getSettings()[1].setValue(controller.getSettings()[1].getIntValue()+1+"");
            }

            case 'F' -> {
                controller.getSkills().setStrenght(10);
                controller.getSettings()[0].setValue(controller.getSettings()[0].getIntValue()+10+"");
            }
            case 'f' -> {
                controller.getSkills().setStrenght(1);
                controller.getSettings()[0].setValue(controller.getSettings()[0].getIntValue()+1+"");
            }

            case 'R' -> {
                controller.getSkills().setHp(10);
                controller.getSettings()[4].setValue(controller.getSettings()[4].getIntValue()+10+"");
            }
            case 'r' -> {
                controller.getSkills().setHp(1);
                controller.getSettings()[4].setValue(controller.getSettings()[4].getIntValue()+1+"");
            }

            case 'P' -> {
                controller.getSkills().setAccuracy(10);
                controller.getSettings()[2].setValue(controller.getSettings()[2].getIntValue()+10+"");
            }
            case 'p' -> {
                controller.getSkills().setAccuracy(1);
                controller.getSettings()[2].setValue(controller.getSettings()[2].getIntValue()+1+"");
            }

            case 'C' -> {
                controller.getSkills().setLuck(10);
                controller.getSettings()[3].setValue(controller.getSettings()[3].getIntValue()+10+"");
            }
            case 'c' -> {
                controller.getSkills().setLuck(1);
                controller.getSettings()[3].setValue(controller.getSettings()[3].getIntValue()+1+"");
            }
        }
    }
}
