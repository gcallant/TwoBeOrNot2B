package Characters;

import Item.Armor;
import Item.ArmorType;
import Item.Weapon;
import Item.WeaponType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Greig on 5/19/2016.
 */
public class DireWolf extends A_Monster
{
        public DireWolf(String name, int health, int strength, int dexterity, Armor armor, Weapon weapon)
        {
            super(name, health, strength, dexterity, ArmorType.Medium, armor, WeaponType.Medium, weapon);
        }

        public boolean specialAbility(Random rand, Party heroes, Party monsters)
        {
            viciousBite(heroes.getCharacter(rand.nextInt(heroes.size())));
            return false;
        }

        public int getLevel()
        {
            return 2;
        }
}
