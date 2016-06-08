package BuffsPercentage;

/**
 * Created by Michael on 5/22/2016.
 */
public class DamageReductionBuff extends BuffList
{
    public DamageReductionBuff(String name)
    {
        super(name);
    }

    public double getAmount()
    {
        double total = 1.0;
        for (Buffs buffs : getList())
        {
            total += buffs.buffAmount();
        }
        return Math.min(total,2.0);
    }

    public String toString()
    {
        return " is taking less damage!";
    }

    public String description()
    {
        return "damage reduction";
    }
}