package Factories;

import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import PartyManagement.Party;
import Utilities.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Michael on 5/19/2016.
 */
public class GenerateItems {
    private ArmorFactory armorFactory;
    private ConsumableFactory consumableFactory;
    private WeaponFactory weaponFactory;
    private Random rand;

    public GenerateItems()
    {
        armorFactory = new ArmorFactory();
        consumableFactory = new ConsumableFactory();
        weaponFactory = new WeaponFactory();
        rand = new Random();
    }

    public void generateItem(Party inventory, int floor)
    {
        List<List<String>> largeList = new ArrayList<List<String>>();

        int[] floor1 = new int[5];
        int[] floor2 = new int[4];
        int[] floor3 = new int[3];

        floor1[0] = 0;
        floor1[1] = 0;
        floor1[2] = 0;
        floor1[3] = 1;
        floor1[4] = 2;

        floor2[0] = 0;
        floor2[1] = 0;
        floor2[2] = 1;
        floor2[3] = 2;

        floor2[0] = 0;
        floor2[1] = 1;
        floor2[2] = 2;

        largeList.add(new ArrayList<String>());
        largeList.add(new ArrayList<String>());
        largeList.add(new ArrayList<String>());

        largeList.get(0).add("Healing");
        largeList.get(0).add("Power");

        largeList.get(1).add("Sword");
        largeList.get(1).add("Hammer");
        largeList.get(1).add("Dagger");
        largeList.get(1).add("Bow");
        largeList.get(1).add("Staff");

        largeList.get(2).add("Chainmail");
        largeList.get(2).add("Cloth");
        largeList.get(2).add("Leather");

        int level = inventory.getCharacter(0).getLevel();

        int generate = 0;
        switch(floor)
        {
            case 1:
                generate = floor1[rand.nextInt(floor1.length)];
                grabItem(inventory, generate, largeList.get(generate), level);
                break;
            case 2:
                generate = floor2[rand.nextInt(floor2.length)];
                grabItem(inventory, generate, largeList.get(generate), level);
                break;
            default:
                generate = floor3[rand.nextInt(floor3.length)];
                grabItem(inventory, generate, largeList.get(generate), level);
                break;
        }
    }

    public void grabItem(Party inventory, int generate, List<String> largeList, int power)
    {
        switch(generate)
        {
            case 0:
                Consumable consumable = consumableFactory.generate(largeList.get(rand.nextInt(largeList.size())), power);
                Display.displayMessage("You found a " + consumable);
                inventory.addToInventory(consumable);
                break;
            case 1:
                Weapon weapon = weaponFactory.generate(largeList.get(rand.nextInt(largeList.size())), power);
                Display.displayMessage("You found a " + weapon);
                inventory.addToInventory(weapon);
                break;
            default:
                Armor armor = armorFactory.generate(largeList.get(rand.nextInt(largeList.size())), power);
                Display.displayMessage("You found a " + armor);
                inventory.addToInventory(armor);
                break;
        }
    }
}
