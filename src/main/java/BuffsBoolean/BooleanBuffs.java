package BuffsBoolean;

/**
 * Created by Michael on 5/22/2016.
 */
public class BooleanBuffs
{
    private int rounds;
    private String source;

    public BooleanBuffs(int rounds, String source)
    {
        this.rounds = rounds;
        this.source = source;
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

    public void replace(int rounds)
    {
        this.rounds = rounds;
    }
}
