package BuffsAndDebuffs;

/**
 * Created by Michael on 5/22/2016.
 */
public class ExhaustedBuffs extends BooleanBuffList
{
    public ExhaustedBuffs(String name)
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