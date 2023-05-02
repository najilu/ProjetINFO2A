package settings;

import java.util.Random;
import java.util.Scanner;
public class Skills {
    private int competPoints;
    private int accuracy;
    private int strenght;
    private int hp;
    private int trueHp;
    private int agility;
    private int luck;

    public Skills() {
        this.competPoints = 200;
        this.accuracy = 0;
        this.strenght = 0;
        this.hp = 0;
        this.agility = 0;
        this.luck = 0;
        this.trueHp=0;
    }

    public void setSkillsPoints(int value){
        this.competPoints -= value;
    }

    public void setAccuracy(int accuracy)
    {
        this.accuracy += accuracy;
    }

    public void setStrenght(int strenght)
    {
        this.strenght += strenght;
    }

    public void setHp(int hp)
    {
        this.hp += hp;
    }

    public void setAgility(int agility)
    {
        this.agility += agility;
    }

    public void setLuck(int luck)
    {
        this.luck += luck;
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
    public void setTrueHp(){
       Random rdm=new Random();
       int c= rdm.nextInt(0,100);
       if (hp<=35){this.trueHp=1;}
       else if (hp<=80){this.trueHp=2;}
       else {this.trueHp=3;}
       if(this.hp>=c){this.trueHp=this.trueHp+1;}
    }
    public int getHp(){
        setTrueHp();
        return trueHp;
    }

    public int getSkillsPoint(){
        return competPoints;
    }
}
