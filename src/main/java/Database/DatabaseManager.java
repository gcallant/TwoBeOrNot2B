package Database;

import Characters.A_Character;
import Exceptions.DatabaseManagerException;
import GameState.Mediator;
import Item.Armor;
import Item.Consumable;
import Item.Weapon;
import PartyManagement.Inventory;
import Utilities.Display;
import Utilities.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grant Callant on 5/12/2016. Manages an SQLite database object called Gamename.db- constructor will create
 * database if it does not exist, or connect to existing database.
 *
 * @author Grant Callant
 */
public class DatabaseManager
{
	private static final String     SEPARATOR          = OSUtil.getSeparator();
	private static final File       EXTERNAL_DIRECTORY = OSUtil.getExternalDirectory();
	private final static String     RESOURCES          = EXTERNAL_DIRECTORY.getAbsolutePath();
	private static final String     DRIVER             = "org.sqlite.JDBC";
	private static final String     DATABASE           = "jdbc:sqlite:" + RESOURCES + SEPARATOR + "DungeonCrawler.db";
	private final        Logger     logger             = LoggerFactory.getLogger(this.getClass());
	private              Connection databaseConnector  = null;
	private              Statement  sqlStatement       = null;

	public DatabaseManager()
	{
		try
		{
			logger.info("Creating Database");
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			sqLiteConfig.enforceForeignKeys(true);
			Class.forName(DRIVER);
			logger.info("Creating connection to database");
			databaseConnector = DriverManager.getConnection(DATABASE, sqLiteConfig.toProperties());
			logger.info("Connection successful to {}", databaseConnector);
			createTables();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			Display.displayMessage("Couldn't find JDBC driver for SQLite\nSaving (and/or) loading will be unsupported.");
			closeConnection();
		}
		catch(SQLException e)
		{
			logger.debug("We had a sql exception, couldn't load", e);
			Display.displayMessage("Could not connect to database\n" + DATABASE +
					                         "\nSaving (and/or) loading will be unsupported.");
			closeConnection();
		}
		catch(DatabaseManagerException e)
		{
			logger.debug("Tried to create table", e);
			logger.info("Trying to recreate above failed table");
			try
			{
				createTables();
			}
			catch(DatabaseManagerException e1)
			{
				e1.printStackTrace();
				Display.displayMessage("Could not create necessary tables in database\n" + DATABASE +
						                         "\nSaving (and/or) loading will be unsupported.");
				logger.trace("Tried to recreate tables, could not", e, e1);
			}
		}
	}

	//	@Contract(pure = true)
	//	public static DatabaseManager getInstance()
	//	{
	//		return DatabaseSingle.INSTANCE;
	//	}

	private void createTables() throws DatabaseManagerException
	{
		logger.info("Attempting to create tables:");
		int result = 0;
		try
		{
			sqlStatement = databaseConnector.createStatement();
		}
		catch(SQLException e)
		{
			logger.debug("Could not create new sqlStatement in tables", e);
		}

		String statement = "";

		{
			statement = "CREATE TABLE IF NOT EXISTS CHARACTERS(" +
					              "NAME TEXT PRIMARY KEY NOT NULL," +
					              " LEVEL INT NOT NULL," +
					              " EXPERIENCE INT NOT NULL," +
					              " HEALTH INT NOT NULL," +
					              " MAXHEALTH INT NOT NULL, " +
					              " POWER INT NOT NULL," +
					              " CUNNING INT NOT NULL," +
					              " ARMOR INT NOT NULL," +
					              " WEAPON INT NOT NULL," +
					              " FLOOR INT NOT NULL);";

			try
			{
				logger.info("Trying attempt on table CHARACTERS");
				result = sqlStatement.executeUpdate(statement);
			}
			catch(SQLException e)
			{
				logger.debug("Could not execute statement to create Character table", e);
			}

			if(result > 0)
			{
				logger.info("CHARACTERS table created successfully");
			}
			else
			{
				logger.info("Found existing CHARACTERS table- using it");
			}
		}

		if(sqlStatement != null)
		{
			try
			{
				sqlStatement.close();
			}
			catch(SQLException e)
			{
				logger.debug("Tried to close sqlStatement", e);
				try
				{
					sqlStatement.close();
				}
				catch(SQLException e1)
				{
					throw new DatabaseManagerException().notClosable("Could not close sqlStatement", e1);
				}
			}
		}
	}

