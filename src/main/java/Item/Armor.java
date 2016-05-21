package Item;

public abstract class Armor
{
   private int power;
   private Enchantment enchantment;
   
   public Armor(int power)
   {
      if (power < 0) {
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
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (!(obj instanceof Armor))
      {
         return false;
      }
      Armor newArmor = (Armor)obj;

      return power == newArmor.power && enchantment.equals(newArmor.enchantment);
   }
}