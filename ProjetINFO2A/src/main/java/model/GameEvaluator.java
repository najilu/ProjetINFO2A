package model;

import consoleLibrary.ConsoleSprites;
import controller.RuntimeController;

public class GameEvaluator
{
    static public void CheckNewCase(Player player, Map map){
        if(map.getEntity(player.getX(), player.getY()) instanceof Bombe){
            player.setHP(player.getHP()-1);
            map.setEntity(player.getX(), player.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
        }
        if(map.getEntity(player.getX(), player.getY()) instanceof TeleportationCase tpCase){
            player.setTeleported(true);
            player.setCurrentTeleportation(tpCase);
        }
        if(map.getEntity(player.getX(), player.getY()) instanceof StoneHeap stoneHeap){
            player.setStones(player.getStones() + stoneHeap.getStoneCount());
            map.setEntity(player.getX(), player.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
        }
        CheckWin(player, map);
    }

    private static void CheckWin(Player player, Map map){
        if(map.getEntity(player.getX(), player.getY()) instanceof NormalCase)
        {
            boolean isVictoryCase = ((NormalCase) map.getEntity(player.getX(), player.getY())).isVictoryCase();
            if (isVictoryCase)
            {
                player.setWin(true);
            }
        }
    }

    static public void CheckHit(Cursor cursor, Map map){
        if(map.getEntity(cursor.getX(), cursor.getY()) instanceof StoneHeap stoneHeap){
            stoneHeap.setStoneCount(stoneHeap.getStoneCount()+1);
        }
        else if(map.getEntity(cursor.getX(), cursor.getY()) instanceof NormalCase normalCase && !normalCase.isVictoryCase()){
            map.setEntity(cursor.getX(), cursor.getY(), new StoneHeap(ConsoleSprites.HEAPOFSTONE.getValue(), 1));
        }
        else if(map.getEntity(cursor.getX(), cursor.getY()) instanceof Bombe bombe){
            map.setEntity(cursor.getX(), cursor.getY(), new NormalCase(ConsoleSprites.NORMALCASE.getValue()));
        }
    }
}
