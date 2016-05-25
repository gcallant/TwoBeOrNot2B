package BuffsAndDebuffs;

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
        return "Exhaustion";
    }
}