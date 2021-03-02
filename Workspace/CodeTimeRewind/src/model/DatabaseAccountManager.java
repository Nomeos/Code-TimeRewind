package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Game;

public class DatabaseAccountManager {

	private String connectionStatement;
	private String sqlQuery;
	private boolean isRegister = false;
	private Connection connection;
	private Statement statement;

	// Constructor of the JsonManager class
	public DatabaseAccountManager() {
	}

	public void DatabaseCreation() {
		try {
			this.connectionStatement = "jdbc:derby:codetimerewinddb;create=true";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CreateAllTables();
	}

	public void CreateAllTables() {
		try {
			this.sqlQuery = "Create Table Accounts (Account_Id int not null generated always as identity,"
					+ "Username varchar(12), Password_Hash varchar(128),Account_Level int,"
					+ "PRIMARY KEY (Account_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Characters (Character_Id int not null generated always as identity,"
					+ "Name varchar(12), Health int,Defense Int,Attack int,Speed int," + "PRIMARY KEY (Character_Id))";
			this.statement.executeUpdate(this.sqlQuery);
			InsertAllDifferentCharacters();
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Account_Own_Characters (Account_Own_Character_Id int not null generated always as identity,"
					+ "Character_Id int, Account_Id int, Level int, Experience_point int,"
					+ "PRIMARY KEY (Account_Own_Character_Id),"
					+ "FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id),"
					+ "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Type_Of_Sprites (Type_Of_Sprite_Id int not null generated always as identity,"
					+ "Name varchar(12), PRIMARY KEY (Type_Of_Sprite_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Character_Sprites ("
					+ "  Character_Sprites_Id int not null generated always as identity," + "  Character_Id int,"
					+ "  Sprite_Path varchar(60)," + "  Type_Of_Sprite_Id int,"
					+ "  PRIMARY KEY (Character_Sprites_Id),"
					+ "  FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id),"
					+ "  FOREIGN KEY (Type_Of_Sprite_Id) REFERENCES Type_Of_Sprites(Type_Of_Sprite_Id))";
			this.statement.executeUpdate(this.sqlQuery);
			InsertAllSpritesIntoEachCharacters();
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
	}

	public void InsertAllDifferentCharacters() {
		this.sqlQuery = "INSERT INTO Characters (Name,Health,Defense,Attack,Speed) values('Nom-eos',400,30,30,100)";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void InsertAllSpritesIntoEachCharacters() {

		try {
			this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Walk')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Win')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Strike')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Idle')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Hurt')";
			this.statement.executeUpdate(this.sqlQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			File characterFolder = new File(System.getProperty("user.dir") + "/res/entity/");
			for (String characterName : characterFolder.list()) {
				File spriteFolder = new File(System.getProperty("user.dir") + "/res/entity/" + characterName + "/");
				for (String characterSpriteFolder : spriteFolder.list()) {
					if (!(characterSpriteFolder == "NotUse")) {
						File spriteFiles = new File(System.getProperty("user.dir") + "/res/entity/" + characterName
								+ "/" + characterSpriteFolder + "/");
						for (String spritePng : spriteFiles.list()) {
							if (spritePng.contains(".png")) {
								this.sqlQuery = "INSERT INTO Character_Sprites(Character_Id,Sprite_Path,Type_Of_Sprite_Id)values((Select Character_Id from Characters where Name = '"
										+ characterName + "'),'/res/entity/" + characterName + "/"
										+ characterSpriteFolder + "/" + spritePng
										+ "',(SELECT Type_Of_Sprite_Id from Type_Of_Sprites where name = '"
										+ characterSpriteFolder + "'))";
								this.statement.executeUpdate(this.sqlQuery);
							}
						}
					}
				}

				this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Walk')";
				this.statement.executeUpdate(this.sqlQuery);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement OpenDatabaseConnection() {

		try {

			this.connectionStatement = "jdbc:derby:C:/Projet TPI/Code-TimeRewind/Workspace/CodeTimeRewind/codetimerewinddb";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement();
			return this.statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void CloseDatabaseConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Principal method for check if the user can register
	public boolean RegisterAccount(Account userAccount) {
		this.isRegister = true;
		if (IsTheUserAlreadyExist(userAccount)) {
			CloseDatabaseConnection();
			return false;
		} else {
			InsertAccountInDatabase(userAccount);
			CloseDatabaseConnection();
			return true;
		}
	}

	// Principal method for check if the user can login
	public boolean LoginAccount(Account userAccount) {
		this.isRegister = false;
		if (IsTheUserAlreadyExist(userAccount)) {
			CloseDatabaseConnection();
			return true;
		} else {
			CloseDatabaseConnection();
			return false;
		}
	}

	public void InsertAccountInDatabase(Account userAccount) {

		try {
			this.sqlQuery = "INSERT INTO Accounts (Username,Password_Hash,Account_Level) values('"
					+ userAccount.getUsername() + "','" + userAccount.getPasswordHash() + "',1)";
			Statement statement = OpenDatabaseConnection();
			statement.executeUpdate(sqlQuery);
			this.sqlQuery = "INSERT INTO Account_Own_Characters (Character_Id, Account_Id, Level,Experience_point)values (1,(SELECT Account_Id from Accounts where Username ='"
					+ userAccount.getUsername() + "'),1,30)";
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Check if the user already exist in the json, it returns a boolean (works with
	// login and register).
	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;
		if (isRegister) {
			this.sqlQuery = "SELECT * FROM Accounts WHERE Username = '" + userAccount.getUsername() + "'";
			Statement statement = OpenDatabaseConnection();
			ResultSet resultQuery;
			try {
				resultQuery = statement.executeQuery(sqlQuery);
				while (resultQuery.next()) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			try {
				this.sqlQuery = "SELECT * FROM Accounts WHERE Username = '" + userAccount.getUsername() + "' ";
				Statement statement = OpenDatabaseConnection();
				ResultSet resultQuery, resultQueryOwnCharacters;
				resultQuery = statement.executeQuery(sqlQuery);
				while (resultQuery.next()) {
					if (userAccount.areThePasswordEquals(resultQuery.getString("Password_hash"))) {
						resultQuery.getString("Username");
						userAccount.setAccount_Level(resultQuery.getInt("Account_Level"));

						result = true;
					}
				}
				if (result) {
					this.sqlQuery = "select" + "  c.Name," + "  ac.level," + "  c.health," + "  c.defense,"
							+ "  c.attack," + "  c.speed, " + "ac.experience_point" + " from" + "  characters c"
							+ "  INNER JOIN Account_own_characters ac ON c.character_id = ac.character_id"
							+ "  INNER JOIN Accounts a ON ac.account_id = a.account_id" + "  where a.username = '"
							+ userAccount.getUsername() + "'";
					resultQueryOwnCharacters = statement.executeQuery(sqlQuery);
					while (resultQueryOwnCharacters.next()) {
						userAccount.getListOfOwnedCharacter().add(new Character(
								resultQueryOwnCharacters.getString("Name"), resultQueryOwnCharacters.getInt("Level"),
								resultQueryOwnCharacters.getInt("health"), resultQueryOwnCharacters.getInt("defense"),
								resultQueryOwnCharacters.getInt("attack"), resultQueryOwnCharacters.getInt("speed"),
								resultQueryOwnCharacters.getInt("experience_point")));
					}
				}

				if (Game.getInstance() != null) {
					Game.getInstance().setPlayerAccount(userAccount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;

	}

}
