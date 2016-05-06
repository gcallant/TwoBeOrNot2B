package Item;

public abstract class Armor implements Storable, Equipable
{
	private final int         power;
	private       Enchantment enchantment;
	private       boolean     equipped;
	private       boolean     stored;

	public Armor(int power)
	{
		if(power > 3 || power < 0)
		{
			throw new IllegalArgumentException(power + " is an invalid value for potion power.");
		}

		this.power = power;
		this.enchantment = null;
		this.equipped = false;
		this.stored = false;
	}

	@Override
	public void store()
	{
		this.stored = true;
		this.equipped = false;
	}

	@Override
	public boolean isStored()
	{
		return this.stored;
	}

	@Override
	public void use()
	{
		this.equipped = true;
		this.stored = false;
	}

	@Override
	public int getPower()
	{
		return this.power;
	}

	@Override
	public void setEnchantment(Enchantment enchantment)
	{
		this.enchantment = enchantment;
	}

	@Override
	public String getParentType()
	{
		return "Armor";
	}

	public String toString()
	{
		return "Armor: ";
	}

	@Override
	public String getChildType()
	{
		return this.getClass() + "";
	}
}