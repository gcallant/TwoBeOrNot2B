import Database.DatabaseManager;
import GameState.StateBase;
import Logging.LoggingManager;
import Utilities.OSException;
import Utilities.OSUtil;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Michael on 5/6/2016.
 */
public class RunGameSample
{
    private static final String GAME_NAME          = "DungeonCrawler";
    private static       File   EXTERNAL_DIRECTORY = null;

    public static void main(String[] args)
    {
        createExternalDirectory();
        LoggingManager loggingManager = new LoggingManager("RunGameSample");




        StateBase gameState = new StateBase();
        Scanner kb = new Scanner(System.in);

        while(gameState.isNotEnd())
        {
            System.out.println(gameState.displayCurrentState());
            gameState.executeCurrentState();
        }
        DatabaseManager database = new DatabaseManager();
      //  database.closeConnection();
    }

    private static void createExternalDirectory()
    {
        try
        {
            File parent = OSUtil.getParentDirectory();
            EXTERNAL_DIRECTORY = OSUtil.createNewDirectory(parent, GAME_NAME);
            OSUtil.setExternalDirectory(EXTERNAL_DIRECTORY);

        }
        catch(OSException e)
        {
            e.printStackTrace();
            System.out.println("Could not make new directory- program must exit");
            System.exit(- 1);
        }
    }
}
