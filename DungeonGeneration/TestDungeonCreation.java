public class TestDungeonCreation
{
   public static void main(String[] args)
   {
      GenerateDungeon dungeon;
      System.out.println(10);
      for(int x = 0; x < 10; x++)
      {
         System.out.println(40 + " " + 40);
         dungeon = new GenerateDungeon(40);
         dungeon.generatePath();
         System.out.println(dungeon);
      }
   }
}