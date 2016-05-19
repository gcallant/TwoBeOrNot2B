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

	protected int getBase()
	{
		return this.base;
	}

	public String toString()
	{
		return super.toString() + "Chainmail(" + (getPower()) + ")" ;
	}
}