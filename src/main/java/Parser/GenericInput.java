package Parser;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Grant Callant on 4/29/16. TwoBeOrNot2B
 */
public class GenericInput
{
	char[]            dataToRead;
	ArrayList<char[]> dataToSave;
	private Reader input;
	private int readSize = 100;

	@Inject
	public GenericInput()
	{

	}

	public ArrayList<char[]> getDataToSave()
	{
		return this.dataToSave;
	}

	public final void attach(InputStream inputStream)
	{
		input = new BufferedReader(new InputStreamReader(inputStream));
	}

	public final void read()
	{
		int bytesRead = 0, i = 0;
		while(true)
		{
			try
			{
				dataToRead = new char[readSize];
				dataToSave = new ArrayList<char[]>(readSize);

				do
				{
					bytesRead = input.read(dataToRead);
					dataToSave.add(dataToRead);
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

	@NotNull
	@Override
	public final String toString()
	{
		final StringBuilder sb = new StringBuilder("GenericInput{");
		sb.append("dataToSave=\n");
		for(char[] c : dataToSave)
		{
			sb.append(c);
		}
		sb.append('}');
		return sb.toString();
	}

	public void closeInputStream()
	{
		try
		{
			input.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
