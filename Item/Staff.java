package Item;

public class Staff extends Weapon
{
   public Staff(int power)
   {
      super(power);
   }

   public void equip(Character.Character character)
   {
      character.equipWeapon(this);
   }
}