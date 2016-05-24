package BuffsAndDebuffs;

/**
 * Created by Michael on 5/24/2016.
 */
public class ConfusionDebuff extends BooleanBuffList
{
    public ConfusionDebuff(String name)
    {
        super(name);
    }

    public boolean isInEffect()
    {
        return size() > 0;
    }

    public String toString()
    {
        return "Confused";
    }
}