package Item;

public class Bow extends Weapon
{
	private final DamageType damageType;
	private int base;

	public Bow(int power)
	{
		super(power, "dexterity");
		this.damageType = DamageType.Piercing;
		this.base = 2;
	}

	public String toString()
	{
		return super.toString() + "Bow";
	}

	public int getBase()
	{
		return this.base;
	}
}