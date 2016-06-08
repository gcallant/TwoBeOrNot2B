package BuffsBoolean;

/**
 * Created by Michael on 5/23/2016.
 */
public class FearDebuff extends BooleanBuffList
{
    public FearDebuff(String name)
    {
        super(name);
    }

    public boolean isInEffect()
    {
        return size() > 0;
    }

    public String toString()
    {
        return " has been Feared";
    }

    public String description()
    {
        return "fear";
    }
}
