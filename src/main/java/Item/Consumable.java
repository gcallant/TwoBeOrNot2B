package Item;

import Characters.A_Character;

public abstract class Consumable
{
   private int power;
   private int rounds;
   
   protected Consumable(int power)
   {
      if(power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
      }
      this.rounds = 4;
      this.power = power;
   }

   public abstract void use(A_Character character);

   public boolean decrementRounds()
   {
      rounds--;
      return rounds == 0;
   }
   
   public int getPower()
   {
      return this.power;
   }
   
   public String toString()
   {
      return "Potion of ";
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (!(obj instanceof Consumable))
      {
         return false;
      }
      Consumable newConsume = (Consumable)obj;
      return power == newConsume.power;
   }
}