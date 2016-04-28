package Item;

public class Dagger extends Weapon
{
   public Dagger(int power)
   {
      super(power);
   }

   public void equip(Character.Character character)
   {
      character.equipWeapon(this);
   }
}