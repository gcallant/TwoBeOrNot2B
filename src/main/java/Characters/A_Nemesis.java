package Characters;

import Item.*;
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

    ArrayList<Consumable> potions;

    public A_Nemesis(String name, int health, int strength, int dexterity, ArmorType armorType, Armor armor, WeaponType weaponType, Weapon weapon)
    {
        super(name, health, strength, dexterity, armorType,armor, weaponType, weapon);
        potions = new ArrayList<Consumable>();
        potions.add(new Healing(5));
        potions.add(new Healing(5));
        cooldown = 0;

    }

    public boolean takeAction(Party heroes, Party monsters)
    {
        boolean noTurn;

        noTurn = cannotAttack();
        resetTurn();

        if(noTurn)
        {
            System.out.println(getName() + " is incapacitated and can't act!");
            return false;
        }

        int choiceToAttack = rand.nextInt(heroes.size());

        if(getHealth() < getMaxHealth()/2 && potions.size() > 0)
        {
            if(rand.nextBoolean())
            {
                potions.get(0).use(this);
            }
        }
        else if(cooldown == 0)
        {
            specialAbility(rand, heroes, monsters);
            cooldown = 4;
        }
        else
        {
            A_Character toAttack = heroes.getCharacter(choiceToAttack);
            attack(toAttack);
            cooldown--;
        }
        return false;
    }

    public abstract boolean specialAbility(Random rand, Party heroes, Party monsters);

}
