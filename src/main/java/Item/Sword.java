package Item;

public class Sword extends Weapon
{
	private final DamageType damageType;
	private int base;

	public Sword(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Slashing;
		base = 2;
	}

	public String toString()
	{
		return super.toString() + "Sword";
	}

	public int getBase()
	{
		return this.base;
	}
}