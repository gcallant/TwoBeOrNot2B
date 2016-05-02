package Item;

public abstract class Armor implements Storable, Equipable
{
   private int power;
   private Enchantment enchantment;
   private boolean equipped;
   private boolean stored;
   
   public Armor(int power)
   {
      if(power > 3 || power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
      }
      
      this.power = power;
      this.enchantment = null;
      this.equipped = false;
      this.stored = false;
   }
   
   public void store()
   {
      this.stored = true;
      this.equipped = false;
   }
   
   public boolean isStored()
   {
      return this.stored;
   }
   
   public void use()
   {
      this.equipped = true;
      this.stored = false;
   }
   
   public int getPower()
   {
      return this.power;
   }
   
   public void setEnchantment(Enchantment enchantment)
   {
      this.enchantment = enchantment;
   }
   
   public String getParentType()
   {
      return "Armor";
   }
   
   public String toString()
   {
      return "Armor: ";
   }
   
   public String getChildType()
   {
      return this.getClass() + "";
   }
}