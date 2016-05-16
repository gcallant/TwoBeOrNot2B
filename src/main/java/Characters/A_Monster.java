package Characters;

/**
 * Created by SaraPage on 4/29/2016.
 */
public abstract class A_Monster extends A_Character
{
	protected String name;

	public A_Monster(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
	{
		super(newName, newHealth, newStrength, newDexterity, newSpeed, newArmor);
	}

	public void takeAction(Party heroes, Party monsters)
	{
		String toPrint;
		toPrint = this.getName();
		toPrint = toPrint + " attacks ";
		toPrint = toPrint + heroes.getParty().get(0).getName();
		System.out.println(toPrint);
	}
}
