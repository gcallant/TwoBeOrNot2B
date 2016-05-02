package Item;

public abstract class Consumable implements Storable
{
   private int power;
   private boolean stored;
   
   protected Consumable(int power)
   {
      if(power > 5 || power < 0)
      {
         throw new IllegalArgumentException(power + " is an invalid value for potion power.");
      }
      
      this.power = power;
      this.stored = false;
   }
   
   public void use()
   {
      this.stored = false;
   }
   
   public void store()
   {
      this.stored = true;
   }
   
   public boolean isStored()
   {
      return this.stored;
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