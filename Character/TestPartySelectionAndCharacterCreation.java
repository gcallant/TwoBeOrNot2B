package Character;

import java.util.*;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class TestPartySelectionAndCharacterCreation
{
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		CharacterFactory characterFactory = new CharacterFactory();

		int choice = HelperMethods.selectPartyMenu(kb);
		HelperMethods.executePartyMenuChoice(kb, choice, characterFactory);
	}
}
