package Item;

public class Staff extends Weapon
{
	private final DamageType damageType;
	private int base;

	public Staff(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Bludgeoning;
		this.base = 0;
	}

	public String toString()
	{
		return super.toString() + "Staff";
	}

	public int getBase()
	{
		return this.base;
	}
}