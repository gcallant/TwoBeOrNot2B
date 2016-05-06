package Item;

public class Bow extends Weapon
{
	private final DamageType damageType;

	public Bow(int power)
	{
		super(power);
		this.damageType = DamageType.Piercing;
	}

	public String toString()
	{
		return super.toString() + "Bow";
	}
}