package BuffsAndDebuffs;

/**
 * Created by Michael on 5/23/2016.
 */
public class NecromancerConditions extends Conditions
{
    private String name;

    public NecromancerConditions(String name)
    {
        super(name);
        this.name = name;
    }

    public void giveStunnedDebuff(int rounds, String source)
    {
        System.out.println(name + " is immune to stun!");
    }

    public void giveBleedDebuff(double percentage, int rounds, String source)
    {
        System.out.println(name + " is immune to bleed!");
    }

    public void givePoisonDebuff(double percentage, int rounds, String source)
    {
        System.out.println(name + " is immune to poison!");
    }
}
