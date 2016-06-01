package Item;

import java.io.Serializable;

public abstract class Armor implements Serializable
{
   private int power;
   private Enchantment enchantment;
   
   public Armor(int power)
   {
      if (power < 0) {
         throw new IllegalArgumentException(power + " is an invalid value for potion Power.");
      }

      this.power = power;
      this.enchantment = null;
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
      return "Armor: ";
   }

   public abstract int getBase();

   public abstract ArmorType getArmorType();

   @Override
   public boolean equals(Object o)
   {
      if(this == o) { return true; }
      if(! (o instanceof Armor)) { return false; }

      Armor armor = (Armor) o;

      if(power != armor.power) { return false; }
      if(enchantment != null ? ! enchantment.equals(armor.enchantment) : armor.enchantment != null) { return false; }

      return true;
   }

   @Override
   public int hashCode()
   {
      int result = power;
      result = 31 * result + (enchantment != null ? enchantment.hashCode() : 0);
      return result;
   }
}