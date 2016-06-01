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

    public int getBase()
    {
        return this.base;
    }

    public String toString()
    {
        return super.toString() + "Leather(" + (getPower()) + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) { return true; }
        if(! (o instanceof Leather)) { return false; }
        if(! super.equals(o)) { return false; }

        Leather leather = (Leather) o;

        if(base != leather.base) { return false; }
        if(armorType != leather.armorType) { return false; }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + base;
        result = 31 * result + (armorType != null ? armorType.hashCode() : 0);
        return result;
    }
}