	private void createInventoryTable()
	{
		int result = 0;

		String statement = "CREATE TABLE IF NOT EXISTS INVENTORY(" +
				              " WEAPON BLOB NOT NULL," +
				              " ARMOR BLOB NOT NULL," +
				              " CONSUMABLE BLOB NOT NULL);";
		try
		{
			sqlStatement = databaseConnector.createStatement();
			logger.info("Trying attempt on table INVENTORY");
			result = sqlStatement.executeUpdate(statement);
		}
		catch(SQLException e)
		{
			logger.debug("Could not execute statement to create Inventory table", e);
		}

		if(result > 0)
		{
			logger.info("INVENTORY table created successfully");
		}
		else
		{
			logger.info("Found an existing INVENTORY table- using it");
		}
	}

	private boolean tableIsPresent(String tableName)
	{
		try
		{
			DatabaseMetaData metaData = databaseConnector.getMetaData();
			ResultSet resultSet = metaData.getTables(null, null, tableName, null);
			if(resultSet != null)
			{ return true; }
		}
		catch(SQLException e)
		{
			logger.debug("Error checking if table is present in db", e);
		}
		return false;
	}

	public void loadParty(Mediator mediator) throws SQLException, DatabaseManagerException
	{
		List<A_Character> heroes = new ArrayList<>(4);
		sqlStatement = databaseConnector.createStatement();
		ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM CHARACTERS;");
		int i = 0, floor = 0, level = 0, experience = 0, health = 0;

		while(resultSet.next())
		{
			String name = resultSet.getString("NAME");
			logger.info("In load party, loaded {} from db", name);
			level = resultSet.getInt("LEVEL");
			experience = resultSet.getInt("EXPERIENCE");
			health = resultSet.getInt("HEALTH");
			int maxHealth = resultSet.getInt("MAXHEALTH");
			int power = resultSet.getInt("POWER");
			int cunning = resultSet.getInt("CUNNING");
			int armorPower = resultSet.getInt("ARMOR");
			int weaponPower = resultSet.getInt("WEAPON");
			floor = resultSet.getInt("FLOOR");
			A_Character loadedCharacter = LoadFacade.makeCharacterFromLoad(name, maxHealth, power, cunning, armorPower,
			                                                               weaponPower);
			heroes.add(loadedCharacter);
			i++;
		}
		logger.info("Successfully loaded all characters in party");

		resultSet.close();
		sqlStatement.close();
		LoadFacade loadFacade = new LoadFacade(mediator);
		loadFacade.setHeroes(heroes);
		loadFacade.setFloor(floor);
		loadFacade.setCurrentHealth(health);
		LoadFacade.setLevelOfHeroes(level);
		LoadFacade.setExperienceOfHeroes(experience);
	}

	public void loadInventory(Mediator mediator)
	throws SQLException, IOException, ClassNotFoundException, DatabaseManagerException
	{
		sqlStatement = databaseConnector.createStatement();
		ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM INVENTORY;");

		logger.info("Attempting to load armor from inventory");
		InputStream armorBlob = resultSet.getBinaryStream("ARMOR");

		logger.info("Attempting to load weapons from inventory");
		InputStream weaponBlob = resultSet.getBinaryStream("WEAPON");

		logger.info("Attempting to load consumables from inventory");
		InputStream consumableBlob = resultSet.getBinaryStream("CONSUMABLE");

		logger.info("Loaded all inventory from db");

		resultSet.close();
		sqlStatement.close();

		logger.info("Converting armor to a list");
		List<Armor> armorList = LoadFacade.getListFromBlob(armorBlob);

		logger.info("Converting weapons to a list");
		List<Weapon> weapons = LoadFacade.getListFromBlob(weaponBlob);

		logger.info("Converting consumables to a list");
		List<Consumable> consumables = LoadFacade.getListFromBlob(consumableBlob);

		logger.info("All inventory converted to lists- creating inventory");
		Inventory inventory = new Inventory(weapons, armorList, consumables);
		LoadFacade.setInventory(inventory);
		LoadFacade.createPartyToLoad();
	}

