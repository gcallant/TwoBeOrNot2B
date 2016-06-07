package BuffsPercentage;

/**
 * Created by Michael on 5/22/2016.
 */
public class PoisonDebuffs extends BuffList
{
    public PoisonDebuffs(String name)
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
        return " has been poisoned!";
    }

    public String description()
    {
        return "poison";
    }
}