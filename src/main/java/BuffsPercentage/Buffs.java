package BuffsPercentage;

/**
 * Created by Michael on 5/21/2016.
 */
public class Buffs
{
    private double buff;
    private int rounds;
    private String source;

    public Buffs(double buff, int rounds, String source)
    {
        this.buff = buff;
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

    public void replace(double buff, int rounds)
    {
        this.buff = buff;
        this.rounds = rounds;
    }

    public double buffAmount()
    {
        return buff - 1.0;
    }
}
