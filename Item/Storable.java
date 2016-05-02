package Item;

public interface Storable 
{
   public void use();
   public int getPower();
   public void store();
   public boolean isStored();
   public String getParentType();
   public String getChildType();
}