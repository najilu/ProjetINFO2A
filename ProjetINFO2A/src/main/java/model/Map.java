package model;

import consoleLibrary.ConsoleSprites;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;

import java.util.Arrays;
import java.util.Random;

public class Map {

    Entity[][] mapEntity;
    int rowMax;
    int colMax;

    public int getRowMax(){
        return rowMax;
    }

    public int getColMax(){
        return colMax;
    }

    public Map(int rowMax, int colMax, Player player){
        this.rowMax = rowMax;
        this.colMax = colMax;
        mapEntity = new Entity[rowMax][colMax];
        generateMapRandom(player);
    }

    private void generateMapRandom(Player player){
        Random random = new Random();
        int seed= random.nextInt(2000,10000);
        int rowCaseWinnable = random.nextInt(-1, rowMax-1);
        /*JNoise perlinNoise =  JNoise.newBuilder().perlin(seed, Interpolation.LINEAR, FadeFunction.IMPROVED_PERLIN_NOISE)
                .scale(1 / 16.0)
                .addModifier(v -> (v + 1) / 2.0)
                .clamp(0.0, 1.0)
                .build();
        */
        JNoise perlinNoise = JNoise.newBuilder().fastSimplex(FastSimplexNoiseGenerator.newBuilder().setSeed(seed).build())
                .scale(1 / 16.0)
                .addModifier(v -> (v + 1) / 2.0)
                .build();
        // ca ma l'air bien pour de la génération de terrain mais pas pour ce genre de jeux

        for(int rowIndex = 0; rowIndex < mapEntity.length; rowIndex++){
            for(int colIndex = 0; colIndex < mapEntity[rowIndex].length; colIndex++){
                if(rowIndex == player.getY() && colIndex == player.getX()){
                    mapEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.NORMALCASE.getValue());
                }
                else{
                    double noise = perlinNoise.evaluateNoise(colIndex, rowIndex);
                    if((noise > 0.5 && noise < 0.51)||(noise >0.3 && noise < 0.33)){
                        mapEntity[rowIndex][colIndex] = new BigStone(ConsoleSprites.BIGSTONECASE.getValue());
                    }
                    else if((noise > 0.6 && noise < 0.65 ) || (noise > 0.4 && noise < 0.5)){
                        mapEntity[rowIndex][colIndex] = new Bombe(ConsoleSprites.BOMBECASE.getValue());
                    }
                    else if(noise>0.35 && noise < 0.36){
                        int[] coordonates= getRandomNormalCase(mapEntity);
                        mapEntity[rowIndex][colIndex] = new TeleportationCase(coordonates[0], coordonates[1], ConsoleSprites.TELEPORTATIONCASE.getValue());
                    }
                    else if(noise > 0.90 && noise <0.90){ // a redesign ya un problème
                        // 5 etant le maximum a cause de la chance (compétence design par maxime)
                        mapEntity[rowIndex][colIndex] = new StoneHeap(ConsoleSprites.HEAPOFSTONE.getValue(), random.nextInt(0,5));
                    }
                    else{
                        mapEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.NORMALCASE.getValue());
                    }
                    //reste un else avec les tas de pierres
                }

                //génération de la case de victoire
                if(colIndex == colMax-1 && rowIndex == rowCaseWinnable){
                    mapEntity[rowIndex][colIndex] = new NormalCase(ConsoleSprites.VICTORYCASE.getValue(), true);
                }
            }
        }
    }

    private int[] getRandomNormalCase(Entity[][] entityVector){
        boolean isFine = false;
        Random random = new Random();
        while (!isFine){
            int y = random.nextInt(0, entityVector.length);
            int x = random.nextInt(0, entityVector[y].length);
            Entity caseChosen = entityVector[y][x];
            if(caseChosen instanceof NormalCase && !((NormalCase) caseChosen).isVictoryCase()){
                return new int[]{x,y};
            }
        }
        return new int[0];
    }

    public Entity getEntity(int colIndex, int rowIndex) {
        return mapEntity[rowIndex][colIndex];
    }

    public void setEntity(int colIndex, int rowIndex, Entity value){
        mapEntity[rowIndex][colIndex] = value;
    }

}
