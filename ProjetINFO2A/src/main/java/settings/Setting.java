package settings;
public abstract class Setting
{
    protected SettingsListName name;
    protected boolean focus;

    public void setFocus(boolean focus)
    {
        this.focus = focus;
    }

    public String getName(){
        return name.toString();
    }

    public Setting(SettingsListName name){
        this.name = name;
        focus = false;
    }

    public abstract void changeValue(boolean next);

    public static int clamp(int borneMin, int borneMax, int value){
        if(value>=borneMax){
            return borneMin;
        }
        else if(value < borneMin){
            return borneMax-1;
        }
        else{
            return value;
        }
    }
}
