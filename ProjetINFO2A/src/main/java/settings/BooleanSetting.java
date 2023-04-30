package settings;

public class BooleanSetting extends Setting
{
    boolean value;

    public boolean getValue(){
        return value;
    }

    public BooleanSetting(boolean defaultValue, SettingsListName name){
        super(name);
        this.value = defaultValue;
    }


    @Override
    public void changeValue(boolean next)
    {
        value = !value;
    }

    @Override
    public String toString()
    {
        return ((focus) ? "➡" : "") + name + " : " + value;
    }
}
