package PartyManagement;

import Characters.A_Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 5/27/2016.
 */
public class BattleManager
{
    private Party heroes;
    private Party monsters;
    private List<A_Character> battle;
    private A_Character current;
    private int currentIndex;

    public BattleManager(Party heroes, Party monsters)
    {
        this.heroes = heroes;
        this.monsters = monsters;
        battle = new ArrayList<A_Character>();
        currentIndex = 0;

        for(A_Character character : heroes)
        {
            character.generateInitiative();
            battle.add(character);
        }

        for(A_Character character : monsters)
        {
            character.generateInitiative();
            battle.add(character);
        }

        Collections.sort(battle, new InitiativeSort());
        current = battle.get(0);
    }

    public boolean takeAction()
    {
        if(heroes.contains(current) && current.isSummon())
        {
            if(current.takeAction(monsters, heroes))
            {
                moveIndex();
                return false;
            }
        }
        else
        {
            if(current.takeAction(heroes, monsters))
            {
                moveIndex();
                return false;
            }
        }

        moveIndex();
        return true;
    }

    private void moveIndex()
    {
        currentIndex = (currentIndex + 1)%battle.size();

        while(battle.get(currentIndex).getDefeated())
        {
            currentIndex = (currentIndex + 1)%battle.size();
        }
        current = battle.get(currentIndex);
    }

    public void addMembers()
    {
        for(A_Character character : heroes)
        {
            if(!battle.contains(character))
            {
                character.generateInitiative();
                battle.add(character);
            }
        }

        for(A_Character character : monsters)
        {
            if(!battle.contains(character))
            {
                character.generateInitiative();
                battle.add(character);
            }
        }

        Collections.sort(battle, new InitiativeSort());

        currentIndex = battle.indexOf(current);
    }

    public boolean heroesWon()
    {
        for(A_Character character : monsters)
        {
            if(!character.getDefeated() && !character.isSummon())
            {
                return false;
            }
        }

        return true;
    }

    public boolean heroesLost()
    {
        for(A_Character character : heroes)
        {
            if(!character.getDefeated() && !character.isSummon())
            {
                return false;
            }
        }

        return true;
    }

    public void startOfTurn()
    {
        heroes.sortDefeated();
        monsters.sortDefeated();
        addMembers();
    }

    public void bossTurn(A_Character character)
    {
        character.takeAction(heroes, monsters);
    }
}
