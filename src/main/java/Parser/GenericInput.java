package Parser;

import com.google.inject.Inject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Grant Callant on 4/29/16. TwoBeOrNot2B
 */
public class GenericInput
{
	char[]            dataToRead;
	ArrayList<String> dataToSave;
	private Reader input;
	private int readSize = 100;

	@Inject
	public GenericInput()
	{

	}

	public ArrayList<String> getDataToSave()
	{
		return this.dataToSave;
	}

	public void attach(InputStream inputStream)
	{
		input = new BufferedReader(new InputStreamReader(inputStream));
	}

	public void read()
	{
		int bytesRead = 0;
		while(true)
		{
			try
			{
				dataToRead = new char[readSize];
				dataToSave = new ArrayList<>(readSize);

				do
				{
					bytesRead = input.read(dataToRead);
					dataToSave.add(Arrays.toString(dataToRead));
				}
				while(bytesRead != - 1);
				break;

			}
			catch(IOException ioE)
			{
				ioE.printStackTrace();
			}
			catch(OutOfMemoryError outOfMemoryError)
			{
				readSize /= 10;
			}
		}

	}
}
