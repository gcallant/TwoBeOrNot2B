package Factories;

import Characters.A_Character;
import Item.*;
import Monsters.Assassin;
import Monsters.DireWolf;
import Monsters.Skeleton;
import Nemesis.MasterAssassin;
import Nemesis.Necromancer;
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
	List<Party> possibleNemesis;
	private double power;

	public NemesisPartyFactory(int level)
	{
		possibleNemesis = new ArrayList<Party>();
//		possibleNemesis.add(NecromancerParty(level));
//		possibleNemesis.add(WerewolfParty(level));
//		possibleNemesis.add(AssassinParty(level));
	}

	public Party getRandomNemesis()
	{
		Random rand = new Random();
		int nemesisParty = rand.nextInt(possibleNemesis.size());
		Party enemy = possibleNemesis.get(nemesisParty);
		enemy.getCharacter(nemesisParty);
		return enemy;
	}

	private Party NecromancerParty(int level, int floor)
	{
		List<A_Character> necParty = new ArrayList<A_Character>();
		necParty.add(new Necromancer("Denegar, Lord", (level * 15) + 200, level + 3, level + 4, new Cloth(level), new Staff(level), level + 3));
		/*necParty.add(new Skeleton("Skeleton", (level * 5) + 30, level + 2, level + 2, new Cloth(level), new Sword(level)
				                           , level + 2));
		necParty.add(new Skeleton("Skeleton", (level * 5) + 30, level + 2, level + 2, new Cloth(level), new Sword(level)
				                           , level + 2));
		return new Party(necParty);*/
		return null;
	}

	private Party WerewolfParty(int level)
	{
		/*List<A_Character> wereParty = new ArrayList<>();
		wereParty.add(new Werewolf("Krant the Dire", (level * 15) + 250, level + 5, level + 6, new Chainmail(level), new Sword(level)));
		wereParty.add(new DireWolf("DireWolf", (level * 5) + 100, level + 4, level + 3, new Chainmail(level), new Sword
				                                                                                                        (level), level + 3));
		wereParty.add(new DireWolf("DireWolf", (level * 5) + 100, level + 4, level + 3, new Chainmail(level), new Sword
				                                                                                                        (level), level + 3));
		return new Party(wereParty);*/
		return null;
	}

	private Party AssassinParty(int level)
	{
		/*List<A_Character> assassinParty = new ArrayList<>();
		assassinParty.add(new MasterAssassin("Jerrik", (level * 15) + 220, level + 4, level + 5, new Cloth(level), new Dagger(level)));
		assassinParty.add(new Assassin("Elite Assassin", (level * 10) + 100, level + 3, level + 4, new Cloth(level), new Dagger(level), level + 3));
		assassinParty.add(new Assassin("Veteran Assassin", (level * 7) + 90, level + 3, level + 3, new Cloth(level), new Dagger(level), level + 3));
		return new Party(assassinParty);*/
		return null;
	}

}
