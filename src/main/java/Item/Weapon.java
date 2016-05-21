package Item;

public abstract class Weapon
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
   
   public String toString()
   {
      return "Weapon: ";
   }

   public abstract WeaponType getWeaponType();

   public abstract int getBase();

   @Override
   public boolean equals(Object obj)
   {
      if(obj == null)
      {
         return false;
      }
      if(!(obj instanceof Weapon))
      {
         return false;
      }
      Weapon newWeapon = (Weapon)obj;
      return power == newWeapon.power && enchantment == newWeapon.enchantment && attackType.equals(newWeapon.attackType);
   }
}