package Factories;

import Characters.*;
import java.util.*;
import Item.*;

/**
 * Created by gm14793 on 5/20/16.
 */
public class NemesisPartyFactory
{
    private double power;
    ArrayList<Party> possibleNemesis;

    public NemesisPartyFactory(int level)
    {
        possibleNemesis = new ArrayList<Party>();
        possibleNemesis.add(NecromancerParty(level));
        possibleNemesis.add(WerewolfParty(level));
        possibleNemesis.add(AssassinParty(level));
    }

    public Party getRandomNemesis()
    {
        Random rand = new Random();
        int nemesisParty = rand.nextInt(possibleNemesis.size());
        Party enemy = possibleNemesis.get(nemesisParty);
        enemy.getCharacterParty().remove(nemesisParty);
        return enemy;
    }

    private Party NecromancerParty(int level)
    {
        ArrayList<A_Character> necParty = new ArrayList<A_Character>();
        necParty.add(new Necromancer("Denegar, Lord",(level*15)+200,level+3,level+4,new Cloth(level),new Staff(level)));
        necParty.add(new Skeleton("Skeleton", (level*5) + 30, level+2, level+2, new Cloth(level), new Sword(level)));
        necParty.add(new Skeleton("Skeleton", (level*5) + 30, level+2, level+2, new Cloth(level), new Sword(level)));
        return new Party(necParty);
    }

    private Party WerewolfParty(int level)
    {
        ArrayList<A_Character> wereParty = new ArrayList<A_Character>();
        wereParty.add(new Werewolf("Krant the Dire", (level*15)+250, level+5, level+6, new Chainmail(level), new Sword(level)));
        wereParty.add(new DireWolf("DireWolf", (level*5)+100, level + 4, level + 3, new Chainmail(level), new Sword(level)));
        wereParty.add(new DireWolf("DireWolf", (level*5)+100, level + 4, level + 3, new Chainmail(level), new Sword(level)));
        return new Party(wereParty);
    }

    private Party AssassinParty(int level)
    {
        ArrayList<A_Character> assassinParty = new ArrayList<A_Character>();
        assassinParty.add(new MasterAssassin("Jerrik", (level*15)+220, level+4, level+5, new Cloth(level), new Dagger(level)));
        assassinParty.add(new Assassin("Elite Assassin", (level*10)+100, level+3, level+4, new Cloth(level), new Dagger(level)));
        assassinParty.add(new Assassin("Veteran Assassin", (level*7)+90, level+3, level+3, new Cloth(level), new Dagger(level)));
        return new Party(assassinParty);
    }

}
