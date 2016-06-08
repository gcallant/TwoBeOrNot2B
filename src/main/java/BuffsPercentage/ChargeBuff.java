package BuffsPercentage;

/**
 * Created by Michael on 5/28/2016.
 */
public class ChargeBuff extends BuffList
{
    public ChargeBuff(String name)
    {
        super(name);
    }

    public double getAmount()
    {
        double total = 0.0;
        for (Buffs buffs : getList())
        {
            total += (buffs.buffAmount());
        }
        return size() > 0 ? total : 1.0;
    }

    public String toString()
    {
        return " is charging up an attack!";
    }

    public String description()
    {
        return "charge";
    }
}
