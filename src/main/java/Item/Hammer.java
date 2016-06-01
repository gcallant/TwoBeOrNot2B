package Item;

public class Hammer extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Hammer(int power)
	{
		super(power, "Power");
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

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Hammer)) { return false; }
		if(! super.equals(o)) { return false; }

		Hammer hammer = (Hammer) o;

		if(base != hammer.base) { return false; }
		if(damageType != hammer.damageType) { return false; }
		if(weaponType != hammer.weaponType) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = damageType != null ? damageType.hashCode() : 0;
		result = 31 * result + base;
		result = 31 * result + (weaponType != null ? weaponType.hashCode() : 0);
		return result;
	}
}