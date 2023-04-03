package model;

import consoleLibrary.ConsoleSprites;

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
            //map.setEntity(tpCase.getTarget()[0], tpCase.getTarget()[1], player); // c'est de la merde le player est pas censé être sur la map faut que je patch ca
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
}
