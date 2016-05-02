package Item;

public class Dagger extends Weapon
{
   private DamageType damageType;
   
   public Dagger(int power)
   {
      super(power);
      this.damageType = DamageType.Piercing;
   }
   
   public String toString()
   {
      return super.toString() + "Dagger";
   }
}