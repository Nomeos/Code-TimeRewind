package model.databaseManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.NoArgsConstructor;
import main.Game;
import model.account.Account;
import model.entity.Character;
import model.entity.Enemy;
import model.level.Level;

@NoArgsConstructor
public class DatabaseAccountManager {

	private String connectionStatement;
	private String sqlQuery;
	private boolean isRegister = false;
	private Connection connection;
	private Statement statement;

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

			this.sqlQuery = "Create Table Characters (Character_Id int not null generated always as identity,"
					+ "Name varchar(12), Health int,Defense Int,Attack int,Speed int, Description varchar(150),"
					+ "PRIMARY KEY (Character_Id))";
			this.statement.executeUpdate(this.sqlQuery);
			InsertAllDifferentCharacters();

			this.sqlQuery = "Create Table Account_Own_Characters (Account_Own_Character_Id int not null generated always as identity,"
					+ "Character_Id int, Account_Id int, Level int, Experience_point int,"
					+ "PRIMARY KEY (Account_Own_Character_Id),"
					+ "FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id),"
					+ "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Type_Of_Sprites (Type_Of_Sprite_Id int not null generated always as identity,"
					+ "Name varchar(12), PRIMARY KEY (Type_Of_Sprite_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Character_Sprites ("
					+ "  Character_Sprites_Id int not null generated always as identity," + "  Character_Id int,"
					+ "  Sprite_Path varchar(60)," + "  Type_Of_Sprite_Id int,"
					+ "  PRIMARY KEY (Character_Sprites_Id),"
					+ "  FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id),"
					+ "  FOREIGN KEY (Type_Of_Sprite_Id) REFERENCES Type_Of_Sprites(Type_Of_Sprite_Id))";
			this.statement.executeUpdate(this.sqlQuery);
			InsertSpriteCategory();
			// InsertAllSpritesIntoEachCharacters();

			this.sqlQuery = "Create Table Chapters (Chapter_Id int not null generated always as identity,"
					+ "Name varchar(20),PRIMARY KEY(Chapter_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Levels (Level_Id int not null generated always as identity,"
					+ " Chapter_Id Int,Name varchar(20), XPosition int, YPosition int, PRIMARY KEY(Level_Id),"
					+ " FOREIGN KEY (Chapter_Id) REFERENCES Chapters(Chapter_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Level_By_Accounts(Account_Id int,Level_Id int, Is_Level_Clear smallint DEFAULT 0,"
					+ "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id),"
					+ "FOREIGN KEY (Level_Id) REFERENCES Levels(Level_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Enemies (Enemy_Id int not null generated always as identity,"
					+ "Name varchar(20), Health int,Defense Int,Attack int,Speed int," + "PRIMARY KEY (Enemy_Id))";
			this.statement.executeUpdate(this.sqlQuery);

