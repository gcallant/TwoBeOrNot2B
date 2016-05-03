package takeAction;

/**
 * Created by gm14793 on 4/28/16.
 */
public class DummyCharacter
{
    private Weapon weap;
    private int health;
    private int power;
    private int armor;



    public DummyCharacter(int h, int p, int ac)
    {
        int health = h;
        int power = p;
        int armor = ac;
    }


    public void setWeapon(Weapon weapon)
    {
        this.weap = weapon;
    }


    public void attack()
    {

    }

}
