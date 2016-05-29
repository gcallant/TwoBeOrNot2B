package Exceptions;

import PartyManagement.Inventory;

/**
 * Created by Grant Callant on 5/21/2016.
 *
 * @author Grant Callant
 */
public class DatabaseManagerException extends Exception
{
	public DatabaseManagerException(String message)
	{
		//No Body
	}

	public DatabaseManagerException()
	{
		//No Body
	}

	public DatabaseManagerException(Throwable cause)
	{
		//No Body
	}

	public DatabaseManagerException(String message, Throwable cause)
	{
		//No Body
	}

	public DatabaseManagerException(String message, String hero)
	{
		//No Body
	}

	public DatabaseManagerException(String message, Inventory inventory)
	{
		//No Body
	}

	public DatabaseManagerException notClosable(String message, Throwable cause)
	{
		return new DatabaseManagerException(message, cause);
	}

	public DatabaseManagerException notSaved(String message, String hero)
	{
		return new DatabaseManagerException(message, hero);
	}

	public DatabaseManagerException notSaved(String message, Inventory inventory)
	{
		return new DatabaseManagerException(message, inventory);
	}

	public DatabaseManagerException notLoaded(String message, Inventory inventory)
	{
		return new DatabaseManagerException(message, inventory);
	}
}
