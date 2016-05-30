package GameState;

import Database.DatabaseManager;
import Exceptions.DatabaseManagerException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Grant Callant on 5/25/16.
 */
public class LoadGame implements I_State
{
	private Mediator mediator = null;

	public LoadGame(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}

	@Override
	public String display()
	{
		return "\nPlease wait while we retrieve your saved game.\n";
	}

	@Override
	public I_State execute()
	{
		DatabaseManager databaseManager = new DatabaseManager();
		try
		{
			databaseManager.loadParty(mediator);
			databaseManager.loadInventory(mediator);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(DatabaseManagerException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return new NewMap(mediator);
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}
