package Item;

import Characters.A_Character;

import java.io.Serializable;

public abstract class Consumable implements Serializable
{
   private int power;
   private int rounds;
   
   protected Consumable(int power)
   {
      if(power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion Power.");
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
   public boolean equals(Object o)
   {
      if(this == o) { return true; }
      if(! (o instanceof Consumable)) { return false; }

      Consumable that = (Consumable) o;

      if(power != that.power) { return false; }
      if(rounds != that.rounds) { return false; }

      return true;
   }

   @Override
   public int hashCode()
   {
      int result = power;
      result = 31 * result + rounds;
      return result;
   }
}