package Item;

public class Bow extends Weapon
{
   public Bow(int power)
   {
      super(power);
   }

   public void equip(Character.Character character)
   {
      character.equipWeapon(this);
   }
}