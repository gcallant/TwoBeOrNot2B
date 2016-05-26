package DungeonGeneration;

import java.util.Arrays;
import java.util.Random;

public class GenerateDungeon
{
   private DungeonTile[][] dungeon;
   private int size;
   private static int maxSearch;
   private static int countToMax;
   private int[] startEnd;
   private int xStart, yStart, xEnd, yEnd;
   private int characterX, characterY;
   
   public GenerateDungeon(int height, int width)
   {
      this.size = size;
      this.dungeon = new DungeonTile[height][width];
      countToMax = 0;
      maxSearch = width*height;
      
      this.startEnd = chooseStartEnd();
      this.startEnd[0] = 0;
      this.startEnd[1] = 0;
      this.startEnd[2] = width - 1;
      this.startEnd[3] = height - 1;
      this.characterX = 0;
      this.characterY = 0;
      
      for(int row = 0; row < this.dungeon.length; row++)
      {
         for(int col = 0; col < this.dungeon[row].length; col++)
         {
            this.dungeon[row][col] = new DungeonTile();
         }
      }
   }
   
   private int[] chooseStartEnd()
   {
      int[] array = new int[4];
      Random rand = new Random();
      
      int leftOrTop = rand.nextInt(1);
      int rightOrBottom = rand.nextInt(1);
      
      if(leftOrTop == 0)
      {
         array[1] = rand.nextInt(this.dungeon.length);
         array[0] = 0;
      }
      else
      {
         array[0] = rand.nextInt(this.dungeon[0].length);
         array[1] = 0;
      }
      if(rightOrBottom == 0)
      {
         array[3] = rand.nextInt(this.dungeon.length);
         array[2] = this.dungeon[0].length - 1;
      }
      else
      {
         array[2] = rand.nextInt(this.dungeon[0].length);
         array[3] = this.dungeon.length - 1;
      }
      return array;
   }
   
