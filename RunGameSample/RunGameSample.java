package RunGameSample;

import DungeonGeneration.*;
import StateExample.StateBase;

import java.util.*;

/**
 * Created by Michael on 5/6/2016.
 */
public class RunGameSample
{
    public static void main(String[] args)
    {
        StateBase gameState = new StateBase();
        Scanner kb = new Scanner(System.in);

        while(!gameState.exitGame())
        {
            System.out.println(gameState.display());
            gameState.execute(kb.nextLine());
        }
    }
}
