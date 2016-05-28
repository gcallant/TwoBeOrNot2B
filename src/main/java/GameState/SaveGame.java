package GameState;

import Database.DatabaseManager;
import Exceptions.DatabaseManagerException;
import Utilities.TestString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
		boolean saved = false;

		DatabaseManager databaseManager = new DatabaseManager();
		try
		{
			logger.info("Attempting to save characters");
			databaseManager.saveCharacters(mediator);
			logger.info("Attempting to save Inventory");
			databaseManager.saveInventory(mediator);
			saved = true;
		}
		catch(SQLException e)
		{
			logger.debug("Tried to save game- had a SQL Exception ", e);
			logger.info("Going to try re-saving");
			saved = resave(databaseManager);
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to save game- had a DatabaseManager Exception ", e);
			logger.info("Going to try re-saving");
			saved = resave(databaseManager);
		}
		catch(IOException e)
		{
			logger.debug("Tried to save game- had an IOException ", e);
			logger.info("Going to try re-saving");
			saved = resave(databaseManager);
		}

		databaseManager.closeConnection();

		if(! saved)
		{
			System.out.println("\nSorry your game was not saved- would you like to try again? [Y/n]:");
			char confirmNotSaved = TestString.ensureChar(validInputs);

			if(confirmNotSaved == 'y' || confirmNotSaved == 'Y')
			{
				return new SaveGame(mediator);
			}
			else
			{
				System.out.println("\nOkay. Would you like to exit? [y/N]:");
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
		}

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

	private boolean resave(DatabaseManager databaseManager)
	{
		try
		{
			databaseManager.saveCharacters(mediator);
			databaseManager.saveInventory(mediator);
		}
		catch(IOException e)
		{
			logger.debug("Tried to resave game- failed", e);
			System.out.println("Save game failed- game is *not* saved.");
			return false;
		}
		catch(SQLException e)
		{
			logger.debug("Tried to resave game- failed", e);
			System.out.println("Save game failed- game is *not* saved.");
			return false;
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to resave game- failed", e);
			System.out.println("Save game failed- game is *not* saved.");
			return false;
		}
		return true;
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}
