package Nemesis;

import BuffsAndDebuffs.NecromancerConditions;
import Characters.A_Character;
import Characters.A_Nemesis;
import Characters.CreatureType;
import Factories.MonsterFactory;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import Mediator.Mediator;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.SummonSkeleton;

import java.util.List;
import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Necromancer extends A_Nemesis
{
	SpecialManager specialManager;

	public Necromancer(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon, int level)
	{
		super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon, level, CreatureType.Undead);
		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new SummonSkeleton());
		reassignConditons(new NecromancerConditions("Necromancer"));
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		specialManager.executeRandomAbility(this, monsters, heroes);
		return true;
	}

	public void startRage(Random rand, Party heroes, Party monsters)
	{
		cooldown = 1;
		System.out.println("\n\nTHE NECROMANCER IS SUMMONING THE DARK ARTS\n\n");
		specialAbility(rand, heroes, monsters);
		specialAbility(rand, heroes, monsters);
		specialAbility(rand, heroes, monsters);
	}

	public void levelUp()
	{
		upgradeHealth();
		upgradeHealth();
		upgradeHealth();
		upgradeHealth();
		upgradeStrength();
		upgradeStrength();
		upgradeDexterity();
		upgradeDexterity();
	}
}
