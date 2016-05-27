package GameState;

import Database.DatabaseManager;
import Database.DatabaseManagerException;
import Mediator.Mediator;
import StringTester.TestString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by Grant Callant on 5/25/16.
 */
public class SaveGame implements I_State
{
	private Mediator mediator = null;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	SaveGame(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}

	@Override
	public String display()
	{
		return "\nNow saving your game.\nPlease do not quit game or turn of your system";
	}

	@Override
	public I_State execute()
	{
		char [] validInputs = {'y', 'Y', 'n', 'N'};

		DatabaseManager databaseManager = new DatabaseManager();
		try
		{
			databaseManager.saveCharacters(mediator);
			databaseManager.saveInventory(mediator);
		}
		catch(SQLException e)
		{
			logger.debug("Tried to save game- had a sql exception ", e);
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to save game- had a DatabaseManager Exception ", e);
		}

		databaseManager.closeConnection();

		System.out.println("\nThank you. Your game is now saved. Would you like to exit? [y/N]:");
		char confirmQuit = TestString.ensureChar(validInputs);

		if(confirmQuit == 'y' || confirmQuit == 'Y')
		{
			return new QuitGame(mediator);
		}
		else
		{
			return new NewMap(mediator);
		}
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}
