package Nemesis;

import Characters.A_Nemesis;
import Characters.CreatureType;
import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;
import Mediator.Mediator;
import PartyManagement.Party;

import java.util.*;

/**
 * Created by gm14793 on 5/22/16.
 */
public class MasterAssassin extends A_Nemesis
{
    public MasterAssassin(String name, int health, int power, int cunning, Armor armor, Weapon weapon)
    {
        super(name, health, power, cunning, ArmorType.Light, armor, WeaponType.Staff, weapon, 1, CreatureType.Undead);
    }

    public boolean specialAbility(Random rand, Party heroes, Party monsters)
    {
        return false;
    }
}
