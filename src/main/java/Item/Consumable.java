package Item;

import Characters.A_Character;

public abstract class Consumable
{
   private int power;
   
   protected Consumable(int power)
   {
      if(power > 5 || power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
      }
      
      this.power = power;
   }

   public abstract void use(A_Character character);
   
   public int getPower()
   {
      return this.power;
   }
   
   public String getParentType()
   {
      return "Consumable";
   }
   
   public String getChildType()
   {
      return this.getClass() + "";
   }
   
   public String toString()
   {
      return "Potion of ";
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Consumable that = (Consumable) o;

      if (power != that.power) return false;

      return true;
   }

   @Override
   public int hashCode()
   {
      return power;
   }
}