   public void generatePath()throws StackOverflowError
   {
      characterX = 0;
      characterY = 0;

      int xPrev = -1, yPrev = -1, count = 0;
      int xLoc = startEnd[0], yLoc = startEnd[1];
      int[] direction, path;
      Random rand = new Random();
      int[][] checked = new int[dungeon.length][dungeon[0].length];
      checked[yLoc][xLoc] = 3;
      
      while(xLoc != startEnd[2] || yLoc != this.startEnd[3])
      {
         direction = findLimit(xLoc,yLoc,xPrev,yPrev,this.dungeon, this.startEnd);
         count = 0;
         path = null;
         
         while(path == null)
         {
            dungeon[yLoc][xLoc].generateDirection(direction);
            path = checkIfGoodPath(xLoc, yLoc, xPrev, yPrev, direction, this.dungeon, checked, this.startEnd);
         }
         
         checked[yLoc][xLoc] = 2;
         
         count = choosePath(path, rand);
         
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
      
      fixDungeon(this.dungeon, this.startEnd);
      int[] array = this.dungeon[0][0].getDirection();
      array[0] = 1;
      this.dungeon[0][0].setDirection(array);
   }
   
   private static int choosePath(int[] path, Random rand)
   {
      int count = 0, pick;
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
               return x;
            }
         }
      }
      return -1;
   }
   
   private static void fixDungeon(DungeonTile[][] dungeon, int[] startEnd)
   {
      int[] fixArray = {1,1,1,1};
      dungeon[dungeon.length-1][dungeon[0].length-1].setUsed();
      dungeon[dungeon.length-1][dungeon[0].length-1].setDirection(fixArray);
      if(dungeon[dungeon.length-2][dungeon.length-1].getDirection()[3] == 0)
      {
         dungeon[dungeon.length-1][dungeon.length-1].getDirection()[1] = 0;
      }
      else
      {
         dungeon[dungeon.length-1][dungeon.length-1].getDirection()[0] = 0;
      }
      //dungeon[startEnd[3]][startEnd[2]].setDirection(fixArray);*/
      
      for(int y = 0; y < dungeon.length; y++)
      {
         for(int x = 0; x < dungeon[0].length; x++)
         {
            if(x != dungeon[0].length -1 || y != dungeon.length - 1) {
               fixArray = dungeon[y][x].getDirection();
               if (y != 0 && dungeon[y - 1][x].getDirection()[3] == 0) {
                  fixArray[1] = 0;
               }
               if (y != startEnd[3] && dungeon[y + 1][x].getDirection()[1] == 0) {
                  fixArray[3] = 0;
               }
               if (x != 0 && dungeon[y][x - 1].getDirection()[2] == 0) {
                  fixArray[0] = 0;
               }
               if (x != startEnd[2] && dungeon[y][x + 1].getDirection()[0] == 0) {
                  fixArray[2] = 0;
               }
               if (x == 0 && y != 0)
                  fixArray[0] = 0;
               if (x == dungeon[0].length - 1)
                  fixArray[2] = 0;
               if (y == 0)
                  fixArray[1] = 0;
               if (y == startEnd[3])
                  fixArray[3] = 0;
               if (x == startEnd[2] && y == startEnd[3])
                  fixArray[2] = 1;
               dungeon[y][x].setDirection(fixArray);
            }
         }
      }
   }
   
   private static int[] findLimit(int xLoc, int yLoc, int xPrev, int yPrev, DungeonTile[][] dungeon, int[] startEnd)
   {
      int[] retArray = new int[4];
      retArray[0] = 2;
      retArray[1] = 2;
      retArray[2] = 2;
      retArray[3] = 2;
      
      if(xLoc == 0)
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
      if(xLoc == dungeon[0].length - 1)
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
      if(yLoc == 0)
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
      if(yLoc == dungeon.length - 1)
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
   
   private static int[] checkIfGoodPath(int xLoc, int yLoc, int xPrev, int yPrev, int[] limit, DungeonTile[][] dungeon, int[][] checked, int[] startEnd)throws StackOverflowError
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
            countToMax = 0;
            //if(yLoc != dungeon.length - 1 && yLoc != 0)
               validPaths[0] = findIfValid(xLoc - 1, yLoc, dungeon, checked, startEnd);
      }
      if(limit[1] == 2 && dungeon[yLoc][xLoc].getDirection()[1] == 1)
      {
            countToMax = 0;
            //if(xLoc != dungeon[0].length - 1 && xLoc != 0)
               validPaths[1] = findIfValid(xLoc, yLoc - 1, dungeon, checked, startEnd);
      }
      if(limit[2] == 2 && dungeon[yLoc][xLoc].getDirection()[2] == 1)
      {
            countToMax = 0;
            validPaths[2] = findIfValid(xLoc + 1, yLoc, dungeon, checked, startEnd);
      }
      if(limit[3] == 2 && dungeon[yLoc][xLoc].getDirection()[3] == 1)
      {
            countToMax = 0;
            validPaths[3] = findIfValid(xLoc, yLoc + 1, dungeon, checked, startEnd);
      }
      
      for(int x = 0; x < validPaths.length; x++)
      {
         if(validPaths[x] == 1)
            return validPaths;
      }
      return null;
   }
   
   private static int findIfValid(int xLoc, int yLoc, DungeonTile[][] dungeon, int[][] checked, int[] startEnd)throws StackOverflowError
   {
      int value = 0;
      countToMax++;
      if(countToMax > maxSearch)
      {
         return 0;
      }
      if(checked[yLoc][xLoc] == 0)
      {
         checked[yLoc][xLoc] = 1;
         if(xLoc == startEnd[2] && yLoc == startEnd[3])
         {
            checked[yLoc][xLoc] = 0;
            return 1;
         }
         if(value == 0 && xLoc + 1 != dungeon[0].length && !dungeon[yLoc][xLoc + 1].isUsed() && checked[yLoc][xLoc + 1] == 0)
         {
            value = findIfValid(xLoc + 1, yLoc, dungeon, checked, startEnd);
         }
         if(value == 0 && yLoc + 1 != dungeon.length && !dungeon[yLoc + 1][xLoc].isUsed() && checked[yLoc + 1][xLoc] == 0)
         {
            value = findIfValid(xLoc, yLoc + 1, dungeon, checked, startEnd);
         }
         if(value == 0 && xLoc - 1 != -1 && !dungeon[yLoc][xLoc - 1].isUsed() && checked[yLoc][xLoc - 1] == 0)
         {
            value = findIfValid(xLoc - 1, yLoc, dungeon, checked, startEnd);
         }
         if(value == 0 && yLoc - 1 != -1 && !dungeon[yLoc - 1][xLoc].isUsed() && checked[yLoc - 1][xLoc] == 0)
         {
            value = findIfValid(xLoc, yLoc - 1, dungeon, checked, startEnd);
         }
         checked[yLoc][xLoc] = 0;
      }
      return value;
   }
   
   public String output()
   {
      String str = "";
      int[] direction;
      int total;
      
      for(int x = 0; x < dungeon.length; x++)
      {
         for(int y = 0; y < dungeon[0].length; y++)
         {
            direction = this.dungeon[x][y].getDirection();
            total = 0;
            for(int z = 0; z < 4; z++)
            {
               total += direction[z]*Math.pow(2,z);
            }
            if((total + "").length() > 1)
               str += total + " ";
            else
               str += total + "  ";
         }
         str += "\n";
      }
      return str;
   }

   public String printCharacter()
   {
      String finalStr = "";
      String str1 = "", str2 = "", str3 = "";
      boolean ifUsed = false;
      for(int x = 0; x < dungeon.length; x++)
      {
         str1 = "";
         str2 = "";
         str3 = "";
         for(int z = 0; z < dungeon[0].length; z++)
         {
            for(int y = 0; y < 3; y++)
            {
                  ifUsed = this.dungeon[x][z].isUsed();
                  if (ifUsed) {
                     if (y == 0) {
                        if(x == this.dungeon.length - 1 && z == this.dungeon[x].length - 1)
                        {
                           if(!this.dungeon[x - 1][z].isUsed())
                           {
                              str1 += " - ";
                           }
                           else
                           {
                              str1 += "   ";
                           }
                        }
                        else {
                           if (this.dungeon[x][z].getDirection()[1] == 0) {
                              str1 += " - ";
                           } else {
                              str1 += "   ";
                           }
                        }
                     } else if (y == 1) {
                        if (x == this.dungeon.length - 1 && z == this.dungeon[x].length - 1) {
                           if (!this.dungeon[x][z-1].isUsed()) {
                              str2 += "[";
                           } else {
                              str2 += " ";
                           }
                           str2 += "->";
                           if (this.dungeon[x][z].getDirection()[2] == 0) {
                              str2 += "]";
                           } else {
                              str2 += " ";
                           }
                        } else {
                           if (this.dungeon[x][z].getDirection()[0] == 0) {
                              str2 += "[";
                           } else {
                              str2 += " ";
                           }
                           if (x == characterX && z == characterY) {
                              str2 += "x";
                           } else {
                              str2 += " ";
                           }
                           if (this.dungeon[x][z].getDirection()[2] == 0) {
                              str2 += "]";
                           } else {
                              str2 += " ";
                           }
                        }
                     } else {
                        if (this.dungeon[x][z].getDirection()[3] == 0) {
                           str3 += " - ";
                        } else {
                           str3 += "   ";
                        }
                     }
                  } else {
                     if (y == 0) {
                        str1 += "   ";
                     } else if (y == 1) {
                        str2 += "   ";
                     } else {
                        str3 += "   ";
                     }
                  }
            }
         }
         finalStr += str1 + "\n"  + str2 + "\n" + str3 + "\n";
      }
      return finalStr;
   }

   public boolean isValidDirection(String direction)
   {
      switch(direction)
      {
         case "right":
            return dungeon[characterX][characterY].getDirection()[2] == 1;
         case "left":
            return dungeon[characterX][characterY].getDirection()[0] == 1 && characterY != 0;
         case "up":
            return dungeon[characterX][characterY].getDirection()[1] == 1;
         case "down":
            return dungeon[characterX][characterY].getDirection()[3] == 1;
      }
      return false;
   }

   public boolean endOfMap()
   {
      return characterX == dungeon.length - 1 && characterY == dungeon[0].length - 1;
   }

   public void updateCharacter(String direction)
   {
      switch(direction)
      {
         case "right":
            characterY++;
            break;
         case "left":
            characterY--;
            break;
         case "up":
            characterX--;
            break;
         case "down":
            characterX++;
            break;
      }
   }
   
   public String toString()
   {
      String finalStr = "";
      String str1 = "", str2 = "", str3 = "";
      boolean ifUsed = false;
      for(int x = 0; x < dungeon.length; x++)
      {
         str1 = "";
         str2 = ""; 
         str3 = "";
         for(int z = 0; z < dungeon[0].length; z++)
         {
            for(int y = 0; y < 3; y++)
            {
               ifUsed = this.dungeon[x][z].isUsed();
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
                  if(x == this.dungeon.length - 1 && z == this.dungeon[x].length - 1)
                  {
                     if(this.dungeon[x][z].getDirection()[0] == 0)
                     {
                        str2 += "[";
                     }
                     else
                     {
                        str2 += " ";
                     }
                     str2 += "x";
                     if(this.dungeon[x][z].getDirection()[2] == 0)
                     {
                        str2 += "]";
                     }
                     else
                     {
                        str2 += " ";
                     }
                  }
                  else
                  {
                     if(this.dungeon[x][z].getDirection()[0] == 0)
                     {
                        str2 += "[";
                     }
                     else
                     {
                        str2 += " ";
                     }
                     if(ifUsed)
                     {
                        str2 += "x";
                     }
                     else
                     {
                        str2 += " ";
                     }
                     if(this.dungeon[x][z].getDirection()[2] == 0)
                     {
                        str2 += "]";
                     }
                     else
                     {
                        str2 += " ";
                     }
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

   public boolean isAtEnd()
   {
      return this.characterY == this.dungeon.length - 1 && this.characterX == this.dungeon[0].length - 1;

   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      GenerateDungeon that = (GenerateDungeon) o;

      if (size != that.size) return false;
      if (xStart != that.xStart) return false;
      if (yStart != that.yStart) return false;
      if (xEnd != that.xEnd) return false;
      if (yEnd != that.yEnd) return false;
      if (characterX != that.characterX) return false;
      if (characterY != that.characterY) return false;
      if (!Arrays.deepEquals(dungeon, that.dungeon)) return false;
      if (!Arrays.equals(startEnd, that.startEnd)) return false;

      return true;
   }

   @Override
   public int hashCode()
   {
      int result = Arrays.deepHashCode(dungeon);
      result = 31 * result + size;
      result = 31 * result + Arrays.hashCode(startEnd);
      result = 31 * result + xStart;
      result = 31 * result + yStart;
      result = 31 * result + xEnd;
      result = 31 * result + yEnd;
      result = 31 * result + characterX;
      result = 31 * result + characterY;
      return result;
   }
}