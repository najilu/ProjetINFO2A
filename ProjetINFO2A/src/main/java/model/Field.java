package model;

import PoissonSampling.Point;
import PoissonSampling.PoissonAlgorithm;
import ConsoleLibrary.ConsoleSprites;
import model.CasesMap.*;
import model.Movable.Player;

import java.util.Objects;
import java.util.Random;

public class Field
{

    SpritableEntity[][] mapSpritableEntity;
    int rowMax;
    int colMax;

    public int getRowMax(){
        return rowMax;
    }

    public int getColMax(){
        return colMax;
    }

    public Field(int rowMax, int colMax, Player player){
        this.rowMax = rowMax;
        this.colMax = colMax;
        mapSpritableEntity = new SpritableEntity[rowMax][colMax];
        generateMapRandom(player);
    }
    private void generateMapRandom(Player player){
        Random random = new Random();
        int rowCaseWinnable = random.nextInt(-1, rowMax-1);
        Point[][] pointsMap = new Point[getColMax()][getRowMax()];
        PoissonAlgorithm poissonAlgorithm = new PoissonAlgorithm(getColMax(), getRowMax(), pointsMap);
        poissonAlgorithm.generatePoints('b', 3.0+player.getSkills().getLuck());
        poissonAlgorithm.generatePoints('t', 8.0);
        poissonAlgorithm.generatePoints('o', 2.5);
        poissonAlgorithm.generatePoints('s', 3.0);

        for(int rowIndex = 0; rowIndex < mapSpritableEntity.length; rowIndex++){
            for(int colIndex = 0; colIndex < mapSpritableEntity[rowIndex].length; colIndex++){
                if(rowIndex == player.getY() && colIndex == player.getX()){
                    mapSpritableEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.NORMALCASE.getValue());
                    mapSpritableEntity[rowIndex][colIndex].setVisible(true);
                }
                else{
                    if(Objects.isNull(pointsMap[colIndex][rowIndex] )){
                        mapSpritableEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.NORMALCASE.getValue());
                    }
                    else if(pointsMap[colIndex][rowIndex].type() == 'o'){
                        mapSpritableEntity[rowIndex][colIndex] = new BigStone(ConsoleSprites.BIGSTONECASE.getValue());
                    }
                    else if(pointsMap[colIndex][rowIndex].type() == 'b'){
                        mapSpritableEntity[rowIndex][colIndex] = new Bombe(ConsoleSprites.BOMBECASE.getValue());
                    }
                    else if(pointsMap[colIndex][rowIndex].type() == 't'){
                        int[] coordonates= getRandomNormalCase(mapSpritableEntity);
                        mapSpritableEntity[rowIndex][colIndex] = new TeleportationCase(coordonates[0], coordonates[1], ConsoleSprites.TELEPORTATIONCASE.getValue());
                    }
                    else if(pointsMap[colIndex][rowIndex].type() == 's'){
                        mapSpritableEntity[rowIndex][colIndex] = new StoneHeap(ConsoleSprites.HEAPOFSTONE.getValue(), random.nextInt(0, 5));
                    }
                    //reste un else avec les tas de pierres
                }

                //génération de la case de victoire
                if(colIndex == colMax-1 && rowIndex == rowCaseWinnable){
                    mapSpritableEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.VICTORYCASE.getValue(), true);
                }
            }
        }
    }

    private int[] getRandomNormalCase(SpritableEntity[][] spritableEntityVector){
        Random random = new Random();
        while (true){
            int y = random.nextInt(0, spritableEntityVector.length);
            int x = random.nextInt(0, spritableEntityVector[y].length);
            SpritableEntity caseChosen = spritableEntityVector[y][x];
            if(caseChosen instanceof NormalCase && !((NormalCase) caseChosen).isVictoryCase()){
                return new int[]{x,y};
            }
        }
    }

    public SpritableEntity getEntity(int colIndex, int rowIndex) {
        return mapSpritableEntity[rowIndex][colIndex];
    }

    public void setEntity(int colIndex, int rowIndex, SpritableEntity value){
        mapSpritableEntity[rowIndex][colIndex] = value;
    }

}
