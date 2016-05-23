package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Necromancer extends A_Nemesis
{
	public Necromancer(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon);
	}

	public void specialAttack(Random rand, Party heroes, Party monsters)
	{
		summonSkeleton();
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		//take action procedure for nemesis still to be determined.
		return false;
	}

}
