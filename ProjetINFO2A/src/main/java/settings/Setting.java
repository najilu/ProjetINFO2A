package settings;

public class Setting {
    private boolean focus;
    private final boolean valuable;
    private final boolean binary;
    private String name;

    private int borneMax;
    private int borneMin;
    private int indexOfValue;
    private String[] arrayOfValue;

    private String value;


    public Setting(String name,int value, int borneMax, int borneMin){
        valuable = true;
        binary = false;
        focus = false;

        this.name = name;
        this.borneMax = borneMax;
        this.borneMin = borneMin;
        this.value = value + "";
    }

    public Setting(String name, boolean value){
        valuable = false;
        binary = true;
        focus = false;

        this.name= name;
        this.value = value + "";
    }

    public Setting(String name, int indexOfValueDefault, String... values){
        valuable = false;
        binary = false;
        focus = false;

        this.name= name;
        this.indexOfValue = indexOfValueDefault;
        this.arrayOfValue = values;
        this.value = arrayOfValue[indexOfValue];
    }

    public int getIntValue(){
        if(valuable){
            return Integer.parseInt(value);
        }
        else{
            throw new RuntimeException("this parameter is not an integer value");
        }
    }

    public boolean getBooleanValue(){
        if(binary){
            return Boolean.parseBoolean(value);
        }
        else {
            throw  new RuntimeException("this parameter is not a boolean");
        }
    }

    public String getValue(){
        return value;
    }

    public void changeValue(boolean next){
        int direction = (next) ? 1 : -1;
        if(valuable){
            value = circulation(getIntValue()+direction, borneMin, borneMax) + "";
        }
        else if(binary){
            value = !getBooleanValue()+"";
        }
        else{
            indexOfValue = circulation(indexOfValue+direction, 0, arrayOfValue.length-1);
            value = arrayOfValue[indexOfValue];
        }
    }

    public void setFocus(boolean value){
        focus = value;
    }

    @Override
    public String toString() {
        return ((focus) ? "➡" : "") + name + " : " + value;
    }


    public static int circulation(int value, int borneMin, int borneMax){
        if(value > borneMax){
            return borneMin;
        }
        else if(value < borneMin){
            return borneMax;
        }
        else {
            return value;
        }
    }
}
