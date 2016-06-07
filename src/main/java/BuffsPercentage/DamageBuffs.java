package BuffsPercentage;

/**
 * Created by Michael on 5/22/2016.
 */
public class DamageBuffs extends BuffList
{
    public DamageBuffs(String name)
    {
        super(name);
    }

    public double getAmount()
    {
        double total = 0.0;
        for (Buffs buffs : getList())
        {
            total += buffs.buffAmount();
        }
        return total;
    }

    public String toString()
    {
        return " damage has been increased!";
    }

    public String description()
    {
        return "damage increase";
    }
}