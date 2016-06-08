package BuffsCharacter;

import Characters.A_Character;

/**
 * Created by Michael on 5/27/2016.
 */
public class CharacterBuffs
{
    private int rounds;
    private String source;
    private A_Character contributor;
    private double amount;


    public CharacterBuffs(A_Character contributor, double amount, int rounds, String source)
    {
        this.rounds = rounds;
        this.source = source;
        this.contributor = contributor;
        this.amount = amount;
    }

    public boolean decrement()
    {
        rounds--;
        if(rounds == 0)
        {
            return true;
        }
        return false;
    }

    public String getSource()
    {
        return source;
    }

    public void replace(A_Character contributor, double amount, int rounds)
    {
        this.amount = amount;
        this.rounds = rounds;
    }

    public A_Character getContributor()
    {
        return contributor;
    }

    public double getAmount()
    {
        return amount;
    }
}
