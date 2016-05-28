import Database.DatabaseManager;
import GameState.StateBase;
import Utilities.OSException;
import Utilities.OSUtil;
import Utilities.PrintMenu;
import Utilities.TestString;
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
		StateBase gameState = new StateBase();
		Scanner kb = new Scanner(System.in);

		if(! shown())
		{
			showIntro();
		}
		else
		{
			System.out.println("Welcome back!");
		}

		while(gameState.isNotEnd())
		{
			System.out.println(gameState.displayCurrentState());
			gameState.executeCurrentState();
		}
	}

	private static void showIntro()
	{
		System.out.println(PrintMenu.welcome());
		System.out.println("Press enter to continue...");
		TestString.enterInput();
		System.out.println(PrintMenu.enjoy());
		System.out.println("Press enter to continue...");
		TestString.enterInput();
		System.out.println(PrintMenu.names());
		System.out.println("Press enter to continue...");
		TestString.enterInput();
		setFlagShown();
	}

	private static void setFlagShown()
	{
		try
		{
			OSUtil.createNewFile(new File(OSUtil.getCurrentDirectory().getAbsolutePath() + SEPARATOR + "src" + SEPARATOR +
					                                "main" + SEPARATOR + "resources" + SEPARATOR), "alreadyShown");
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

		shown = OSUtil.pathExists(OSUtil.getCurrentDirectory().getAbsolutePath() + SEPARATOR + "src" + SEPARATOR +
				                            "main" + SEPARATOR + "resources" + SEPARATOR + "alreadyShown");
		return shown;
	}

	//    @NotNull
	//    private static StateBase initialSetup()
	//    {
	//        createExternalDirectory();
	//        return new StateBase();
	//    }

	private static void cleanUp(DatabaseManager database)
	{
		database.closeConnection();
	}

	//    private static void createExternalDirectory()
	//    {
	//        try
	//        {
	//            File parent = OSUtil.getParentDirectory();
	//            EXTERNAL_DIRECTORY = OSUtil.createNewDirectory(parent, GAME_NAME);
	//            OSUtil.setExternalDirectory(EXTERNAL_DIRECTORY);
	//
	//        }
	//        catch(OSException e)
	//        {
	//            e.printStackTrace();
	//            System.out.println("Could not make new directory- program must exit");
	//            System.exit(- 1);
	//        }
	//    }
}
