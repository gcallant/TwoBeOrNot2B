package PartyManagement;

import Characters.A_Character;
import Factories.MonsterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by SaraPage on 5/12/2016.
 */
public class GenerateMonsterParty
{
    private MonsterFactory factory;
    private int                numOfDifficultyLevels;
    private List<List<String>> listsOfMonsters;

    public GenerateMonsterParty()
    {
        factory = new MonsterFactory();

        numOfDifficultyLevels = 3;
        listsOfMonsters = new ArrayList<List<String>>(numOfDifficultyLevels);
        for(int i = 0; i < numOfDifficultyLevels; i++)
        {
            listsOfMonsters.add(new ArrayList<String>());
        }

        int[] numberOfGoblins = {10,2,2};
        int[] numberOfOrcs = {5, 5, 3};
        int[] numberOfOgres = {2, 4, 5};
        int[] numberOfUndeadClerics = {3, 10, 3};
        int[] numberOfWarChiefs = {1, 3, 4};
        int[] numberOfAssassins = {1, 5, 6};
        int[] numberOfDireWolfs = {2, 4, 8};
        int[] numberOfSkeletons = {3, 5, 7};
        int[] numberOfSaplings = {2, 4, 6};
        int[] numberOfSlimes = {2, 4, 3};

        //Manually add one monster to each of the lists
        addInValues("Goblin",numberOfGoblins);
        addInValues("Orc", numberOfOrcs);
        addInValues("Ogre", numberOfOgres);
        addInValues("Undead Cleric", numberOfUndeadClerics);
        addInValues("War Chief", numberOfWarChiefs);
        addInValues("Assassin", numberOfAssassins);
        addInValues("Dire Wolf", numberOfDireWolfs);
        addInValues("Skeleton", numberOfSkeletons);
        addInValues("Sapling", numberOfSaplings);
        addInValues("Slime", numberOfSlimes);
    }

    private void addInValues(String name, int[] numToAdd)
    {
        for(int x = 0; x < numToAdd.length; x++)
        {
            for(int y = 0; y < numToAdd[x]; y++)
            {
                listsOfMonsters.get(x).add(name);
            }
        }
    }

    public Party generateEnemyParty(int indexForDifficulty, int level, boolean normal)
    {
        //This is the ArrayList that we will eventually return - a party of 4 monsters
        int numOfEnemies = 4;
        List<A_Character> listOfParty = new ArrayList<A_Character>(numOfEnemies);

        //Obtain the ArrayList of the Monsters that we want to create our party
        if(indexForDifficulty > listsOfMonsters.size())
        {
            indexForDifficulty = listsOfMonsters.size();
        }
        List<String> listToPullFrom = listsOfMonsters.get(indexForDifficulty - 1);

        //We are randomly choosing the names of monsters from listToPullFrom
        //We create the monster first and then add it to the listOfParty
        //so that we can create a Party
        Random rand = new Random();
        int randomNum = 0, monsterCount = 0;
        String monsterType;
        A_Character monster = null;
        for(int i = 0; i < numOfEnemies; i++)
        {
            monsterCount = 1;
            randomNum = rand.nextInt(listToPullFrom.size());
            monsterType = listToPullFrom.get(randomNum);
            for(A_Character character : listOfParty)
            {
                if(character.getName().contains(monsterType))
                {
                    monsterCount++;
                }
            }
            monster = factory.createMonster(monsterType, listToPullFrom.get(randomNum) + " " + monsterCount, level, normal, indexForDifficulty);

            listOfParty.add(monster);
        }

        Party party = new Party(listOfParty);
        party.setFloorLevel(indexForDifficulty);

        return new Party(listOfParty);
    }
}
