package BuffsPercentage;

/**
 * Created by Michael on 5/23/2016.
 */
public class BleedDebuff extends BuffList
{
    public BleedDebuff(String name)
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
        return " is bleeding!";
    }

    public String description()
    {
        return "bleeding";
    }
}