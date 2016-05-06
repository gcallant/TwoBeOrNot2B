package Item;

public class Staff extends Weapon
{
	private final DamageType damageType;

	public Staff(int power)
	{
		super(power);
		this.damageType = DamageType.Bludgeoning;
	}

	public String toString()
	{
		return super.toString() + "Staff";
	}
}