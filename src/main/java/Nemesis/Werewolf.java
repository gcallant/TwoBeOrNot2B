package Nemesis;

import Characters.A_Nemesis;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import PartyManagement.Party;

import java.util.*;

/**
 * Created by gm14793 on 5/22/16.
 */
public class Werewolf extends A_Nemesis
{
    public Werewolf(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Medium, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        //doubleAttack(heroes.getCharacter(rand.nextInt(heroes.size())));
        return false;
    }
}
