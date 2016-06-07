package Item;

import java.io.Serializable;

public abstract class Weapon implements Serializable
{
   private int power;
   private Enchantment enchantment;
   private String attackType;
   
   public Weapon(int power, String attackType)
   {
      if(power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for weapon Power.");
      }
      this.power = power;
      this.enchantment = null;
      this.attackType = attackType;
   }

   public String getAttackType()
   {
      return this.attackType;
   }
   
   public int getPower()
   {
      return this.power + getBase();
   }
   
   public void setEnchantment(Enchantment enchantment)
   {
      this.enchantment = enchantment;
   }
   
   public String toString()
   {
      return "Weapon: ";
   }

   public abstract WeaponType getWeaponType();

   public abstract int getBase();

   @Override
   public boolean equals(Object o)
   {
      if(this == o) { return true; }
      if(! (o instanceof Weapon)) { return false; }

      Weapon weapon = (Weapon) o;

      if(power != weapon.power) { return false; }
      if(enchantment != null ? ! enchantment.equals(weapon.enchantment) : weapon.enchantment != null) { return false; }
      if(attackType != null ? ! attackType.equals(weapon.attackType) : weapon.attackType != null) { return false; }

      return true;
   }

   @Override
   public int hashCode()
   {
      int result = power;
      result = 31 * result + (enchantment != null ? enchantment.hashCode() : 0);
      result = 31 * result + (attackType != null ? attackType.hashCode() : 0);
      return result;
   }
}