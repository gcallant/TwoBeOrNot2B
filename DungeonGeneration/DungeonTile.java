package DungeonGeneration;

import java.util.Random;

public class DungeonTile
{
   private int[] direction;
   private boolean used;
   
   public DungeonTile()
   {
      this.used = false;
      this.direction = new int[4];
   }
   
   public void generateDirection(int[] limit)
   {
      boolean valid = false;
      Random rand = new Random();
      
      for(int x = 0; x < this.direction.length; x++)
      {
         if(limit[x] == 2)
         {
            this.direction[x] = rand.nextInt(2);
         }
         else
         {
            this.direction[x] = limit[x];
         }
      }
      this.used = true;
   }
   
   public int[] getDirection()
   {
      return this.direction;
   }
   
   public void setDirection(int[] array)
   {
      this.direction = array;
   }
   
   public boolean isUsed()
   {
      return used;
   }
}