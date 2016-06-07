package Buffs;

import BuffsPercentage.BuffList;
import BuffsPercentage.Buffs;

/**
 * Created by Michael on 5/24/2016.
 */
public class RegenStaticBuff extends BuffList
{
    public RegenStaticBuff(String name)
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
        return "RegenStatic";
    }

    public String description()
    {
        return "regen";
    }
}
