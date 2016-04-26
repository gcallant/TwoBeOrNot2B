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
   
   public void use(Character character)
   {
      this.consume(character);
   }
   
   public int getPower()
   {
      return this.power;
   }
   
   public abstract void consume(Character character);
}