package settings;

public class IntSetting extends Setting
{
    private int value;
    private final int borneMin;
    private final int borneMax;

    public int getValue(){
        return value;
    }

    public IntSetting(int defaultValue, SettingsListName name, int borneMin, int borneMax){
        super(name);
        this.value = defaultValue;
        this.borneMin = borneMin;
        this.borneMax = borneMax;
    }


    @Override
    public void changeValue(boolean next)
    {
        int direction = (next) ? 1 : -1;
        value = Setting.clamp(borneMin, borneMax, value+direction);
    }

    @Override
    public String toString()
    {
        return ((focus) ? "➡" : "") + name + " : " + value;
    }
}
