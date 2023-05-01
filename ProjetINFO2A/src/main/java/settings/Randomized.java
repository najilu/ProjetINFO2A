package settings;

import model.Field;

import java.util.Random;

public class Randomized {
    public static int[] Randomize(int x, int y, int accuracy){
        Random prng = new Random();
        int a = 0;
        int b = 0;
        int c = prng.nextInt(0,101);
        if(accuracy<=c) {
             if(accuracy<20){
                a = prng.nextInt(-2,3);
                b =prng.nextInt(-2,3);
            }
            else if (accuracy<35&&accuracy>=21){
                a = prng.nextInt(-1,2);
                b =prng.nextInt(-2,3);
            }
            else if(accuracy<60&&accuracy>=36){
                a = prng.nextInt(-1,2);
                b =prng.nextInt(-1,2);
            }
            else if (accuracy<100&&accuracy>=61){
                a = 0;
                b =prng.nextInt(-1,2);
            }
        }
        x=x+a;
        y=y+b;
        return new int[] {x,y};}
        }

