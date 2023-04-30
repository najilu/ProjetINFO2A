package settings;

public enum SettingsListName
{
    WallHack("WallHack"),
    GodMod("GodMod");


    private final String value;
    SettingsListName(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
