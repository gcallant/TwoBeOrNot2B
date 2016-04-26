package Item;

public interface Equipable
{     
   public void use(Character character);
   public int getPower();
   public void setEnchantment(Enchantment enchantment);
   public void equip(Character character);
}