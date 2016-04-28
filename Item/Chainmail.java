package Item;

public class Chainmail extends Armor
{
   public Chainmail(int power)
   {
      super(power);
   }

   public void equip(Character.Character character)
   {
      character.equipWeapon(this);
   }
}