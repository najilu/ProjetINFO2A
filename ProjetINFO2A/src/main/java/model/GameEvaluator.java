package model;

import ConsoleLibrary.ConsoleSprites;
import controller.RuntimeController;
import model.CasesMap.Bombe;
import model.CasesMap.NormalCase;
import model.CasesMap.StoneHeap;
import model.CasesMap.TeleportationCase;
import model.Movable.Cursor;
import model.Movable.Player;
import settings.Randomized;

import java.util.Random;

public class GameEvaluator
{
    static public void CheckNewCase(Player player, Field field, RuntimeController runtimeController){
        if(field.getEntity(player.getX(), player.getY()) instanceof Bombe){
            if(!RuntimeController.settings.get(6).getBooleanValue())
            {
                Random random = new Random();
                int pourcent = random.nextInt(0,101);
                if(pourcent>player.getSkills().getAgility()){
                    player.setHP(player.getHP()-1);
                }
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
        int[] newCase = Randomized.Randomize(cursor.getX(), cursor.getY(), RuntimeController.settings.get(2).getIntValue());
        // x puis y
        if(field.getEntity(newCase[0], newCase[1]) instanceof StoneHeap stoneHeap){
            stoneHeap.setStoneCount(stoneHeap.getStoneCount()+1);
        }
        else if(field.getEntity(newCase[0], newCase[1]) instanceof NormalCase normalCase && !normalCase.isVictoryCase()){
            field.setEntity(newCase[0], newCase[1], new StoneHeap(ConsoleSprites.HEAPOFSTONE.getValue(), 1));
        }
        else if(field.getEntity(newCase[0], newCase[1]) instanceof Bombe bombe){
            field.setEntity(newCase[0], newCase[1], new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
        }

        field.getEntity(newCase[0], newCase[1]).setVisible(true); //regarder pourquoi parfois la pierre ne rend pas la case visible
    }
}
