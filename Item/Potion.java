package Item;

public class Potion extends Consumable
{
   public Potion(int power)
   {
      super(power);
   }
   
   public String toString()
   {
      return super.toString() + "Healing";
   }
}