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
    List<Buffs> regen;
    List<Buffs> poison;
    String name;

    public BuffsManager(String name)
    {
        attack = new ArrayList<Buffs>();
        damage = new ArrayList<Buffs>();
        regen = new ArrayList<Buffs>();
        poison = new ArrayList<Buffs>();
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

    public void addRegenBuff(double buff, int rounds, String source)
    {
        searchList(buff, rounds, source, regen);
    }

    public void addPoisonDebuff(double debuff, int rounds, String source)
    {
        searchList(debuff, rounds, source, poison);
    }

    private void searchList(double buff, int rounds, String source, List<Buffs> list)
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
        decrementList(attack);

        decrementList(damage);

        decrementList(regen);

        decrementList(poison);
    }

    public void decrementBad()
    {
        decrementList(poison);
    }

    private void decrementList(List<Buffs> list)
    {
        List<Buffs> checks = new ArrayList<Buffs>();

        for(Buffs aBuff : list)
        {
            if(aBuff.decrement())
            {
                System.out.println(name + "'s buff/debuff has ended from " + aBuff.getSource());
                checks.add(aBuff);
            }
        }

        for(Buffs aBuff : checks)
        {
            list.remove(aBuff);
        }
    }

    public double getDamageBuffAmount()
    {
        return getAmount(damage,1.0);
    }

    public double getAttackBuffAmount()
    {
        return getAmount(attack,1.0);
    }

    public double getRegenAmount()
    {
        double total = getAmount(regen, 0.0);
        return total;
    }

    public double getPoisonAmount()
    {
        double total = getAmount(poison, 0.0);
        return total;
    }

    private double getAmount(List<Buffs> list, double total)
    {
        for(Buffs aBuff : list)
        {
            total += 1.0 + aBuff.buffAmount();
        }
        return total;
    }

    public boolean badCondition()
    {
        return poison.size() > 0;
    }

    public void cleanBuffs()
    {
        attack.clear();
        damage.clear();
        regen.clear();
        poison.clear();
    }
}
