package Item;

public class Sword extends Weapon
{
   public Sword(int power)
   {
      super(power);
   }
   
   public void equip(Character character)
   {
      character.equipWeapon(this);
   }
}