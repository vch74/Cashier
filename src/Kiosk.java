/**
 * Created by andrew on 3/6/14.
 */
public class Kiosk
{
    private String code;
    private String name;

    public Kiosk(String code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public Kiosk(String code)
    {
        this.code = code;
        this.name = "Киоск 1";
    }

    public String retCode()
    {
        return code;
    }

    public String retName()
    {
        return name;
    }

}
