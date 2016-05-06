package Item;

public enum DamageType
{
	Piercing, Bludgeoning, Slashing;

	public String toString()
	{
		switch(this)
		{
			case Piercing:
				return "Piercing";
			case Bludgeoning:
				return "Bludgeoning";
			case Slashing:
				return "Slashing";
			default:
				return "N/A";
		}
	}
}