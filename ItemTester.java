import Item.*;
import java.util.*;

public class ItemTester
{
   public static void main(String[] args)
   {
      ArrayList myList = new ArrayList<Storable>();
      
      myList.add(new Bow(1));
      myList.add(new Chainmail(2));
      myList.add(new Dagger(3));
      myList.add(new Bow(3));
      myList.add(new Bow(1));
      myList.add(new Hammer(3));
      myList.add(new Dagger(1));
      myList.add(new Potion(2));
      myList.add(new Staff(1));
      myList.add(new Sword(2));
      
      for(int x = 0; x < myList.size(); x++)
      {
         System.out.println(myList.get(x) + " " + ((Storable)myList.get(x)).getPower());
      }
      
      System.out.println("\n\n\n\n\n");
      
      Collections.sort(myList, new CompareStorables());
      
      for(int x = 0; x < myList.size(); x++)
      {
         System.out.println(myList.get(x) + " " + ((Storable)myList.get(x)).getPower());
      }
   }
}