package Item;

public abstract class Armor
{
   private int power;
   private Enchantment enchantment;
   
   public Armor(int power)
   {
      if (power > 10 || power < 0) {
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
}