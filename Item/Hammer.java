package Item;

public class Hammer extends Weapon
{
   public Hammer(int power)
   {
      super(power);
   }
   
   public void equip(Character character)
   {
      character.equipWeapon(this);
   }
}