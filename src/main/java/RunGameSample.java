import Database.DatabaseManager;
import GameState.StateBase;
import Utilities.Display;
import Utilities.OSException;
import Utilities.OSUtil;
import org.jetbrains.annotations.NotNull;

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
        StateBase gameState = new StateBase();
        Scanner kb = new Scanner(System.in);

        while(gameState.isNotEnd())
        {
            Display.displayMessage(gameState.displayCurrentState());
            gameState.executeCurrentState();
        }
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
//            Display.displayMessage("Could not make new directory- program must exit");
//            System.exit(- 1);
//        }
//    }
}
