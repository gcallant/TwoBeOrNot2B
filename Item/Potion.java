package Item;

public class Potion extends Consumable
{
   public Potion(int power)
   {
      super(power);
   }
   
   public void consume(Character character)
   {
      character.restore(super.getPower());
   }
}