package Item;

public abstract class Consumable implements Storable
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
}