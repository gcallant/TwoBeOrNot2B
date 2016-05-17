package Item;

public abstract class Weapon implements Storable, Equipable
{
   private int power;
   private Enchantment enchantment;
   private String attackType;
   
   public Weapon(int power, String attackType)
   {
      if(power > 3 || power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for weapon power.");
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
   
   public String getParentType()
   {
      return "Weapon";
   }
   
   public String getChildType()
   {
      return this.getClass() + "";
   }
   
   public String toString()
   {
      return "+" + this.getPower() + " Weapon: ";
   }

   public abstract int getBase();
}