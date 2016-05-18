package Item;

/**
 * Created by Michael on 5/18/2016.
 */
public class Leather extends Armor
{
    private int base;
    private ArmorType armorType;

    public Leather(int power)
    {
        super(power);
        base = 3;
        armorType = ArmorType.Medium;
    }

    public ArmorType getArmorType()
    {
        return this.armorType;
    }

    protected int getBase()
    {
        return this.base;
    }

    public String toString()
    {
        return super.toString() + "Leather(" + (getPower()) + ")";
    }
}
