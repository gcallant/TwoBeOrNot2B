package Equipment;

import Item.*;

import java.util.Random;

/**
 * Created by Michael on 5/6/2016.
 */
public class Equipment
{
    private Storable weapon;
    private Storable armor;

    public Equipment(Storable weapon, Storable armor)
    {
        this.weapon = weapon;
        this.armor = armor;
    }

    public int getWeaponPower()
    {
        return weapon.getPower();
    }

    public int getArmorPower()
    {
        return armor.getPower();
    }

    public int calculateDamage(int attackBonus, Equipment enemyEquipment)
    {
        Random rand = new Random();
        if((rand.nextInt(5) + this.getWeaponPower()) > enemyEquipment.getArmorPower())
        {
            return this.getWeaponPower();
        }
        else
        {
            return 0;
        }
    }

    public Storable equipWeapon(Storable weapon)
    {
        Storable returnValue = this.weapon;
        this.weapon = weapon;
        return returnValue;
    }

    public Storable equipArmor(Storable armor)
    {
        Storable returnValue = this.armor;
        this.armor = armor;
        return returnValue;
    }
}
