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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cloth cloth = (Cloth) o;

        if (base != cloth.base) return false;
        if (armorType != cloth.armorType) return false;

        return true;
    }
}
