package Item;

public class Staff extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Staff(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Bludgeoning;
		this.weaponType = WeaponType.Light;
		this.base = 0;
	}

	public String toString()
	{
		return super.toString() + "Staff(" + (getPower()) + ")";
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