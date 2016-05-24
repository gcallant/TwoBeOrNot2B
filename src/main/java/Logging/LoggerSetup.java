package Logging;

import Utilities.OSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Grant Callant on 5/23/2016.
 *
 * @author Grant Callant
 */
public class LoggerSetup
{
	private static final String SEPARATOR          = OSUtil.getSeparator();
	private static final String LOGGER_PROPERTIES  = SEPARATOR + "src" + SEPARATOR + "main" +
			                                                   SEPARATOR + "resources" + SEPARATOR + "log4j.properties";
	private static final File   EXTERNAL_DIRECTORY = OSUtil.getExternalDirectory();

	private LoggerSetup()
	{

	}

	private static void injectGenericIntoProperties(List<String> loggerProperties)
	{
		String line = loggerProperties.get(1);
		loggerProperties.remove(1);
		int location = line.indexOf('=') + 1;
		line = line.substring(0, location);
		line += "null";
		loggerProperties.add(1, line);

		line = loggerProperties.get(5);
		loggerProperties.remove(5);
		location = line.indexOf('=') + 1;
		line = line.substring(0, location);
		line += "prelog.ignore";
		loggerProperties.add(5, line);
	}

	public static List<String> readLoggerProperties(File cwd) throws FileNotFoundException
	{
		File loggerFile = new File(cwd.getAbsolutePath() + LOGGER_PROPERTIES);
		Scanner input = new Scanner(loggerFile);
		List<String> loggerProperties = new ArrayList<>(9);
		String line = "";

		while(input.hasNext())
		{
			line = input.nextLine();
			loggerProperties.add(line);
		}
		input.close();
		return loggerProperties;
	}

	public static void configureLogger()
	{
		File cwd = OSUtil.getCurrentDirectory();
		String directoryToSave = "";
		try
		{
			List<String> loggerProperties = readLoggerProperties(cwd);
			if(SEPARATOR.compareTo("\\\\") == 0)
			{
				directoryToSave = convertSeparators(EXTERNAL_DIRECTORY.getAbsolutePath().split("\\\\"));
			}
			else
			{
				directoryToSave = EXTERNAL_DIRECTORY.getAbsolutePath();
			}
			injectExternalDirectoryIntoProperties(loggerProperties, directoryToSave);
			writePropertiesToFile(loggerProperties, cwd);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		LoggingManager.setConfigured(true);
	}

	private static String convertSeparators(String[] cwd)
	{
		String directory = "";
		for(int i = 0; i < cwd.length; i++)
		{
			if(i == cwd.length - 1)
			{
				directory += cwd[i];
			}
			else
			{
				directory += cwd[i] + "\\\\";
			}
		}
		return directory;
	}

	private static void writePropertiesToFile(List<String> loggerProperties, File cwd) throws FileNotFoundException
	{
		PrintStream printStream = new PrintStream(cwd.getAbsolutePath() + LOGGER_PROPERTIES);

		for(int i = 0; i < loggerProperties.size(); i++)
		{
			printStream.println(loggerProperties.get(i));
		}
		printStream.close();
	}

	private static void injectExternalDirectoryIntoProperties(List<String> loggerProperties, String directoryToSave)
	{
		String line = loggerProperties.get(1);
		loggerProperties.remove(1);
		int location = line.indexOf('=') + 1;
		line = line.substring(0, location);
		line += directoryToSave;
		loggerProperties.add(1, line);

		line = loggerProperties.get(5);
		loggerProperties.remove(5);
		location = line.indexOf('=') + 1;
		line = line.substring(0, location);
		line += "${log}" + SEPARATOR + "game.log";
		loggerProperties.add(5, line);
	}

	public static void cleanLoggerProperties()
	{
		File cwd = OSUtil.getCurrentDirectory();
		String directoryToSave = "";
		try
		{
			List<String> loggerProperties = readLoggerProperties(cwd);

			injectGenericIntoProperties(loggerProperties);
			writePropertiesToFile(loggerProperties, cwd);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
