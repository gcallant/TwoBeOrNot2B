package BuffsPercentage;

/**
 * Created by Michael on 5/22/2016.
 */
public class AttackDebuffs extends BuffList
{
    public AttackDebuffs(String name)
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
        return " attack has been decreased!";
    }

    public String description()
    {
        return "attack decrease";
    }
}