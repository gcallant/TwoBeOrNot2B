package Item;

public class Hammer extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Hammer(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Bludgeoning;
		this.base = 3;
		this.weaponType = WeaponType.Heavy;
	}

	public String toString()
	{
		return super.toString() + "Hammer(" + (getPower()) + ")";
	}

	public WeaponType getWeaponType()
	{
		return this.weaponType;
	}

	public int getBase()
	{
		return this.base;
	}
}