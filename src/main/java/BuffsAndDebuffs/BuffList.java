package BuffsAndDebuffs;

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
                System.out.println(source + "'s " + toString() + " has been refreshed on " + name);
                aBuff.replace(buff, rounds);
                found = true;
            }
        }
        if(!found)
        {
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
                System.out.println(name + "'s " + toString() + " has ended from " + aBuff.getSource());
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
}
