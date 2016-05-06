package Parser;

import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Grant Callant on 4/29/16. TwoBeOrNot2B
 */
public class GenericInput
{
	private Reader input;

	@Inject
	public GenericInput()
	{

	}

	protected void attach(InputStream inputStream)
	{
		this.input = new BufferedReader(new InputStreamReader(inputStream));
	}
}
