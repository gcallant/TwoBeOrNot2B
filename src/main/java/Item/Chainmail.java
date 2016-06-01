package Item;

public class Chainmail extends Armor
{
	private int base;
	private ArmorType armorType;

	public Chainmail(int power)
	{
		super(power);
		base = 5;
		armorType = ArmorType.Heavy;
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
		return super.toString() + "Chainmail(" + (getPower()) + ")" ;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Chainmail)) { return false; }
		if(! super.equals(o)) { return false; }

		Chainmail chainmail = (Chainmail) o;

		if(base != chainmail.base) { return false; }
		if(armorType != chainmail.armorType) { return false; }

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