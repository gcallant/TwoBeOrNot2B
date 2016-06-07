package BuffsPercentage;

import Utilities.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/22/2016.
 */
public abstract class BuffList
{
    private List<Buffs> list;
    private String name;

    public BuffList(String name)
    {
        list = new ArrayList<Buffs>();
        this.name = name;
    }

    public void addBuff(double buff, int rounds, String source)
    {
        searchList(buff, rounds, source);
    }

    private void searchList(double buff, int rounds, String source)
    {
        boolean found = false;

        for(Buffs aBuff : list)
        {
            if(aBuff.getSource().equals(source))
            {
                Display.displayMessage(source + "'s " + description() + " has been refreshed on " + name);
                aBuff.replace(buff, rounds);
                found = true;
            }
        }
        if(!found)
        {
            Display.displayMessage(name + toString());
            list.add(new Buffs(buff, rounds, source));
        }
    }

    public void decrementList()
    {
        List<Buffs> checks = new ArrayList<Buffs>();

        for(Buffs aBuff : list)
        {
            if(aBuff.decrement())
            {
                Display.displayMessage(name + "'s " + description() + " has ended from " + aBuff.getSource());
                checks.add(aBuff);
            }
        }

        for(Buffs aBuff : checks)
        {
            list.remove(aBuff);
        }
    }

    protected List<Buffs> getList()
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

    public abstract double getAmount();

    public abstract String description();
}
