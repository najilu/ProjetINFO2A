import settings.Setting;

public class Singleton
{
    public Setting[] paramStart;
    public Setting[] paramInGame;
    private Singleton()
    {

    }

    /* Instance unique pré-initialisée */
    private static final Singleton INSTANCE = new Singleton();

    /** Point d'accès pour l'instance unique du singleton */
    public static Singleton getInstance()
    {   return INSTANCE;
    }
}
