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

/*
Werewolf turns into DireWolf nemesis with wolf allies.
Assassin: Special poison vial with rogues
Anti paladin: Special: inflict damage on all heroes and cause random debuff
Warlord:
 */

public abstract class A_Nemesis extends A_Character
{

    protected int cooldown;
    protected boolean rage;
    private boolean rageUsed;
    private int rageCount;

    public A_Nemesis(String name, int health, int power, int cunning, ArmorType armorType, Armor armor,
                     WeaponType weaponType, Weapon weapon, int level, CreatureType creatureType)
    {
        super(name, health, power, cunning, armorType,armor, weaponType, weapon, creatureType);
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

        if((cooldown == 0 && monsters.size() < 6) || (cooldown == 0 && rage))
        {
            specialAbility(rand, heroes, monsters);
            if(!rage || rageCount == 0)
            {
                cooldown = 2;
            }
            else
            {
                rageCount -= 1;
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

    public int getLevel()
    {
        return 100*super.getLevel();
    }

}