			this.sqlQuery = "Create Table Enemy_Per_Levels (Enemy_Per_Level_Id int not null generated always as identity,"
					+ "Level_Id int,Enemy_Id int,Level int," + "PRIMARY KEY (Enemy_Per_Level_Id),"
					+ "FOREIGN KEY (Level_Id) REFERENCES Levels(Level_Id),"
					+ "FOREIGN KEY (Enemy_Id) REFERENCES Enemies(Enemy_Id))";
			this.statement.executeUpdate(this.sqlQuery);
			InsertTheFirstLevel();

		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")
					|| e.getSQLState().equals("08003")) {
			} else {
				e.printStackTrace();
			}
		}
	}

	public void DeleteDatabase() {
		OpenDatabaseConnection();
		this.sqlQuery = "Drop table Enemy_Per_Levels";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Enemies";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Level_By_Accounts";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Levels";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Chapters";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Character_Sprites";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Type_Of_Sprites";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Account_Own_Characters";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Characters";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		this.sqlQuery = "Drop table Accounts";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
	}

	public boolean InsertTheFirstLevel() {
		boolean bool = true;
		try {
			this.sqlQuery = "INSERT INTO Enemies(Name, Health, Defense, Attack, Speed )values('Skeleton',100,10,150,0)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemies(Name, Health, Defense, Attack, Speed )values('Zombie',150,20,100,0)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemies(Name, Health, Defense, Attack, Speed )values('Boar',300,50,10,20)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Chapters(Name)values('Chapter One')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Chapters(Name)values('Chapter Two')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Chapters(Name)values('Chapter Three')";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Levels(Chapter_Id,Name,XPosition,YPosition)values(1,'Facile',125,125)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Levels(Chapter_Id,Name,XPosition,YPosition)values(1,'Moyen',400,400)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Levels(Chapter_Id,Name,XPosition,YPosition)values(1,'Difficile',70,800)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Levels(Chapter_Id,Name,XPosition,YPosition)values(1,'Difficile+',600,900)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Levels(Chapter_Id,Name,XPosition,YPosition)values(2,'Ultra Difficile',150,70)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(1,1,1)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(2,1,1)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(2,2,1)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(3,3,2)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(5,3,3)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(4,1,2)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(4,1,2)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(4,2,2)";
			this.statement.executeUpdate(this.sqlQuery);
			this.sqlQuery = "INSERT INTO Enemy_Per_Levels(Level_Id,Enemy_Id,Level)values(4,2,1)";
			this.statement.executeUpdate(this.sqlQuery);

		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	public boolean InsertAllDifferentCharacters() {
		boolean bool = true;
		this.sqlQuery = "INSERT INTO Characters ( Name, Health, Defense, Attack, Speed, description )values('Nom-eos', 400, 30, 100, 0, 'Ce personnage est très détendu ,&n a trouvé son épée dans un champ de fleur&n et pense qu '' il a une grande destinée.')";
		try {
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;

	}

	public void InsertSpriteCategory() {
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
			e.printStackTrace();
		}
	}

	/*
	 * public void InsertAllSpritesIntoEachCharacters() { try { File characterFolder
	 * = new File(System.getProperty("user.dir") + "/res/entity/"); for (String
	 * characterName : characterFolder.list()) { File spriteFolder = new
	 * File(System.getProperty("user.dir") + "/res/entity/" + characterName + "/");
	 * for (String characterSpriteFolder : spriteFolder.list()) { if
	 * (!(characterSpriteFolder == "NotUse")) { File spriteFiles = new
	 * File(System.getProperty("user.dir") + "/res/entity/" + characterName + "/" +
	 * characterSpriteFolder + "/"); for (String spritePng : spriteFiles.list()) {
	 * if (spritePng.contains(".png")) { this.sqlQuery =
	 * "INSERT INTO Character_Sprites(Character_Id,Sprite_Path,Type_Of_Sprite_Id)values((Select Character_Id from Characters where Name = '"
	 * + characterName + "'),'/res/entity/" + characterName + "/" +
	 * characterSpriteFolder + "/" + spritePng +
	 * "',(SELECT Type_Of_Sprite_Id from Type_Of_Sprites where name = '" +
	 * characterSpriteFolder + "'))"; this.statement.executeUpdate(this.sqlQuery); }
	 * } } }
	 * 
	 * this.sqlQuery = "INSERT INTO Type_Of_Sprites(Name)values('Walk')";
	 * this.statement.executeUpdate(this.sqlQuery); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */

	public Statement OpenDatabaseConnection() {

		try {

			this.connectionStatement = "jdbc:derby:C:/Projet TPI/Code-TimeRewind/Workspace/CodeTimeRewind/codetimerewinddb";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
	public boolean RegisterAccount(Account userAccount) throws SlickException {
		this.isRegister = true;
		if (IsTheUserAlreadyExist(userAccount)) {
			CloseDatabaseConnection();
			return false;
		} else {
			InsertAccountInDatabase(userAccount);
			InsertLevelsInAccount(userAccount);
			CloseDatabaseConnection();
			return true;
		}
	}

	// Principal method for check if the user can login
	public boolean LoginAccount(Account userAccount) throws SlickException {
		this.isRegister = false;
		if (IsTheUserAlreadyExist(userAccount)) {
			GetPlayerCharacters(userAccount);
			TakePlayerProgression(userAccount);
			CloseDatabaseConnection();
			return true;
		} else {
			CloseDatabaseConnection();
			return false;
		}
	}

	public void InsertLevelsInAccount(Account userAccount) {

		this.sqlQuery = "SELECT Level_Id FROM Levels";
		try {
			ResultSet resultQuery;
			Statement statement = OpenDatabaseConnection();
			resultQuery = statement.executeQuery(sqlQuery);
			List<Integer> i = new ArrayList<Integer>();
			while (resultQuery.next()) {
				i.add(resultQuery.getInt("Level_Id"));
			}
			for (int j : i) {
				this.sqlQuery = "INSERT INTO Level_By_Accounts(Account_Id, Level_Id, Is_Level_Clear)values((SELECT ACCOUNT_ID from ACCOUNTS WHERE Username = '"
						+ userAccount.getUsername() + "')," + j + ",0)";
				this.statement.executeUpdate(sqlQuery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void InsertAccountInDatabase(Account userAccount) {

		try {
			this.sqlQuery = "INSERT INTO Accounts (Username,Password_Hash,Account_Level) values('"
					+ userAccount.getUsername() + "','" + userAccount.getPasswordHash() + "',1)";
			Statement statement = OpenDatabaseConnection();
			statement.executeUpdate(sqlQuery);
			this.sqlQuery = "INSERT INTO Account_Own_Characters (Character_Id, Account_Id, Level,Experience_point)values (1,(SELECT Account_Id from Accounts where Username ='"
					+ userAccount.getUsername() + "'),1,0)";
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void TakePlayerProgression(Account userAccount) {
		Statement statement = OpenDatabaseConnection();
		ResultSet resultQuery;
		int levelId = 0;
		int chapterId = 1;
		List<Level> listOfLevel = new ArrayList<>();
		List<List<Level>> listOfChapter = new ArrayList<>();
		BufferedImage bimg;

		try {
			this.sqlQuery = "SELECT C.CHAPTER_ID,L.LEVEL_ID,L.NAME,LBA.IS_LEVEL_CLEAR,E.NAME,EPL.LEVEL,E.ATTACK,E.DEFENSE,E.HEALTH,E.SPEED, L.XPOSITION,L.YPOSITION FROM ACCOUNTS A\r\n"
					+ "INNER JOIN LEVEL_BY_ACCOUNTS LBA on A.ACCOUNT_ID = LBA.ACCOUNT_ID\r\n"
					+ "INNER JOIN LEVELS L on L.LEVEL_ID = LBA.LEVEL_ID\r\n"
					+ "INNER JOIN ENEMY_PER_LEVELS EPL on L.LEVEL_ID = EPL.LEVEL_ID\r\n"
					+ "INNER JOIN CHAPTERS C on C.CHAPTER_ID = L.CHAPTER_ID\r\n"
					+ "INNER JOIN ENEMIES E on EPL.ENEMY_ID = E.ENEMY_ID WHERE A.USERNAME = '"
					+ userAccount.getUsername() + "'";
			resultQuery = statement.executeQuery(sqlQuery);
			while (resultQuery.next()) {
				if (resultQuery.getInt(1) == chapterId) {
					if (resultQuery.getInt(2) == levelId) {
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(levelId - 1).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), 0, 0,
										bimg.getWidth(), bimg.getHeight(), new Image("/res/entity/"
												+ resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png")));
						bimg = null;
					} else {
						levelId = resultQuery.getInt("LEVEL_ID");
						listOfLevel.add(new Level(resultQuery.getString(3), resultQuery.getBoolean(4),
								resultQuery.getInt(11), resultQuery.getInt(12)));
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(levelId - 1).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), 0, 0,
										bimg.getWidth(), bimg.getHeight(), new Image("/res/entity/"
												+ resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png")));

					}
				} else {
					chapterId++;
					listOfChapter.add(new ArrayList<>(listOfLevel));
					listOfLevel.clear();
					if (resultQuery.getInt(2) == levelId) {
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(levelId - 1).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), 0, 0,
										bimg.getWidth(), bimg.getHeight(), new Image("/res/entity/"
												+ resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png")));
					} else {
						levelId = resultQuery.getInt("LEVEL_ID");
						listOfLevel.add(new Level(resultQuery.getString(3), resultQuery.getBoolean(4),
								resultQuery.getInt(11), resultQuery.getInt(12)));
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(0).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), 0, 0,
										bimg.getWidth(), bimg.getHeight(), new Image("/res/entity/"
												+ resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png")));
						if(resultQuery.isLast()) {
							listOfChapter.add(new ArrayList<>(listOfLevel));
						}
					}

				}

			}
		} catch (SQLException | SlickException | IOException e) {
			e.printStackTrace();
		}
		if (Game.getInstance() != null) {
			Game.getInstance().setListOfChapters(listOfChapter);
		}

	}

	// Check if the user already exist in the json, it returns a boolean (works with
	// login and register).
	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;

		if (isRegister) {
			Statement statement = OpenDatabaseConnection();
			ResultSet resultQuery;
			try {
				this.sqlQuery = "SELECT * FROM Accounts WHERE Username = '" + userAccount.getUsername() + "'";
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
				ResultSet resultQuery;
				resultQuery = statement.executeQuery(sqlQuery);
				while (resultQuery.next()) {
					if (userAccount.areThePasswordEquals(resultQuery.getString("Password_hash"))) {
						resultQuery.getString("Username");
						userAccount.setAccount_Level(resultQuery.getInt("Account_Level"));

						result = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;

	}

	private void GetPlayerCharacters(Account userAccount) {
		ResultSet resultQueryOwnCharacters;
		try {
			resultQueryOwnCharacters = statement.executeQuery(sqlQuery);

			BufferedImage bimg;
			this.sqlQuery = "select" + "  c.Name," + "  ac.level," + "  c.health," + "  c.defense," + "  c.attack,"
					+ "  c.speed, " + "ac.experience_point," + " c.description" + " from" + "  characters c"
					+ "  INNER JOIN Account_own_characters ac ON c.character_id = ac.character_id"
					+ "  INNER JOIN Accounts a ON ac.account_id = a.account_id" + "  where a.username = '"
					+ userAccount.getUsername() + "'";
			resultQueryOwnCharacters = statement.executeQuery(sqlQuery);
			while (resultQueryOwnCharacters.next()) {
				bimg = ImageIO.read(new File("./res/entity/" + resultQueryOwnCharacters.getString("Name") + "/"
						+ resultQueryOwnCharacters.getString("Name") + ".png"));
				userAccount.getListOfOwnedCharacter()
						.add(new Character(resultQueryOwnCharacters.getString("Name"),
								resultQueryOwnCharacters.getInt("Level"), resultQueryOwnCharacters.getInt("health"),
								resultQueryOwnCharacters.getInt("defense"), resultQueryOwnCharacters.getInt("attack"),
								resultQueryOwnCharacters.getInt("speed"), 0, 0, bimg.getWidth(), bimg.getHeight(),
								resultQueryOwnCharacters.getInt("experience_point"),
								resultQueryOwnCharacters.getString("description")));
			}
		} catch (SQLException | SlickException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.CloseDatabaseConnection();
		if (Game.getInstance() != null) {
			Game.getInstance().setPlayerAccount(userAccount);
		}

	}

}
