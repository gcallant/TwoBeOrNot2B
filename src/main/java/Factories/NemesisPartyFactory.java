package Factories;

import Characters.A_Character;
import Characters.A_Nemesis;
import Item.*;
import Monsters.Assassin;
import Monsters.DireWolf;
import Monsters.Skeleton;
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
	List<String> possibleNemesis;

	public NemesisPartyFactory()
	{
		possibleNemesis = new ArrayList<String>();
		possibleNemesis.add("Necromancer");
		possibleNemesis.add("Werewolf");
		possibleNemesis.add("Trent");
	}

	public Party getRandomNemesis(int level, int floor)
	{
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
		}

		return party;
	}

	private Party NecromancerParty(int level, int floor)
	{
		List<A_Character> necParty = new ArrayList<A_Character>();
		necParty.add(new Necromancer("Lord Denegar, The Vile", 1000, 20, 10, new Cloth(level), new Staff(level), level));
		necParty.add(new MonsterFactory().createMonster("Skeleton", "Skeleton", level, true, floor));
		necParty.add(new MonsterFactory().createMonster("Undead Cleric", "Skeleton Cleric", level, true, floor));
		Party party = new Party(necParty);
		party.setFloorLevel(floor);
		return party;
	}

	private Party WerewolfParty(int level, int floor)
	{
		List<A_Character> wereParty = new ArrayList<A_Character>();
		wereParty.add(new Werewolf("Krant, the Dire", 600, 20, 10, new Cloth(level), new Hammer(level), level));
		wereParty.add(new MonsterFactory().createMonster("Dire Wolf", "Dire Wolf", level, true, floor));
		wereParty.add(new MonsterFactory().createMonster("Dire Wolf", "Dire Wolf", level, true, floor));
		Party party = new Party(wereParty);
		party.setFloorLevel(floor);
		return party;
	}

	private Party TrentParty(int level, int floor)
	{
		List<A_Character> trentParty = new ArrayList<A_Character>();
		trentParty.add(new Trent("Olohem, The Iron Skin", 1800, 30, 4, new Cloth(level), new Hammer(level), level));
		trentParty.add(new MonsterFactory().createMonster("Sapling", "Sapling", level, true, floor));
		trentParty.add(new MonsterFactory().createMonster("Sapling", "Sapling", level, true, floor));
		Party party = new Party(trentParty);
		party.setFloorLevel(floor);
		return party;
	}
}
