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
public class MasterAssassin extends A_Nemesis
{
    public MasterAssassin(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
    {
        super(name, health, strength, dexterity, ArmorType.Light, armor, WeaponType.Staff, weapon);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        return false;
    }
}
