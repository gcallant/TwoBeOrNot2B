package GameState;

import Utilities.Display;
import Utilities.OSUtil;
import Utilities.TestString;

/**
 * Created by Michael on 5/6/2016.
 */
public class MainMenu implements I_State
{
	private Mediator mediator;

	public MainMenu(Mediator mediator)
	{
		this.mediator = mediator;
	}

	public boolean isEndOfGame()
	{
		return false;
	}

	public String display()
	{
		return "Choose an option (number)\n1) Start a New Game \n2) Continue a Saved Game \n3) Quit";
	}

	public I_State execute()
	{
		int command = TestString.ensureInt(3);
		switch(command)
		{
			case 1:
				return checkForSavedGames();
			case 2:
				return new LoadGame(mediator);
			case 3:
				return new ExitGame(mediator);
			default:
				return new MainMenu(mediator);
		}
	}

	private I_State checkForSavedGames()
	{
		char[] validInputs = {'y', 'Y', 'n', 'N'};
		String savedGame = OSUtil.getExternalDirectory().getAbsolutePath() + OSUtil.getSeparator() + "DungeonCrawler.db";
		if(OSUtil.pathExists(savedGame))
		{
			Display.displayMessage("Are you sure you want to start a new game? If you have a previous saved game" +
					                         " it will be removed. [y/N]");

			char choice = TestString.ensureChar(validInputs);
			if(choice == 'y' || choice == 'Y')
			{
				Display.displayMessage("Just to confirm- you want to REMOVE your PREVIOUS saved game and start a NEW GAME? [y/N]");
				choice = TestString.ensureChar(validInputs);

				if(choice == 'y' || choice == 'Y')
				{
					OSUtil.deleteFile(savedGame);
					return new CharacterCreation(mediator);
				}
				else
				{
					return new MainMenu(mediator);
				}
			}
			else
			{
				return new MainMenu(mediator);
			}
		}
		return new CharacterCreation(mediator);
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof MainMenu)) { return false; }

		MainMenu mainMenu = (MainMenu) o;

		if(mediator != null ? ! mediator.equals(mainMenu.mediator) : mainMenu.mediator != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		return mediator != null ? mediator.hashCode() : 0;
	}
}
