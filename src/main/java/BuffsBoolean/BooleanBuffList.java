package BuffsBoolean;

import Utilities.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/22/2016.
 */
public abstract class BooleanBuffList
{

    private List<BooleanBuffs> list;
    private String name;

    public BooleanBuffList(String name)
    {
        list = new ArrayList<BooleanBuffs>();
        this.name = name;
    }

    public void addBuff(int rounds, String source)
    {
        searchList(rounds, source);
    }

    private void searchList(int rounds, String source)
    {
        boolean found = false;

        for(BooleanBuffs aBuff : list)
        {
            if(aBuff.getSource().equals(source))
            {
                Display.displayMessage(source + " has been refreshed on " + name);
                aBuff.replace(rounds);
                found = true;
            }
        }
        if(!found)
        {
            list.add(new BooleanBuffs(rounds, source));

        }
    }

    public void decrementList()
    {
        List<BooleanBuffs> checks = new ArrayList<BooleanBuffs>();

        for(BooleanBuffs aBuff : list)
        {
            if(aBuff.decrement())
            {
                Display.displayMessage(name + "'s " + description() + " has ended from " + aBuff.getSource());
                checks.add(aBuff);
            }
        }

        for(BooleanBuffs aBuff : checks)
        {
            Display.displayMessage(name + toString());
            list.remove(aBuff);
        }
    }

    protected List<BooleanBuffs> getList()
    {
        return list;
    }

    public int size()
    {
        return list.size();
    }

    public void clear()
    {
        list.clear();
    }

    public abstract boolean isInEffect();

    public abstract String description();
}
