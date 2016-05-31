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
import java.util.List;

/**
 * Created by Grant Callant on 5/12/2016. Manages an SQLite database object called Gamename.db- constructor will create
 * database if it does not exist, or connect to existing database.
 *
 * @author Grant Callant
 */
public class DatabaseManager
{
	private final static File       CWD                = OSUtil.getCurrentDirectory();
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
			e.printStackTrace();
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
				logger.trace("Tried recreate, could not", e, e1);
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
			e.printStackTrace();
		}

		String statement = "";

		{
			statement = "CREATE TABLE IF NOT EXISTS CHARACTERS(" +
					              "NAME TEXT PRIMARY KEY NOT NULL," +
					              " HEALTH INT NOT NULL," +
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
				e.printStackTrace();
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

		{
			statement = "CREATE TABLE IF NOT EXISTS INVENTORY(" +
					              //					              "ITEMID INT PRIMARY KEY NOT NULL," +
					              //					              " ITEMTYPE TEXT NOT NULL," +
					              "WEAPON BLOB NOT NULL," +
					              " ARMOR BLOB NOT NULL," +
					              " CONSUMABLE BLOB NOT NULL);";
			//					              " OWNER TEXT NOT NULL," +
			//					              " FOREIGN KEY(OWNER) REFERENCES CHARACTERS(NAME));";
			try
			{
				logger.info("Trying attempt on table INVENTORY");
				result = sqlStatement.executeUpdate(statement);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
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
			e.printStackTrace();
		}
		return false;
	}

	public void loadParty(Mediator mediator) throws SQLException
	{
		A_Character[] heroes = new A_Character[4];
		sqlStatement = databaseConnector.createStatement();
		ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM CHARACTERS;");
		int i = 0, floor = 0;

		while(resultSet.next() && i < 4)
		{
			String name = resultSet.getString("NAME");
			logger.info("In load party, loaded {} from db", name);
			int health = resultSet.getInt("HEALTH");
			int power = resultSet.getInt("POWER");
			int cunning = resultSet.getInt("CUNNING");
			int armorPower = resultSet.getInt("ARMOR");
			int weaponPower = resultSet.getInt("WEAPON");
			floor = resultSet.getInt("FLOOR");
			A_Character loadedCharacter = SaveFactory.makeCharacterFromLoad(name, health, power, cunning, armorPower,
			                                                                weaponPower);
			heroes[i] = loadedCharacter;
			i++;
		}
		logger.info("Successfully loaded all characters in party");

		resultSet.close();
		sqlStatement.close();
		SaveFactory saveFactory = new SaveFactory(mediator);
		saveFactory.setHeroes(heroes);
		saveFactory.setFloor(floor);
	}

	public void loadInventory(Mediator mediator)
	throws SQLException, IOException, ClassNotFoundException, DatabaseManagerException
	{
		sqlStatement = databaseConnector.createStatement();
		ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM INVENTORY;");

		InputStream armorBlob = resultSet.getBinaryStream("ARMOR");
		InputStream weaponBlob = resultSet.getBinaryStream("WEAPON");
		InputStream consumableBlob = resultSet.getBinaryStream("CONSUMABLE");

		resultSet.close();
		sqlStatement.close();

		List<Armor> armorList = SaveFactory.getListFromBlob(armorBlob);
		List<Weapon> weapons = SaveFactory.getListFromBlob(weaponBlob);
		List<Consumable> consumables = SaveFactory.getListFromBlob(consumableBlob);

		Inventory inventory = new Inventory(weapons, armorList, consumables);
		SaveFactory.setInventory(inventory);
		SaveFactory.createPartyToLoad();
	}

	public void saveCharacters(Mediator mediator) throws SQLException, DatabaseManagerException
	{
		int[] level = new int[1];
		A_Character[] heroes = SaveFactory.getPartyToSave(mediator, level);
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
	 * @param hero character to save
	 * @param level
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements
	 * that return nothing
	 * @throws SQLException
	 */
	private int insertIntoCharacters(A_Character hero, int[] level) throws SQLException
	{
		String name = hero.getName();
		int health = hero.getHealth();
		int power = hero.getHealth();
		int cunning = hero.getCunning();
		int armorPower = (hero.getArmor().getPower() - hero.getArmor().getBase()); //Adds base back on load
		int weaponPower = (hero.getWeapon().getPower() - hero.getWeapon().getBase());
		int floor = level[0];
		String concatValue = "'" + name + "'" + ", " + health + ", " + power + ", " + cunning +
				                       ", " + armorPower + ", " + weaponPower + "," + floor;
		String statement = "REPLACE INTO CHARACTERS(NAME, HEALTH, POWER, CUNNING, ARMOR, WEAPON, FLOOR)" +
				                     "VALUES (" + concatValue + ");";
		logger.info("Attempting to insert hero {} into db", hero.getName());
		return sqlStatement.executeUpdate(statement);
	}

	public void saveInventory(Mediator mediator) throws IOException, SQLException
	{
		PreparedStatement preparedStatement = null;
		Blob[] inventoryBlob = SaveFactory.getSerializedInventoryToSaveAsBlobs(mediator);
		Blob armorBlob = inventoryBlob[0];
		Blob weaponBlob = inventoryBlob[1];
		Blob consumableBlob = inventoryBlob[2];

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
