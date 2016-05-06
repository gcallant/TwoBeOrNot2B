package Item;

public abstract class Consumable implements Storable
{
	private final int     power;
	private       boolean stored;

	protected Consumable(int power)
	{
		if(power > 5 || power < 0)
		{
			throw new IllegalArgumentException(power + " is an invalid value for potion power.");
		}

		this.power = power;
		this.stored = false;
	}

	@Override
	public void use()
	{
		this.stored = false;
	}

	@Override
	public void store()
	{
		this.stored = true;
	}

	@Override
	public boolean isStored()
	{
		return this.stored;
	}

	@Override
	public int getPower()
	{
		return this.power;
	}

	@Override
	public String getParentType()
	{
		return "Consumable";
	}

	@Override
	public String getChildType()
	{
		return this.getClass() + "";
	}

	public String toString()
	{
		return "Potion of ";
	}
}