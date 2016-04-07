import java.util.Random;

public class GenerateDungeon
{
   private DungeonTile[][] dungeon;
   private int size;
   private static int[][] checked;
   
   public GenerateDungeon(int size)
   {
      this.size = size;
      this.dungeon = new DungeonTile[size][size];
      
      for(int row = 0; row < this.dungeon.length; row++)
      {
         for(int col = 0; col < this.dungeon[row].length; col++)
         {
            this.dungeon[row][col] = new DungeonTile();
         }
      }
   }
   
   public void generatePath()
   {
      int xPrev = -1, yPrev = -1, count = 0, pick = 0;
      int xLoc = 0, yLoc = 0;
      int[] direction, path;
      Random rand = new Random();
      
      while(xLoc != this.size - 1 || yLoc != this.size - 1)
      {
         direction = findLimit(xLoc,yLoc,xPrev,yPrev,this.dungeon);
         count = 0;
         path = null;
         while(path == null)
         {
            dungeon[yLoc][xLoc].generateDirection(direction);
            path = goodPath(xLoc, yLoc, xPrev, yPrev, direction, this.dungeon, this.size);
         }
         for(int x = 0; x < path.length; x++)
         {
            if(path[x] == 1)
               count++;
         }
         pick = rand.nextInt(count);
         for(int x = 0; x < path.length; x++)
         {
            if(path[x] == 1)
            {
               count--;
               if(count == pick)
               {
                  count = x;
                  x = path.length;
               }
            }
         }
         xPrev = xLoc;
         yPrev = yLoc;
         switch(count)
         {
            case 1: yLoc--;
                    break;
            case 0: xLoc--;
                    break;
            case 2: xLoc++;
                    break;
            case 3: yLoc++;
                    break;
            default: break;
         }
      }
      int[] fixArray = {1,1,0,0};
      dungeon[size-1][size-1].setDirection(fixArray);
      
      for(int y = 0; y < size; y++)
      {
         for(int x = 0; x < size; x++)
         {
            fixArray = dungeon[y][x].getDirection();
            if(y != 0 && dungeon[y-1][x].getDirection()[3] == 0)
            {
               fixArray[1] = 0;
            }
            if(y != size - 1 && dungeon[y+1][x].getDirection()[1] == 0)
            {
               fixArray[3] = 0;
            }
            if(x != 0 && dungeon[y][x-1].getDirection()[2] == 0)
            {
               fixArray[0] = 0;
            }
            if(x != size - 1 && dungeon[y][x+1].getDirection()[0] == 0)
            {
               fixArray[2] = 0;
            }
            if(x == 0)
               fixArray[0] = 0;
            if(x == size - 1)
               fixArray[2] = 0;
            if(y == 0)
               fixArray[1] = 0;
            if(y == size - 1)
               fixArray[3] = 0; 
            if(x == 0 && y == 0)
               fixArray[0] = 1;
            if(x == size - 1 && y == size - 1)
               fixArray[2] = 1;
            dungeon[y][x].setDirection(fixArray);
         }
      }
   }
   
   private static int[] findLimit(int xLoc, int yLoc, int xPrev, int yPrev, DungeonTile[][] dungeon)
   {
      int[] retArray = new int[4];
      retArray[0] = 2;
      retArray[1] = 2;
      retArray[2] = 2;
      retArray[3] = 2;
      
      if(xLoc == 0) //|| !goodPath(xLoc - 1, yLoc))
      {
         retArray[0] = 0;
      }
      else if(xPrev == xLoc - 1 && yPrev == yLoc)
      {
         retArray[0] = 1;
      }
      else if(dungeon[yLoc][xLoc - 1].isUsed())
      {
         retArray[0] = 0;
      }
      if(xLoc == dungeon.length - 1)// || !goodPath(xLoc + 1, yLoc))
      {
         retArray[2] = 0;
      }
      else if(xPrev == xLoc + 1 && yPrev == yLoc)
      {
         retArray[2] = 1;
      }
      else if(dungeon[yLoc][xLoc + 1].isUsed())
      {
         retArray[2] = 0;
      }
      if(yLoc == 0) //|| !goodPath(xLoc, yLoc - 1))
      {
         retArray[1] = 0;
      }
      else if(xPrev == xLoc && yPrev == yLoc - 1)
      {
         retArray[1] = 1;
      }
      else if(dungeon[yLoc - 1][xLoc].isUsed())
      {
         retArray[1] = 0;
      }
      if(yLoc == dungeon.length - 1)// || !goodPath(xLoc, yLoc + 1))
      {
         retArray[3] = 0;
      }
      else if(xPrev == xLoc && yPrev == yLoc + 1)
      {
         retArray[3] = 1;
      }
      else if(dungeon[yLoc + 1][xLoc].isUsed())
      {
         retArray[3] = 0;
      }
      return retArray;
   }
   
