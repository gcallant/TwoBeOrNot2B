package Item;

public abstract class Weapon
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
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Weapon weapon = (Weapon) o;

      if (power != weapon.power) return false;
      if (!attackType.equals(weapon.attackType)) return false;

      return true;
   }
}