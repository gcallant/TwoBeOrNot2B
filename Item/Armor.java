package Item;

public abstract class Armor implements Storable, Equipable
{
   private int power;
   private Enchantment enchantment;
   
   public Armor(int power)
   {
      if(power > 3 || power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
      }
      
      this.power = power;
      this.enchantment = null;
   }
   
   public void use(Character character)
   {
      this.equip(character);
   }
   
   public int getPower()
   {
      return this.power;
   }
   
   public void setEnchantment(Enchantment enchantment)
   {
      this.enchantment = enchantment;
   }
   
   public abstract void equip(Character character);
}