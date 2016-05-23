package DungeonGeneration;

import java.util.Arrays;
import java.util.Random;

public class DungeonTile
{
	private int[]   direction;
	private boolean used;

	public DungeonTile()
	{
		this.used = false;
		this.direction = new int[4];
	}

	public void setUsed()
	{
		used = true;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DungeonTile that = (DungeonTile) o;

		if (used != that.used) return false;
		if (!Arrays.equals(direction, that.direction)) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = Arrays.hashCode(direction);
		result = 31 * result + (used ? 1 : 0);
		return result;
	}
}