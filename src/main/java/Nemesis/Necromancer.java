package Nemesis;

import BuffsAndDebuffs.UndeadConditions;
import Characters.A_Character;
import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.SummonSkeleton;

import java.util.Random;

/**
 * Created by Greig on 5/19/2016.
 */
public class Necromancer extends A_Nemesis
{
	SpecialManager specialManager;

	public Necromancer(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level)
	{
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon, level, CreatureType.Humanoid);
		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new SummonSkeleton());
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		specialManager.executeRandomAbility(this, monsters, heroes);
		return true;
	}

	public boolean takeAction(Party heroes, Party monsters)
	{
		boolean noTurn, noSpecial, useSpecial;

		if(!rage && !rageUsed)
		{
			rage = getHealth() < (getMaxHealth() / 4);
			if(rage)
			{
				startRage(rand, heroes, monsters);
				rageUsed = true;
				rageCount = 5;
				endTurn();
				return false;
			}
		}
		noTurn = conditions.cannotAttack();
		noSpecial = conditions.cannotUseSpecial();

		if(conditions.confusedEffect(this, heroes, monsters))
		{
			noTurn = true;
		}

		resetTurn();

		if(noTurn && !rage)
		{
			endTurn();
			return false;
		}

		if(noSpecial && !rage)
		{
			useSpecial = false;
		}

		heroes.sortDefeated();
		monsters.sortDefeated();

		int choiceToAttack = rand.nextInt(heroes.size());

		if(getHealth() < getMaxHealth()/2 && potions.size() > 0)
		{
			if(rand.nextBoolean())
			{
				potions.get(0).use(this);
				potions.remove(0);
			}
		}
		if((cooldown == 0 && monsters.size() < 6) || (cooldown == 0 && rage))
		{
			specialAbility(rand, heroes, monsters);
			if(!rage || rageCount == 0)
			{
				cooldown = 2;
			}
			else
			{
				rageCount--;
				cooldown = 1;
			}
		}
		else
		{
			A_Character toAttack = heroes.getCharacter(choiceToAttack);
			attack(toAttack);
		}
		endTurn();
		return false;
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
		upgradepower();
		upgradepower();
		upgradecunning();
		upgradecunning();
	}
}
