package Item;

public class Sword extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Sword(int power)
	{
		super(power, "Power");
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

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Sword)) { return false; }
		if(! super.equals(o)) { return false; }

		Sword sword = (Sword) o;

		if(base != sword.base) { return false; }
		if(damageType != sword.damageType) { return false; }
		if(weaponType != sword.weaponType) { return false; }

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