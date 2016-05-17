package Item;

public class Chainmail extends Armor
{
	private int base;

	public Chainmail(int power)
	{
		super(power);
		base = 6;
	}

	protected int getBase()
	{
		return this.base;
	}

	public String toString()
	{
		return super.toString() + "Chainmail";
	}
}