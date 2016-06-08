package Factories;

import Characters.A_Character;
import Item.*;
import Nemesis.MasterAssassin;
import Nemesis.Necromancer;
import Nemesis.Trent;
import Nemesis.Werewolf;
import PartyManagement.Party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gm14793 on 5/20/16.
 */
public class NemesisPartyFactory
{
	private List<String> possibleNemesis;

	public NemesisPartyFactory()
	{
		possibleNemesis = new ArrayList<String>();

		possibleNemesis.add("Necromancer");
		possibleNemesis.add("Werewolf");
		possibleNemesis.add("Trent");
		possibleNemesis.add("Master Assassin");
	}

	public Party getRandomNemesis(int level, int floor)
	{
		if (level < 1 || floor < 1)
		{
			throw new IllegalArgumentException("Invalid level or floor. Values must be greater than 0.");
		}

		Random rand = new Random();
		int nemesisParty;
		Party party = NecromancerParty(level, floor);
		String boss = "";
		if(possibleNemesis.size() > 0)
		{
			nemesisParty = rand.nextInt(possibleNemesis.size());
			boss = possibleNemesis.get(nemesisParty);
			possibleNemesis.remove(nemesisParty);
		}

		switch(boss)
		{
			case "Necromancer":
				party = NecromancerParty(level, floor);
				break;
			case "Werewolf":
				party = WerewolfParty(level, floor);
				break;
			case "Trent":
				party = TrentParty(level, floor);
				break;
			case "Master Assassin":
				party = AssassinParty(level, floor);
				break;
		}
		return party;
	}

	private Party NecromancerParty(int level, int floor)
	{
		List<A_Character> necParty = new ArrayList<A_Character>();

		necParty.add(new Necromancer("Lord Drengar, The Vile", 1000, 30, 10, new Cloth(level), new Staff(level), level));
		necParty.add(new MonsterFactory().createMonster("Skeleton", "Skeleton", level, true, floor));
		necParty.add(new MonsterFactory().createMonster("Undead Cleric", "Skeleton Cleric", level, true, floor));

		Party party = new Party(necParty);
		party.setFloorLevel(floor);

		return party;
	}

	private Party WerewolfParty(int level, int floor)
	{
		List<A_Character> wereParty = new ArrayList<A_Character>();

		wereParty.add(new Werewolf("Krant, the Dire", 600, 15, 10, new Cloth(level), new Hammer(level), level));
		wereParty.add(new MonsterFactory().createMonster("Dire Wolf", "Dire Wolf 1", level, true, floor));
		wereParty.add(new MonsterFactory().createMonster("Dire Wolf", "Dire Wolf 2", level, true, floor));

		Party party = new Party(wereParty);
		party.setFloorLevel(floor);

		return party;
	}

	private Party TrentParty(int level, int floor)
	{
		List<A_Character> trentParty = new ArrayList<A_Character>();

		trentParty.add(new Trent("Olohem, The Forsaken", 1200, 35, 4, new Cloth(level), new Hammer(level), level));
		trentParty.add(new MonsterFactory().createMonster("Sapling", "Sapling 1", level, true, floor));
		trentParty.add(new MonsterFactory().createMonster("Sapling", "Sapling 2", level, true, floor));

		Party party = new Party(trentParty);
		party.setFloorLevel(floor);

		return party;
	}

	private Party AssassinParty(int level, int floor)
	{
		List<A_Character> assassinParty = new ArrayList<A_Character>();

		assassinParty.add(new MasterAssassin("Scarlett, The Shadow", 600, 13, 25, new Leather(level), new Dagger(level), level));
		assassinParty.add(new MonsterFactory().createMonster("Assassin", "Assassin 1", level, true, floor));
		assassinParty.add(new MonsterFactory().createMonster("Assassin", "Assassin 2", level, true, floor));

		Party party = new Party(assassinParty);
		party.setFloorLevel(floor);

		return party;
	}
}
