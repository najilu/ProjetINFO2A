package settings;

import java.util.Random;
import java.util.Scanner;
public class Skills {
    Scanner sc= new Scanner (System.in);
    private int competPoints = 100;
    private int accuracy;
    private int strenght;
    private int hp;
    private int trueHp;
    private int agility;
    private int luck;

    public Skills() {
        this.competPoints = 100;
        this.accuracy = 0;
        this.strenght = 0;
        this.hp = 0;
        this.agility = 0;
        this.luck = 0;
        this.trueHp=0;
    }
    public void assignPoints(){
        char c;
        do {
            if(this.competPoints>=10) {
            System.out.println("Assignez vos points de compétences: Vous avez");
            System.out.println("pressez  f pour +1 en Force (F pour +10)");
            System.out.println("pressez  a pour +1 en Agilité (A pour +10)");
            System.out.println("pressez  p pour +1 en Précision (P pour +10)");
            System.out.println("pressez  s pour +1 en Santé (S pour +10)");
            System.out.println("pressez  c pour +1 en Chance (C pour +10)");
            c = sc.next().charAt(0);
            switch(c){

                    case 'a': {
                        this.agility = this.agility + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'A': {
                        this.agility = this.agility + 10;
                        this.competPoints = this.competPoints - 10;
                        break;
                    }
                    case 'c': {
                        this.luck = this.luck + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'C': {
                        this.luck = this.luck + 10;
                        this.competPoints = this.competPoints - 10;
                        break;
                    }
                    case 'f': {
                        this.strenght = this.strenght + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'F': {
                        this.strenght = this.strenght + 10;
                        this.competPoints = this.competPoints - 10;
                        break;
                    }
                    case 'p': {
                        this.accuracy = this.accuracy + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'P': {
                        this.accuracy = this.accuracy + 10;
                        this.competPoints = this.competPoints - 10;
                        break;
                    }
                    case 's': {
                        this.hp = this.hp + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'S': {
                        this.hp = this.hp + 10;
                        this.competPoints = this.competPoints - 10;
                        break;
                    }
                }
            }
            else{
                System.out.println("Il vous reste moins de 10points à placer: plus de modifications par 10 !");
                c = sc.next().charAt(0);
                switch(c){
                    case 'a': {
                        this.agility = this.agility + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'c': {
                        this.luck = this.luck + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'f': {
                        this.strenght = this.strenght + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 'p': {
                        this.accuracy = this.accuracy + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                    case 's': {
                        this.hp = this.hp + 1;
                        this.competPoints = this.competPoints - 1;
                        break;
                    }
                }
            }
        }while (competPoints!=0);
    setTrueHp();
    }




    public int getAccuracy(){
        return this.accuracy;
    }
    public double getLuck(){
        return 0.02*this.luck;
    }
    public double getAgility(){
       return this.agility/10.0;
    }
    public int getStrenght(){
        if(this.strenght<=11){return 1;}
        else if(this.strenght<=22){return 2;}
        else if(this.strenght<=33){return 3;}
        else if(this.strenght<=44){return 4;}
        else if(this.strenght<=55){return 5;}
        else if(this.strenght<=66){return 6;}
        else if(this.strenght<=77){return 7;}
        else if(this.strenght<=88){return 8;}
        else if(this.strenght<=99){return 9;}
        else return 10;
    }
       private void setTrueHp(){
           Random rdm=new Random();
           int c= rdm.nextInt(0,100);
           if (hp<=35){this.trueHp=1;}
           else if (hp<=80){this.trueHp=2;}
           else {this.trueHp=3;}
           if(this.hp>=c){this.trueHp=this.trueHp+1;}
    }
    public int getHp(){
        return trueHp;
    }
}
