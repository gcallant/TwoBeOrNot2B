package Database;

import Characters.A_Character;
import Mediator.Mediator;
import Utilities.OSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;

import java.sql.*;

/**
 * Created by Grant Callant on 5/12/2016. Manages an SQLite database object called Gamename.db- constructor will create
 * database if it does not exist, or connect to existing database.
 *
 * @author Grant Callant
 */
public class DatabaseManager
{
	private static final String         DRIVER            = "org.sqlite.JDBC";
	private static final String         DATABASE          = "jdbc:sqlite:DungeonCrawler.db";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private              Connection     databaseConnector = null;
	private              Statement      sqlStatement      = null;



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
			System.out.println("Couldn't find JDBC driver for SQLite\nSaving (and/or) loading will be unsupported.");
			closeConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Could not connect to database\n" + DATABASE +
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
				System.out.println("Could not create necessary tables in database\n" + DATABASE +
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
					              " STRENGTH INT NOT NULL," +
					              " CUNNING INT NOT NULL," +
					              " ARMOR INT NOT NULL," +
					              " WEAPON INT NOT NULL);";

			try
			{
				logger.info("Trying attempt on table CHARACTERS");
				result = sqlStatement.executeUpdate(statement);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

				logger.info("CHARACTERS table created successfully");
		}

		{
			statement = "CREATE TABLE IF NOT EXISTS INVENTORY(" +
					              "ITEMID INT PRIMARY KEY NOT NULL," +
					              " ITEMTYPE TEXT NOT NULL," +
					              " WEAPON BLOB NOT NULL," +
					              " ARMOR BLOB NOT NULL," +
					              " CONSUMABLE BLOB NOT NULL," +
					              " OWNER TEXT NOT NULL," +
					              " FOREIGN KEY(OWNER) REFERENCES CHARACTERS(NAME));";
			try
			{
				logger.info("Trying attempt on table INVENTORY");
				result = sqlStatement.executeUpdate(statement);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

				logger.info("INVENTORY table created successfully");
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

	public void loadParty(Mediator mediator)
	{

	}

	public void loadInventory(Mediator mediator)
	{

	}

	public void saveCharacters(Mediator mediator) throws SQLException, DatabaseManagerException
	{
		A_Character[] heroes = SaveFactory.getPartyToSave(mediator);
		int result = 0;

		for(A_Character hero : heroes)
		{
			logger.info("In Save- saving {} to db", hero.getName());
			result = insertIntoCharacters(hero);

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
	 *
	 * @param hero character to save
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
	 *         or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	private int insertIntoCharacters(A_Character hero) throws SQLException
	{
		String name = hero.getName();
		int health = hero.getHealth();
		int strength = hero.getHealth();
		int cunning = hero.getCunning();
		int armorPower = hero.getArmor().getPower();
		int weaponPower = hero.getWeapon().getPower();
		String concatValue = "'" + name + "'" + ", " + health + ", " + strength + ", " + cunning +
				                       ", " + armorPower + ", " + weaponPower;
		String statement = "REPLACE INTO CHARACTERS(NAME, HEALTH, STRENGTH, CUNNING, ARMOR, WEAPON)" +
				  "VALUES (" + concatValue + ");";
		logger.info("Attempting to insert hero {} into db", hero.getName());
		return sqlStatement.executeUpdate(statement);
	}

	public void saveInventory(Mediator mediator)
	{

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
			logger.info("Tried to close connection to db", e);
		}
	}



//	private static class DatabaseSingle
//	{
//		private static final DatabaseManager INSTANCE = new DatabaseManager();
//	}
}
