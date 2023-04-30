package settings;

public class StringSetting extends Setting
{
    private String value;
    private int indexOfValue;
    private String[] arrayOfValue;

    public String getValue(){
        return value;
    }

    public int getIndexOfValue(){
        return indexOfValue;
    }

    public void setIndexOfValue(int value){
        indexOfValue=value;
    }

    public StringSetting(int defaultIndexOfValue, SettingsListName name, String... arrayOfValue){
        super(name);
        this.arrayOfValue = arrayOfValue;
        this.indexOfValue =defaultIndexOfValue;
        this.value = this.arrayOfValue[indexOfValue];
    }


    @Override
    public void changeValue(boolean next)
    {
        int direction = (next) ? 1 : -1;
        indexOfValue = Setting.clamp(0, arrayOfValue.length, indexOfValue+direction);
        value = arrayOfValue[indexOfValue];
    }

    @Override
    public String toString()
    {
        return ((focus) ? "➡" : "") + name + " : " + value;
    }
}
