package Item;

public class Sword extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Sword(int power)
	{
		super(power, "strength");
		this.damageType = DamageType.Slashing;
		this.weaponType = WeaponType.Medium;
		base = 2;
	}

	public String toString()
	{
		return super.toString() + "Sword(" + (getPower()) + ")";
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