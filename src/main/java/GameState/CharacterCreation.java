package GameState;

import Characters.A_Character;
import PartyManagement.CreateMember;
import PartyManagement.Party;
import Utilities.Display;
import Utilities.FileUtil;
import Utilities.OSUtil;
import Utilities.TestString;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Created by Michael on 5/12/2016.
 */

public class CharacterCreation implements I_State
{
	private static final String SEPARATOR = OSUtil.getSeparator();
	private Mediator          mediator;
	private List<A_Character> party;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	public CharacterCreation(Mediator mediator)
	{
		this.mediator = mediator;
		party = new ArrayList<A_Character>();
		this.mediator.receiveCurrentLevel(0);
	}

	@NotNull
	private List<String> readInNames() throws IOException
	{
		File cwd = OSUtil.getCurrentDirectory();
		InputStream randomNames = getClass().getResourceAsStream("/RandomNames.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(randomNames));
		String readLine = "";
		int lineCount = FileUtil.countFileLines(new Scanner(randomNames));
		List<String> readList = new ArrayList<>(lineCount);
		randomNames = getClass().getResourceAsStream("/RandomNames.txt");
		bufferedReader = new BufferedReader(new InputStreamReader(randomNames));


		while((readLine = bufferedReader.readLine()) != null)
		{
			readList.add(readLine);
		}
		return Collections.unmodifiableList(readList);
	}

	public boolean isEndOfGame()
	{
		return false;
	}

	public String display()
	{
		return "Choose an option\n1) Create a customized party\n2) Randomly generate a party\n3) Cancel";
	}

	@Override
	public I_State execute()
	{
		int command = TestString.ensureInt(3);
		switch(command)
		{
			case 1:
				if(createNewParty())
				{
					mediator.receiveParty(new Party(party));
					return new NewMap(mediator);
				}
				return new MainMenu(mediator);
			case 2:
				if(createRandomNewParty())
				{
					mediator.receiveParty(new Party(party));
					return new NewMap(mediator);
				}
			case 3:
				return new MainMenu(mediator);

			default:
				return new CharacterCreation(mediator);
		}
	}

    private boolean createNewParty()
    {
        String input = "";
        Display.displayMessage("You can choose four heroes! Choose wisely.");
        while(party.size() < 4)
        {
            Display.displayMessage("Choose character number " + (party.size() + 1) + ":");
	        A_Character toAdd = CreateMember.createMember(party);
	        if(toAdd != null)
            {
                party.add(toAdd);
            }
        }
        return CreateMember.confirm();
    }
	private boolean createRandomNewParty()
	{
		String input = "";
		List<String> randomNameList = null;
		try
		{
			randomNameList = readInNames();
		}
		catch(IOException e)
		{
			logger.debug("Tried to open & read in random name list", e);
		}
		while(party.size() < 4)
		{
			A_Character toAdd = CreateMember.createRandomMember(randomNameList);
			if(toAdd != null)
			{
				party.add(toAdd);
			}
			try
			{
				Thread.sleep(1); //We sleep 1ms to allow seed to change
			}
			catch(InterruptedException e)
			{
				logger.info("Sleep was interrupted during random party creation", e);
			}
		}
		displayRandomParty(party);
		return CreateMember.confirm();
	}

	private void displayRandomParty(List<A_Character> party)
	{
		Display.displayMessage("Here is your generated party!\n");
		for(A_Character hero : party)
		{
			System.out.println(hero.getName());
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) { return true; }
		if(! (o instanceof CharacterCreation)) { return false; }

		CharacterCreation that = (CharacterCreation) o;

		if(mediator != null ? ! mediator.equals(that.mediator) : that.mediator != null) { return false; }
		if(party != null ? ! party.equals(that.party) : that.party != null) { return false; }
		if(logger != null ? ! logger.equals(that.logger) : that.logger != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = mediator != null ? mediator.hashCode() : 0;
		result = 31 * result + (party != null ? party.hashCode() : 0);
		result = 31 * result + (logger != null ? logger.hashCode() : 0);
		return result;
	}
}
