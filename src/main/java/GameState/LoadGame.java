package GameState;

import Database.DatabaseManager;
import Exceptions.DatabaseManagerException;
import Utilities.Display;
import Utilities.TestString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Grant Callant on 5/25/16.
 */
public class LoadGame implements I_State
{
	private Mediator mediator = null;
	private Logger   logger   = LoggerFactory.getLogger(this.getClass());

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
		char[] validInputs = {'y', 'Y', 'n', 'N'};
		boolean loaded = false;

		DatabaseManager databaseManager = new DatabaseManager();
		try
		{
			logger.info("Attempting to load party now");
			databaseManager.loadParty(mediator);
			logger.info("Party loaded- attempting to load inventory");
			databaseManager.loadInventory(mediator);
			loaded = true;
		}
		catch(SQLException e)
		{
			logger.debug("Tried to load game- had a SQL Exception ", e);
			Display.displayMessage("No games to load");
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to load game- had a DatabaseManager Exception ", e);
			logger.info("Going to try re-loading");
			loaded = retryLoad(databaseManager);
		}
		catch(IOException e)
		{
			logger.debug("Tried to load game- had an IOException ", e);
			logger.info("Going to try re-loading");
			loaded = retryLoad(databaseManager);
		}
		catch(ClassNotFoundException e)
		{
			logger.debug("Tried to load game- had a Class Not Found Exception ", e);
			logger.info("Going to try re-loading");
			loaded = retryLoad(databaseManager);
		}
		databaseManager.closeConnection();

		if(! loaded)
		{
			Display.displayMessage("\nSorry your game was not loaded- would you like to try again? [Y/n]:");
			char confirmNotSaved = TestString.ensureChar(validInputs);

			if(confirmNotSaved == 'y' || confirmNotSaved == 'Y')
			{
				return new LoadGame(mediator);
			}
			else
			{
				return new MainMenu(mediator);
			}
		}
		else
		{
			logger.info("Successfully loaded game- now to menu");
			Display.displayMessage("Thank you- your game has been loaded, \npress enter to continue");
			TestString.enterInput();
			return new NewMap(mediator);
		}
	}

	private boolean retryLoad(DatabaseManager databaseManager)
	{
		try
		{
			logger.info("In retry load- last chance to re-load party now");
			databaseManager.loadParty(mediator);
			logger.info("In retry load- last chance to re- load inventory now");
			databaseManager.loadInventory(mediator);
			return true;
		}
		catch(SQLException e)
		{
			logger.debug("Tried to load game- had a SQL Exception ", e);
			Display.displayMessage("Sorry, there was an error loading your game");
			return false;
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to load game- had a DatabaseManager Exception ", e);
			Display.displayMessage("Sorry, there was an error loading your game");
			return false;
		}
		catch(IOException e)
		{
			logger.debug("Tried to load game- had an IOException ", e);
			Display.displayMessage("Sorry, there was an error loading your game");
			return false;
		}
		catch(ClassNotFoundException e)
		{
			logger.debug("Tried to load game- had a Class Not Found Exception ", e);
			Display.displayMessage("Sorry, there was an error loading your game");
			return false;
		}
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}
