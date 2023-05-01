package model;

import consoleLibrary.ConsoleSprites;
import controller.RuntimeController;
import model.CasesMap.Bombe;
import model.CasesMap.NormalCase;
import model.CasesMap.StoneHeap;
import model.CasesMap.TeleportationCase;
import model.Movable.Cursor;
import model.Movable.Player;
import java.util.Objects;

public class GameEvaluator
{
    static public void CheckNewCase(Player player, Field field, RuntimeController runtimeController){
        if(field.getEntity(player.getX(), player.getY()) instanceof Bombe){
            if(!RuntimeController.settings.get(6).getBooleanValue())
            {
                player.setHP(player.getHP()-1);
                field.setEntity(player.getX(), player.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
                //field.getEntity(player.getX(), player.getY()).setVisible(true);
            }

        }
        if(field.getEntity(player.getX(), player.getY()) instanceof TeleportationCase tpCase){
            player.setTeleported(true);
            player.setCurrentTeleportation(tpCase);
            field.getEntity(tpCase.getTarget()[0], tpCase.getTarget()[1]).setVisible(true);
        }
        if(field.getEntity(player.getX(), player.getY()) instanceof StoneHeap stoneHeap){
            player.setStones(player.getStones() + stoneHeap.getStoneCount());
            field.setEntity(player.getX(), player.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
            //field.getEntity(player.getX(), player.getY()).setVisible(true);
        }
        field.getEntity(player.getX(), player.getY()).setVisible(true);
        CheckWin(player, field);
    }

    private static void CheckWin(Player player, Field field){
        if(field.getEntity(player.getX(), player.getY()) instanceof NormalCase)
        {
            boolean isVictoryCase = ((NormalCase) field.getEntity(player.getX(), player.getY())).isVictoryCase();
            if (isVictoryCase)
            {
                player.setWin(true);
            }
        }
    }

    static public void CheckHit(Cursor cursor, Field field){
        if(field.getEntity(cursor.getX(), cursor.getY()) instanceof StoneHeap stoneHeap){
            stoneHeap.setStoneCount(stoneHeap.getStoneCount()+1);
        }
        else if(field.getEntity(cursor.getX(), cursor.getY()) instanceof NormalCase normalCase && !normalCase.isVictoryCase()){
            field.setEntity(cursor.getX(), cursor.getY(), new StoneHeap(ConsoleSprites.HEAPOFSTONE.getValue(), 1));
        }
        else if(field.getEntity(cursor.getX(), cursor.getY()) instanceof Bombe bombe){
            field.setEntity(cursor.getX(), cursor.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
        }

        field.getEntity(cursor.getX(), cursor.getY()).setVisible(true);
    }
}
