package TestDungeonGenerations;

import DungeonGeneration.*;

public class TestDungeonCreation
{
   public static void main(String[] args)
   {
      GenerateDungeon dungeon = null;
      System.out.println(1);
      int x = 10, y = 15;
      for(int z = 0; z < 100; z++)
      {
         System.out.println(x + " " + y);
         dungeon = new GenerateDungeon(x,y);
         try
         {
            dungeon.generatePath();
            System.out.println(dungeon);  
            System.out.println();
            System.out.println(dungeon.output());
         }
         catch(StackOverflowError e)
         {
            //System.out.println("Dungeon " + x + " failed to be created");
         }
      }
   }
}