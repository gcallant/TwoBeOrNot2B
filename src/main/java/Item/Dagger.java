package Item;

public class Dagger extends Weapon
{
	private final DamageType damageType;
	private int base;

	public Dagger(int power)
	{
		super(power, "dexterity");
		this.damageType = DamageType.Piercing;
		this.base = 1;
	}

	public String toString()
	{
		return super.toString() + "Dagger";
	}

	public int getBase()
	{
		return this.base;
	}
}