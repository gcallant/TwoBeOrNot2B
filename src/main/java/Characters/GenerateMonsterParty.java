package Characters;

import Factories.MonsterFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SaraPage on 5/12/2016.
 */
public class GenerateMonsterParty
{
    MonsterFactory factory;
    private int numOfDifficultyLevels;
    private ArrayList<ArrayList<String>> listsOfMonsters;

    public GenerateMonsterParty()
    {
        factory = new MonsterFactory();

        numOfDifficultyLevels = 3;
        listsOfMonsters = new ArrayList<ArrayList<String>>(numOfDifficultyLevels);
        for(int i = 0; i < listsOfMonsters.size(); i++)
        {
            listsOfMonsters.add(new ArrayList<String>());
        }

        //Manually add one monster to each of the lists
        listsOfMonsters.get(0).add("Goblin");
        listsOfMonsters.get(1).add("Orc");
        listsOfMonsters.get(3).add("Ogre");
    }
    public Party generateEnemyParty(int indexForDifficulty)
    {
        //This is the ArrayList that we will eventually return - a party of 4 monsters
        int numOfEnemies = 4;
        ArrayList<A_Character> listOfParty = new ArrayList<A_Character>(numOfEnemies);

        //Obtain the ArrayList of the Monsters that we want to create our party
        if(indexForDifficulty >= listsOfMonsters.size())
        {
            indexForDifficulty = listsOfMonsters.size() - 1;
        }
        ArrayList<String> listToPullFrom = listsOfMonsters.get(indexForDifficulty);

        //This is just so that we can name the monsters correctly - wolf1, wolf2, Orc1, Orge1, etc
        int[] numOfEachMonster = new int[listToPullFrom.size()];
        for (int i = 0; i < numOfEachMonster.length; i++)
        {
            numOfEachMonster[i] = 1;
        }

        //We are randomly choosing the names of monsters from listToPullFrom
        //We create the monster first and then add it to the listOfParty
        //so that we can create a Party
        Random rand = new Random();
        int randomNum = 0;
        A_Character monster = null;
        for(int i = 0; i < numOfEnemies; i++)
        {
            randomNum = rand.nextInt(listToPullFrom.size());
            monster = factory.createMonster(listToPullFrom.get(randomNum) + " " + numOfEachMonster[randomNum]);
            numOfEachMonster[randomNum]++;

            listOfParty.add(monster);
        }

        return new Party(listOfParty);
    }
}
