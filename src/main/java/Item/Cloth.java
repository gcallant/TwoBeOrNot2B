package Item;

/**
 * Created by Michael on 5/18/2016.
 */
public class Cloth extends Armor
{
    private int base;
    private ArmorType armorType;

    public Cloth(int power)
    {
        super(power);
        base = 2;
        armorType = ArmorType.Light;
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
        return super.toString() + "Cloth(" + (getPower()) + ")";
    }
}
