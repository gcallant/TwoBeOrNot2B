package Utilities;

import Logging.LoggingManager;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.FileSystems;

/**
 * Created by Grant Callant on 5/14/2016. A collection of static utilities that simplify working with the operating
 * system.
 *
 * @author Grant Callant
 */
public class OSUtil
{


	/**
	 * Method to get the parent directory of where the program is currently being run from.
	 *
	 * @return parent directory as a File
	 * @throws OSException if parent directory could not be opened
	 */

	private static final String         SEPARATOR          = FileSystems.getDefault().getSeparator();
	private static       File           EXTERNAL_DIRECTORY = null;
	private static       LoggingManager logger             = waitForLoggerConfiguration();

	@Nullable
	private static LoggingManager waitForLoggerConfiguration()
	{
		if(logger.isConfigured())
		{
			return logger = new LoggingManager("OSUtil");
		}
		return null;
	}

	public static File getParentDirectory() throws OSException
	{
		File cwd = new File(System.getProperty("user.dir"));

		if(logger.isConfigured())
		{
			logger.getLogger().info("Got Current directory {}", cwd);
		}
		File parent = new File(cwd.getParent());
		if(parent == null || parent.isFile())
		{
			throw new OSException("Couldn't get parent directory");
		}
		if(logger.isConfigured())
		{
			logger.getLogger().info("Got Parent directory {}", parent);
		}
		return parent;
	}

	public static File getCurrentDirectory()
	{
		File cwd = new File(System.getProperty("user.dir"));
		return cwd;
	}

	public static File getExternalDirectory()
	{
		return EXTERNAL_DIRECTORY;
	}

	public static void setExternalDirectory(File externalDirectory) throws OSException
	{
		if(externalDirectory != null)
		{
			//Can only be set to a directory in the parent directory- but can't be the parent or the cwd
			if(! externalDirectory.equals(getCurrentDirectory()) && ! externalDirectory.equals(getParentDirectory()) &&
					     externalDirectory.toString().startsWith(getParentDirectory().toString()))
			{
				EXTERNAL_DIRECTORY = externalDirectory;
			}

			else
			{
				throw new OSException("External Directory cannot be parent or current directory");
			}
		}
		else
		{
			throw new OSException("External Directory cannot be null");
		}
	}

	public static File createNewDirectory(File parentDirectory, String newDirectoryName) throws OSException
	{
		verifyDirectory(parentDirectory, newDirectoryName);
		//logger.getLogger().info("Attempting to create new directory {} in {}",
		//                        newDirectoryName, parentDirectory);
		File newDirectory = new File(parentDirectory.getAbsolutePath() + SEPARATOR + newDirectoryName);
		newDirectory.mkdir();
		if(! newDirectory.exists())
		{
			throw new OSException(new Throwable("Could not create new directory"));
		}
		//logger.getLogger().info("Successfully created new directory {} in {}", newDirectory, parentDirectory);
		return newDirectory;
	}

	public static String getSeparator()
	{
		return SEPARATOR;
	}

	private static void verifyDirectory(File parentDirectory, String newDirectoryName) throws OSException
	{
		if(parentDirectory == null || newDirectoryName == null || newDirectoryName.isEmpty())
		{
			throw new OSException(new Throwable("Parent directory or new directory is null."));
		}

		if(! parentDirectory.exists() || parentDirectory.isFile())
		{
			throw new OSException(new Throwable("Parent directory does not exist or is not a valid directory."));
		}

		File newDirectory = new File(parentDirectory.getAbsolutePath() + SEPARATOR + newDirectoryName);
		//		if(newDirectory.exists())
		//		{
		//			throw new OSException(new Throwable("Folder already exists!")).writeException();
		//		}

		if(! parentDirectory.canWrite())
		{
			throw new OSException(new Throwable("Access Denied: Cannot write to this directory!")).writeException();
		}
	}
}
