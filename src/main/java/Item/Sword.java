package Item;

public class Sword extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Sword(int power)
	{
		super(power, "power");
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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Sword sword = (Sword) o;

		if (base != sword.base) return false;
		if (damageType != sword.damageType) return false;
		if (weaponType != sword.weaponType) return false;

		return true;
	}
}