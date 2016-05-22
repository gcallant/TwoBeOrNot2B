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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Dagger dagger = (Dagger) o;

		if (base != dagger.base) return false;
		if (damageType != dagger.damageType) return false;
		if (weaponType != dagger.weaponType) return false;

		return true;
	}
}