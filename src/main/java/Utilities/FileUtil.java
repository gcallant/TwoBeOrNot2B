package Utilities;

import java.util.Scanner;

/**
 * Created by Grant Callant on 5/26/2016.
 *
 * @author Grant Callant
 */
public class FileUtil
{
	public static int countFileLines(Scanner fin)
	{
		if(fin == null)
		{
			return 0;
		}
		int count = 0;
		while(fin.hasNext())
		{
			count++;
			fin.nextLine();
		}
		//		fin.close();
		return count;
	}
}
