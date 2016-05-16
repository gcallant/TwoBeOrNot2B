package Utilities;

import Logging.LoggingManager;

import java.io.File;

/**
 * Created by Grant Callant on 5/14/2016. A collection of static utilities that simplify working with the operating
 * system.
 *
 * @author Grant Callant
 */
public class OSUtil
{
	private static LoggingManager logger = new LoggingManager("OSUtil");

	/**
	 * Method to get the parent directory of where the program is currently being run from.
	 *
	 * @return parent directory as a File
	 * @throws OSException if parent directory could not be opened
	 */
	public static File getParentDirectory() throws OSException
	{
		logger.getLogger().info("Attempting to get parent Directory");
		File cwd = new File(System.getProperty("user.dir"));
		logger.getLogger().info("Got Current directory {}", cwd);
		File parent = new File(cwd.getParent());
		if(parent == null || parent.isFile())
		{
			throw new OSException("Couldn't get parent directory");
		}
		logger.getLogger().info("Got Parent directory {}", parent);
		return parent;
	}

	public static File createNewDirectory(File parentDirectory, String newDirectoryName) throws OSException
	{
		verifyDirectory(parentDirectory, newDirectoryName);
		logger.getLogger().info("Attempting to create new directory {} in {}",
		                        newDirectoryName, parentDirectory);
		File newDirectory = new File(parentDirectory.getAbsolutePath() + "\\" + newDirectoryName);
		boolean directoryCreated = directoryCreated = newDirectory.mkdir();
		if(! directoryCreated)
		{
			throw new OSException(new Throwable("Could not create new directory"));
		}
		logger.getLogger().info("Successfully created new directory {} in {}", newDirectory, parentDirectory);
		return newDirectory;
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

		File newDirectory = new File(parentDirectory.getAbsolutePath() + "\\" + newDirectoryName);
		if(newDirectory.exists())
		{
			throw new OSException(new Throwable("Folder already exists!")).writeException();
		}

		if(! parentDirectory.canWrite())
		{
			throw new OSException(new Throwable("Access Denied: Cannot write to this directory!")).writeException();
		}
	}
}
