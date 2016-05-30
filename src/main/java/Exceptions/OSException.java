package Exceptions;

/**
 * Created by Grant Callant on 5/14/2016.
 *
 * @author Grant Callant
 */

public class OSException extends Exception
{
	public OSException(String message)
	{
		super(message);
	}

	public OSException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public OSException(Throwable cause)
	{
		super(cause);
	}

	public OSException()
	{
		super();
	}

	public OSException writeException()
	{
		return new OSException();
	}
}
