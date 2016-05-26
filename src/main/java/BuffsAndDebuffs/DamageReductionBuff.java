package BuffsAndDebuffs;

import java.util.List;

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
        return "Damage Reduction";
    }
}