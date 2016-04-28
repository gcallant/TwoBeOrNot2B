package Item;

public interface Equipable
{
   public void use(Character.Character character);
   public int getPower();
   public void setEnchantment(Enchantment enchantment);

   public void equip(Character.Character character);
}