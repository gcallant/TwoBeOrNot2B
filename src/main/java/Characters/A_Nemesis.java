package Characters;

import Item.*;
import Mediator.Mediator;
import PartyManagement.Party;

import java.util.*;

/**
 * Created by Greig on 5/19/2016.
 */

/*
Werewolf turns into DireWolf nemesis with wolf allies.
Assassin: Special poison vial with rogues
Anti paladin: Special: inflict damage on all heroes and cause random debuff
Warlord:
 */

public abstract class A_Nemesis extends A_Character
{

    protected List<Consumable> potions;
    protected int cooldown;
    protected boolean rage;
    protected boolean rageUsed;
    protected int rageCount;

    public A_Nemesis(String name, int health, int power, int cunning, ArmorType armorType, Armor armor,
                     WeaponType weaponType, Weapon weapon, int level, CreatureType creatureType)
    {
        super(name, health, power, cunning, armorType,armor, weaponType, weapon, creatureType);
        potions = new ArrayList<Consumable>();
        potions.add(new Healing(5));
        potions.add(new Healing(5));
        cooldown = 0;
        int curLevel = 1;

        while(curLevel < level)
        {
            levelUp();
            curLevel++;
        }
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

        if(getDefeated())
        {
            return false;
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
                rageCount = Math.max(0,rageCount - 1);
                if(rageCount == 0)
                {
                    rage = false;
                }
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

    public boolean isRaged()
    {
        return (getHealth() < (getMaxHealth()/4)) && !rageUsed;
    }

    public void endTurn()
    {
        cooldown--;
        super.endTurn();
    }

    public abstract void startRage(Random rand, Party heroes, Party monsters);

    public abstract void levelUp();

    public abstract boolean specialAbility(Random rand, Party heroes, Party monsters);

}
