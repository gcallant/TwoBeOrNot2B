package Item;

public class Hammer extends Weapon
{
	private final DamageType damageType;

	public Hammer(int power)
	{
		super(power);
		this.damageType = DamageType.Bludgeoning;
	}

	public String toString()
	{
		return super.toString() + "Hammer";
	}
}