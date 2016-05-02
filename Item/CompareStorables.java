package Item;

import java.util.Comparator;

public class CompareStorables implements Comparator<Storable>
{
   public int compare(Storable s1, Storable s2)
   {
      int parentType = s1.getParentType().compareTo(s2.getParentType());
      int childType = s1.getChildType().compareTo(s2.getChildType());
      int power = s1.getPower() - s2.getPower();
      
      if(parentType != 0)
      {
         return parentType;
      }
      
      if(childType != 0)
      {
         return childType;
      }
      
      return power;
   }
}