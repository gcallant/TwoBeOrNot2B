package Item;

public class Dagger extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Dagger(int power)
	{
		super(power, "dexterity");
		this.damageType = DamageType.Piercing;
		this.base = 1;
		this.weaponType = WeaponType.Light;
	}

	public String toString()
	{
		return super.toString() + "Dagger(" + (getPower()) + ")";
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