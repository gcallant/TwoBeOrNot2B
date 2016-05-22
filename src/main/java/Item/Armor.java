package Item;

public abstract class Armor
{
   private int power;
   private Enchantment enchantment;
   
   public Armor(int power)
   {
      if (power > 3 || power < 0) {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
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

   protected abstract int getBase();

   public abstract ArmorType getArmorType();

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Armor armor = (Armor) o;

      if (power != armor.power) return false;
      //if (!enchantment.equals(armor.enchantment)) return false;

      return true;
   }

   @Override
   public int hashCode()
   {
      int result = power;
      result = 31 * result + enchantment.hashCode();
      return result;
   }
}