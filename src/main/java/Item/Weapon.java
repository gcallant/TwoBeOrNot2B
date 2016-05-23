package Item;

public abstract class Weapon
{
   private int power;
   private Enchantment enchantment;
   private String attackType;
   
   public Weapon(int power, String attackType)
   {
      if(power > 10 || power < 0)
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
   
   public String toString()
   {
      return "Weapon: ";
   }

   public abstract WeaponType getWeaponType();

   public abstract int getBase();
}