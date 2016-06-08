package BuffsPercentage;

/**
 * Created by Michael on 5/24/2016.
 */
public class BurnDebuff extends BuffList
{
    public BurnDebuff(String name)
    {
        super(name);
    }

    public double getAmount()
    {
        double total = 0;
        for (Buffs buffs : getList())
        {
            total += (buffs.buffAmount());
        }
        return total;
    }

    public String toString()
    {
        return " is on fire!";
    }

    public String description()
    {
        return "burning";
    }
}