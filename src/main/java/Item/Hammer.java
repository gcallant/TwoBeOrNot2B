package Item;

public class Hammer extends Weapon
{
	private final DamageType damageType;
	private int base;

	public Hammer(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Bludgeoning;
		this.base = 3;
	}

	public String toString()
	{
		return super.toString() + "Hammer";
	}

	public int getBase()
	{
		return this.base;
	}
}