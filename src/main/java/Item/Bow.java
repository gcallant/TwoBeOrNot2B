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
		this.weaponType = WeaponType.Light;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Bow bow = (Bow) o;

		if (base != bow.base) return false;
		if (damageType != bow.damageType) return false;
		if (weaponType != bow.weaponType) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = damageType.hashCode();
		result = 31 * result + base;
		result = 31 * result + weaponType.hashCode();
		return result;
	}
}