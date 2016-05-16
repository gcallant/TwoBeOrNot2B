package AttackAndDefendBehavior;
import Characters.*;
/**
 * Created by SaraPage on 4/29/2016.
 */
public class RangedAttack implements I_Attack
{
	public RangedAttack()
	{
	}

	@Override
	public void performAttack(A_Character toAttack)
	{
		System.out.println("The character performs Attack B");
	}
}
