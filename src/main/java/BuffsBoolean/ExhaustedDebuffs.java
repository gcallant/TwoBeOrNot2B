package BuffsBoolean;

/**
 * Created by Michael on 5/22/2016.
 */
public class ExhaustedDebuffs extends BooleanBuffList
{
    public ExhaustedDebuffs(String name)
    {
        super(name);
    }

    public boolean isInEffect()
    {
        return size() > 0;
    }

    public String toString()
    {
        return " has been Exhausted";
    }

    public String description()
    {
        return "exhaustion";
    }
}