package DungeonGeneration;

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

	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof DungeonTile))
		{
			return false;
		}

		DungeonTile thatTile = (DungeonTile)obj;

		//This will test if the arrays for direction are the same
		if (this.direction.length != thatTile.direction.length)
		{
			return false;
		}

		for (int i = 0; i < this.direction.length; i++)
		{
			if (this.direction[i] != thatTile.direction[i])
			{
				return false;
			}
		}
		boolean equalDirection = true;

		return equalDirection && this.used == thatTile.used;

	}
}