package Item;

public class Dagger extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Dagger(int power)
	{
		super(power, "cunning");
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
		if(this == o) { return true; }
		if(! (o instanceof Dagger)) { return false; }
		if(! super.equals(o)) { return false; }

		Dagger dagger = (Dagger) o;

		if(base != dagger.base) { return false; }
		if(damageType != dagger.damageType) { return false; }
		if(weaponType != dagger.weaponType) { return false; }

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