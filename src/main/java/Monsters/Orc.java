package Monsters;

import Characters.A_Monster;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.ConfusingStrike;
import SpecialAbilities.SpecialManager;
import SpecialAbilities.StunningStrike;
import com.google.common.base.Objects;

import java.util.Random;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Orc extends A_Monster
{
	private SpecialManager specialManager;
	private int level;

	public Orc(String name, int health, int power, int cunning, Armor armor, Weapon weapon, int level, int floor)
	{
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Light, weapon, 6, level, CreatureType.Humanoid);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new StunningStrike());

		if(floor >= 3)
		{
			specialManager.addSpecialAbility(new ConfusingStrike());
		}

		this.level = level;
	}

	public boolean specialAbility(Random rand, Party heroes, Party monsters)
	{
		specialManager.executeRandomAbility(this, monsters, heroes);
		return false;
	}

	public void levelUp()
	{
		upgradePower();
		upgradePower();
		upgradeHealth();
		upgradeHealth();
		upgradeHealth();
	}

	public int getLevel()
	{
		return level*8;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Orc)) { return false; }
		if(! super.equals(o)) { return false; }
		Orc orc = (Orc) o;
		return level == orc.level &&
				         Objects.equal(specialManager, orc.specialManager);
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(super.hashCode(), specialManager, level);
	}
}
