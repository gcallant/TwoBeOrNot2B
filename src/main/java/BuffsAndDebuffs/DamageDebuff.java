package BuffsAndDebuffs;

/**
 * Created by Michael on 5/22/2016.
 */
public class DamageDebuff extends BuffList
{
    public DamageDebuff(String name)
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
        return "Damage debuff";
    }
}