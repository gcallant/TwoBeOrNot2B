package Logging;

import Utilities.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Grant Callant on 5/14/2016.
 *
 * @author Grant Callant
 */
public class LoggingManager
{
/*	private static final String  SEPARATOR          = OSUtil.getSeparator();
	private static final String  LOGGER_PROPERTIES  = SEPARATOR + "src" + SEPARATOR + "main" +
			                                                    SEPARATOR + "resources" + SEPARATOR + "log4j.properties";
	private static       Logger  logger             = null;
	private static       boolean isLoggerConfigured = false;
	private              File    EXTERNAL_DIRECTORY = OSUtil.getExternalDirectory();

	public LoggingManager(Class callingClass)
	{
		if(isLoggerConfigured)
		{
			logger = LoggerFactory.getLogger(callingClass);
		}
		else
		{
			configureLogger();
		}
	}

	public LoggingManager(String callingClass)
	{
		if(isLoggerConfigured)
		{
			logger = LoggerFactory.getLogger(callingClass);
		}
		else
		{
			configureLogger();
		}
	}

	public static boolean isConfigured()
	{
		return isLoggerConfigured;
	}

	public static Logger getLogger()
	{
		return logger;
	}

	public List<String> readLoggerProperties(File cwd) throws FileNotFoundException
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

	private void configureLogger()
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
		isLoggerConfigured = true;
	}

	private String convertSeparators(String[] cwd)
	{
		String directory = "";
		for(int i = 0; i < cwd.length; i++)
		{
			directory += cwd[i] + "\\\\";
		}
		return directory;
	}

	private void writePropertiesToFile(List<String> loggerProperties, File cwd) throws FileNotFoundException
	{
		PrintStream printStream = new PrintStream(cwd.getAbsolutePath() + LOGGER_PROPERTIES);

		for(int i = 0; i < loggerProperties.size(); i++)
		{
			printStream.println(loggerProperties.get(i));
		}
		printStream.close();
	}

	private void injectExternalDirectoryIntoProperties(List<String> loggerProperties, String directoryToSave)
	{
		String line = loggerProperties.get(1);
		loggerProperties.remove(1);
		int location = line.indexOf('=') + 1;
		line = line.substring(0, location);
		line += directoryToSave;
		loggerProperties.add(1, line);
	}*/
}
