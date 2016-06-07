package BuffsBoolean;

/**
 * Created by Michael on 5/22/2016.
 */
public class StunnedDebuff extends BooleanBuffList
{
    public StunnedDebuff(String name)
    {
        super(name);
    }

    public boolean isInEffect()
    {
        return size() > 0;
    }

    public String toString()
    {
        return " has been Stunned";
    }

    public String description()
    {
        return "stun";
    }
}