   private static int[] goodPath(int xLoc, int yLoc, int xPrev, int yPrev, int[] limit, DungeonTile[][] dungeon, int size)
   {
      int[] direction = dungeon[yLoc][xLoc].getDirection();
      int[] validPaths = new int[4];
      if(xPrev == -1)
      {
         if(direction[2] != 0 || direction[3] != 0)
            return direction;
         else
            return null;
      }
      
      if(limit[0] == 2 && dungeon[yLoc][xLoc].getDirection()[0] == 1)
      {
            checked = new int[size][size];
            validPaths[0] = findIfValid(xLoc - 1, yLoc, size, dungeon);
      }
      if(limit[1] == 2 && dungeon[yLoc][xLoc].getDirection()[1] == 1)
      {
            checked = new int[size][size];
            validPaths[1] = findIfValid(xLoc, yLoc - 1, size, dungeon);
      }
      if(limit[2] == 2 && dungeon[yLoc][xLoc].getDirection()[2] == 1)
      {
            checked = new int[size][size];
            validPaths[2] = findIfValid(xLoc + 1, yLoc, size, dungeon);
      }
      if(limit[3] == 2 && dungeon[yLoc][xLoc].getDirection()[3] == 1)
      {
            checked = new int[size][size];
            validPaths[3] = findIfValid(xLoc, yLoc + 1, size, dungeon);
      }
      
      for(int x = 0; x < validPaths.length; x++)
      {
         if(validPaths[x] == 1)
            return validPaths;
      }
      return null;
   }
   
   private static int findIfValid(int xLoc, int yLoc, int size, DungeonTile[][] dungeon)
   {
      checked[yLoc][xLoc] = 1;
      int value = 0;
      if(xLoc == size - 1 && yLoc == size -1)
      {
         return 1;
      }
      if(value == 0 && xLoc + 1 != size && !dungeon[yLoc][xLoc + 1].isUsed() && checked[yLoc][xLoc + 1] != 1)
      {
         value = findIfValid(xLoc + 1, yLoc, size, dungeon);
         checked[yLoc][xLoc + 1] = 0;
      }
      if(value == 0 && yLoc + 1 != size && !dungeon[yLoc + 1][xLoc].isUsed() && checked[yLoc + 1][xLoc] != 1)
      {
         value = findIfValid(xLoc, yLoc + 1, size, dungeon);
         checked[yLoc + 1][xLoc] = 0;
      }
      if(value == 0 && xLoc - 1 != -1 && !dungeon[yLoc][xLoc - 1].isUsed() && checked[yLoc][xLoc - 1] != 1)
      {
         value = findIfValid(xLoc - 1, yLoc, size, dungeon);
         checked[yLoc][xLoc - 1] = 0;
      }
      if(value == 0 && yLoc - 1 != -1 && !dungeon[yLoc - 1][xLoc].isUsed() && checked[yLoc - 1][xLoc] != 1)
      {
         value = findIfValid(xLoc, yLoc - 1, size, dungeon);
         checked[yLoc - 1][xLoc] = 0;
      }
      return value;
   }
   
   public String toString()
   {
      String finalStr = "";
      String str1 = "", str2 = "", str3 = "";
      for(int x = 0; x < this.size; x++)
      {
         str1 = "";
         str2 = ""; 
         str3 = "";
         for(int z = 0; z < this.size; z++)
         {
            for(int y = 0; y < 3; y++)
            {
               if(y == 0)
               { 
                  if(this.dungeon[x][z].getDirection()[1] == 0)
                  {
                     str1 += " - ";
                  }
                  else
                  {
                     str1 += "   ";
                  }
               }
               else if(y == 1)
               {
                  if(this.dungeon[x][z].getDirection()[0] == 0)
                  {
                     str2 += "| ";
                  }
                  else
                  {
                     str2 += "  ";
                  }
                  if(this.dungeon[x][z].getDirection()[2] == 0)
                  {
                     str2 += "|";
                  }
                  else
                  {
                     str2 += " ";
                  }
               }
               else
               {
                  if(this.dungeon[x][z].getDirection()[3] == 0)
                  {
                     str3 += " - ";
                  }
                  else
                  {
                     str3 += "   ";
                  }
               }
            }
         }
         finalStr += str1 + "\n"  + str2 + "\n" + str3 + "\n";
      }
      return finalStr;
   }
}