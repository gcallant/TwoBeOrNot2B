import Database.DatabaseManager;
import Exceptions.OSException;
import GameState.StateBase;
import Utilities.Display;
import Utilities.OSUtil;
import Utilities.PrintMenu;
import Utilities.TestString;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Michael on 5/6/2016.
 */
public class RunGameSample
{
	private static final String GAME_NAME          = "DungeonCrawler";
	private static final String SEPARATOR          = OSUtil.getSeparator();
	private static final Logger logger             = LoggerFactory.getLogger("RunGameSample");
	private static       File   EXTERNAL_DIRECTORY = null;

    public static void main(String[] args)
    {
	    StateBase gameState = initialSetup();
	    Scanner kb = new Scanner(System.in);

		if(! shown())
		{
			showIntro();
		}
		else
		{
			Display.displayMessage("\nWelcome back!\n");
		}

	    while(gameState.isNotEnd())
        {
            Display.displayMessage(gameState.displayCurrentState());
            gameState.executeCurrentState();
        }
    }

	private static void showIntro()
	{
		Display.displayMessage(PrintMenu.welcome());
		Display.displayMessage("\nPress enter to continue...\n");
		TestString.enterInput();
		Display.displayMessage(PrintMenu.enjoy());
		Display.displayMessage("\nPress enter to continue...\n");
		TestString.enterInput();
		Display.displayMessage(PrintMenu.names());
		Display.displayMessage("\nPress enter to continue...\n");
		TestString.enterInput();
		setFlagShown();
	}

	private static void setFlagShown()
	{
		try
		{
			OSUtil.createNewFile(new File(EXTERNAL_DIRECTORY + SEPARATOR), "alreadyShown");
		}
		catch(OSException e)
		{
			logger.info("Couldn't set intro shown marker", e);
		}
		catch(IOException e)
		{
			logger.info("Couldn't set intro shown marker", e);
		}
	}

	private static boolean shown()
	{
		boolean shown = false;

		shown = OSUtil.pathExists(EXTERNAL_DIRECTORY + SEPARATOR + "alreadyShown");
		return shown;
	}

	@NotNull
	private static StateBase initialSetup()
	{
		createExternalDirectory();
		//		     new JFXPanel();
		return new StateBase();
	}

	private static void cleanUp(DatabaseManager database)
	{
		database.closeConnection();
	}

	private static void createExternalDirectory()
	{
		try
		{
			File parent = OSUtil.getCurrentDirectory();
			EXTERNAL_DIRECTORY = OSUtil.createNewDirectory(parent, GAME_NAME);
			OSUtil.setExternalDirectory(EXTERNAL_DIRECTORY);

		}
		catch(OSException e)
		{
			e.printStackTrace();
			Display.displayMessage("Could not make new directory- program must exit");
			System.exit(- 1);
		}
	}
}
