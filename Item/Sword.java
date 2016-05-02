package Item;

public class Sword extends Weapon
{
   private DamageType damageType;
   
   public Sword(int power)
   {
      super(power);
      this.damageType = DamageType.Slashing;
   }
   
   public String toString()
   {
      return super.toString() + "Sword";
   }
}