	public void saveCharacters(Mediator mediator) throws SQLException, DatabaseManagerException
	{
		int[] level = new int[1];
		List<A_Character> heroes = SaveFacade.getPartyToSave(mediator, level);
		int result = 0;
		sqlStatement = databaseConnector.createStatement();

		for(A_Character hero : heroes)
		{
			logger.info("In Save- saving {} to db", hero.getName());
			result = insertIntoCharacters(hero, level);

			if(result > 0)
			{
				logger.info("{} saved successfully", hero.getName());
			}
			else
			{
				throw new DatabaseManagerException().notSaved("Could not save hero", hero.getName());
			}
		}
		logger.info("Saved all heroes");
		sqlStatement.close();
	}

	/**
	 * @param hero       character to save
	 * @param floorLevel
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements
	 * that return nothing
	 * @throws SQLException
	 */
	private int insertIntoCharacters(A_Character hero, int[] floorLevel) throws SQLException
	{
		String name = hero.getName();
		int health = hero.getHealth();
		int maxHealth = hero.getMaxHealth();
		int level = hero.getLevel();
		int experience = hero.getExperience();
		int power = hero.getPower();
		int cunning = hero.getCunning();
		int armorPower = (hero.getArmor().getPower() - hero.getArmor().getBase()); //Adds base back on load
		int weaponPower = (hero.getWeapon().getPower() - hero.getWeapon().getBase());
		int floor = floorLevel[0];
		String concatValue = "'" + name + "'" + ", " + level + ", " + experience + ", " + health + ", " + maxHealth + "," +
				                       " " + power + ", " +
				                       cunning + ", " + armorPower + ", " + weaponPower + "," + floor;
		String statement = "REPLACE INTO CHARACTERS(NAME, LEVEL, EXPERIENCE, HEALTH, MAXHEALTH, POWER, CUNNING, ARMOR, WEAPON, " +
				                     "FLOOR)" + "VALUES (" + concatValue + ");";
		logger.info("Attempting to insert hero {} into db", hero.getName());
		return sqlStatement.executeUpdate(statement);
	}

	public void saveInventory(Mediator mediator) throws IOException, SQLException
	{
		PreparedStatement preparedStatement = null;
		sqlStatement = databaseConnector.createStatement();
		Blob[] inventoryBlob = SaveFacade.getSerializedInventoryToSaveAsBlobs(mediator);
		Blob armorBlob = inventoryBlob[0];
		Blob weaponBlob = inventoryBlob[1];
		Blob consumableBlob = inventoryBlob[2];

		dropInventoryTable();
		createInventoryTable();
		String statement = "REPLACE INTO INVENTORY(WEAPON, ARMOR, CONSUMABLE)" +
				                     "VALUES (?, ?, ?);";

		preparedStatement = databaseConnector.prepareStatement(statement);
		preparedStatement.setBytes(1, weaponBlob.getBytes(1, (int) weaponBlob.length()));
		preparedStatement.setBytes(2, armorBlob.getBytes(1, (int) armorBlob.length()));
		preparedStatement.setBytes(3, consumableBlob.getBytes(1, (int) consumableBlob.length()));
		logger.info("In save- attempting to save inventory into db");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		logger.info("Successfully saved inventory");
	}

	private void dropInventoryTable() throws SQLException
	{
		logger.info("Attempting to drop inventory table...");
		sqlStatement = databaseConnector.createStatement();
		String statement = "DROP TABLE IF EXISTS INVENTORY;";
		int result = sqlStatement.executeUpdate(statement);
		if(result > 0)
		{
			logger.info("Successfully dropped inventory table");
		}
		else
		{
			logger.info("Inventory table didn't exist");
		}
	}

	public void closeConnection()
	{
		try
		{
			if(! sqlStatement.isClosed())
			{
				sqlStatement.close();
			}

			if(! databaseConnector.isClosed())
			{
				databaseConnector.close();
			}
		}
		catch(SQLException e)
		{
			logger.debug("Tried to close connection to db", e);
		}
	}

	//	private static class DatabaseSingle
	//	{
	//		private static final DatabaseManager INSTANCE = new DatabaseManager();
	//	}
}
