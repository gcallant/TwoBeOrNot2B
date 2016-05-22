package Item;

public class Bow extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Bow(int power)
	{
		super(power, "dexterity");
		this.damageType = DamageType.Piercing;
		this.base = 2;
		this.weaponType = WeaponType.Ranged;
	}

	public String toString()
	{
		return super.toString() + "Bow(" + (getPower()) + ")";
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