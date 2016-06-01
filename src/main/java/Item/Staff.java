package Item;

public class Staff extends Weapon
{
	private final DamageType damageType;
	private int base;
	private WeaponType weaponType;

	public Staff(int power)
	{
		super(power, "Power");
		this.damageType = DamageType.Bludgeoning;
		this.weaponType = WeaponType.Staff;
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

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Staff)) { return false; }
		if(! super.equals(o)) { return false; }

		Staff staff = (Staff) o;

		if(base != staff.base) { return false; }
		if(damageType != staff.damageType) { return false; }
		if(weaponType != staff.weaponType) { return false; }

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