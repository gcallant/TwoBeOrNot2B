package BuffsAndDebuffs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/21/2016.
 */
public class BuffsManager
{
    List<Buffs> attack;
    List<Buffs> damage;
    String name;

    public BuffsManager(String name)
    {
        attack = new ArrayList<Buffs>();
        damage = new ArrayList<Buffs>();
        this.name = name;
    }

    public void addAttackBuff(double buff, int rounds, String source)
    {
        searchList(buff, rounds, source, attack);
    }

    public void addDamageBuff(double buff, int rounds, String source)
    {
        searchList(buff, rounds, source, damage);
    }

    public void searchList(double buff, int rounds, String source, List<Buffs> list)
    {
        boolean found = false;

        for(Buffs aBuff : list)
        {
            if(aBuff.getSource().equals(source))
            {
                System.out.println(source + " has been refreshed on " + name);
                aBuff.replace(buff, rounds);
                found = true;
            }
        }
        if(!found)
        {
            list.add(new Buffs(buff, rounds, source));
        }
    }

    public void decrement()
    {
        for(Buffs aBuff : attack)
        {
            if(aBuff.decrement())
            {
                System.out.println(name + "'s attack has returned to normal from " + aBuff.getSource());
                attack.remove(aBuff);
            }
        }

        for(Buffs aBuff : damage)
        {
            if(aBuff.decrement())
            {
                System.out.println(name + "'s damage has returned to normal from " + aBuff.getSource());
                damage.remove(aBuff);
            }
        }
    }

    public double getDamageBuffAmount()
    {
        double total = 1.0;
        for(Buffs aBuff : damage)
        {
            total += aBuff.buffAmount();
        }
        return total;
    }

    public double getAttackBuffAmount()
    {
        double total = 1.0;
        for(Buffs aBuff : attack)
        {
            total += aBuff.buffAmount();
        }
        return total;
    }

    public void cleanBuffs()
    {
        attack = new ArrayList<Buffs>();
        damage = new ArrayList<Buffs>();
    }
}
