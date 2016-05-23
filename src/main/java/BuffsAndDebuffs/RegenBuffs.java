package BuffsAndDebuffs;

import java.util.List;

/**
 * Created by Michael on 5/22/2016.
 */
public class RegenBuffs extends BuffList
{
    public RegenBuffs(String name)
    {
        super(name);
    }

    public double getAmount()
    {
        double total = 0;
        for (Buffs buffs : getList())
        {
            total += buffs.buffAmount();
        }
        return total;
    }

    public String toString()
    {
        return "Regen";
    }
}