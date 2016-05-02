package Item;

public class Healing extends Consumable
{
   public Healing(int power)
   {
      super(power);
   }
   
   public String toString()
   {
      return super.toString() + "Healing";
   }
}