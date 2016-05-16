package Characters;

import AttackAndDefendBehavior.*;

/**
 * Created by gm14793 on 5/9/16.
 */
public class Rogue extends A_Hero implements I_Attack, I_Defend
{

    public Rogue(String newName, int newHealth, int newStrength, int newDexterity, int newSpeed, int newArmor)
    {
        super(newName, newHealth, newStrength, newDexterity, newSpeed, newArmor);
    }


    public void performAttack(A_Character toAttack)
    {

    }

    public void performDefense()
    {

    }


}
