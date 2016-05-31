package Heroes;

import Characters.A_Hero;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;
import SpecialAbilities.MeteorShower;
import SpecialAbilities.OwlsInsight;
import SpecialAbilities.SpecialManager;
import com.google.common.base.Objects;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class Mage extends A_Hero
{
	private SpecialManager specialManager;
	public Mage(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
	{
		super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon);

		specialManager = new SpecialManager();
		specialManager.addSpecialAbility(new OwlsInsight());
		specialManager.addSpecialAbility(new MeteorShower());
	}

	public static String Information()
	{
		return "Mage:\n" + OwlsInsight.description() + "\n" + MeteorShower.description();
	}

	public boolean specialAbility(Party heroes, Party monsters)
	{
		return specialManager.chooseSpecialAbility(this, heroes, monsters);
	}

	public String getName()
	{
		return super.getName() + " the Mage";
	}

	public void upgradeAbilities()
	{
		specialManager.upgradeAbilities();
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof Mage)) { return false; }
		if(! super.equals(o)) { return false; }
		Mage mage = (Mage) o;
		return Objects.equal(specialManager, mage.specialManager);
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(super.hashCode(), specialManager);
	}
}
