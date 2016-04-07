public class TestDungeonCreation
{
   public static void main(String[] args)
   {
      GenerateDungeon dungeon = new GenerateDungeon(10);
      dungeon.generatePath();
      System.out.println(dungeon);
   }
}