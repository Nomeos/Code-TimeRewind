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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.NoArgsConstructor;
import main.Game;
import model.account.Account;
import model.chapter.Chapter;
import model.enemy_Per_Stage.Enemy_Per_Stage;
import model.livingEntity.character.Character;
import model.livingEntity.enemy.Enemy;
import model.stage.Stage;

@NoArgsConstructor
public class DatabaseAccountManager {

	private boolean isRegister = false;
	private Session session;
	private SessionFactory sessionFactory;

	public void DatabaseCreation() {
		/*
		 * try {
		 * 
		 * this.connectionStatement = "jdbc:derby:codetimerewinddb;create=true";
		 * this.connection = DriverManager.getConnection(this.connectionStatement);
		 * this.statement = this.connection.createStatement();
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } CreateAllTables();
		 */

		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session = sessionFactory.getCurrentSession();
		sessionFactory.close();

	}

	public void CreateAllTables(Session session) {

		/*
		 * try { this.sqlQuery =
		 * "Create Table Accounts (Account_Id int not null generated always as identity,"
		 * + "Username varchar(12), Password_Hash varchar(128),Account_Level int," +
		 * "PRIMARY KEY (Account_Id))"; this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Characters (Character_Id int not null generated always as identity,"
		 * +
		 * "Name varchar(12), Health int,Defense Int,Attack int,Speed int, Description varchar(150),"
		 * + "PRIMARY KEY (Character_Id))"; this.statement.executeUpdate(this.sqlQuery);
		 * InsertAllDifferentCharacters();
		 * 
		 * this.sqlQuery =
		 * "Create Table Account_Own_Characters (Account_Own_Character_Id int not null generated always as identity,"
		 * + "Character_Id int, Account_Id int, Level int, Experience_point int," +
		 * "PRIMARY KEY (Account_Own_Character_Id)," +
		 * "FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id)," +
		 * "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id))";
		 * this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Type_Of_Sprites (Type_Of_Sprite_Id int not null generated always as identity,"
		 * + "Name varchar(12), PRIMARY KEY (Type_Of_Sprite_Id))";
		 * this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery = "Create Table Character_Sprites (" +
		 * "  Character_Sprites_Id int not null generated always as identity," +
		 * "  Character_Id int," + "  Sprite_Path varchar(60)," +
		 * "  Type_Of_Sprite_Id int," + "  PRIMARY KEY (Character_Sprites_Id)," +
		 * "  FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id)," +
		 * "  FOREIGN KEY (Type_Of_Sprite_Id) REFERENCES Type_Of_Sprites(Type_Of_Sprite_Id))"
		 * ; this.statement.executeUpdate(this.sqlQuery); InsertSpriteCategory(); //
		 * InsertAllSpritesIntoEachCharacters();
		 * 
		 * this.sqlQuery =
		 * "Create Table Chapters (Chapter_Id int not null generated always as identity,"
		 * + "Name varchar(20),PRIMARY KEY(Chapter_Id))";
		 * this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Levels (Level_Id int not null generated always as identity," +
		 * " Chapter_Id Int,Name varchar(20), XPosition int, YPosition int, PRIMARY KEY(Level_Id),"
		 * + " FOREIGN KEY (Chapter_Id) REFERENCES Chapters(Chapter_Id))";
		 * this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Level_By_Accounts(Account_Id int,Level_Id int, Is_Level_Clear smallint DEFAULT 0,"
		 * + "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id)," +
		 * "FOREIGN KEY (Level_Id) REFERENCES Levels(Level_Id))";
		 * this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Enemies (Enemy_Id int not null generated always as identity," +
		 * "Name varchar(20), Health int,Defense Int,Attack int,Speed int," +
		 * "PRIMARY KEY (Enemy_Id))"; this.statement.executeUpdate(this.sqlQuery);
		 * 
		 * this.sqlQuery =
		 * "Create Table Enemy_Per_Levels (Enemy_Per_Level_Id int not null generated always as identity,"
		 * + "Level_Id int,Enemy_Id int,Level int," +
		 * "PRIMARY KEY (Enemy_Per_Level_Id)," +
		 * "FOREIGN KEY (Level_Id) REFERENCES Levels(Level_Id)," +
		 * "FOREIGN KEY (Enemy_Id) REFERENCES Enemies(Enemy_Id))";
		 * this.statement.executeUpdate(this.sqlQuery); InsertTheFirstLevel();
		 * 
		 * } catch (SQLException e) { if (e.getSQLState().equals("42Y55") ||
		 * e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32") ||
		 * e.getSQLState().equals("08003")) { } else { e.printStackTrace(); } }
		 */
	}

	/*
	 * public void DeleteDatabase() { OpenDatabaseConnection(); this.sqlQuery =
	 * "Drop table Enemy_Per_Levels"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery = "Drop table Enemies"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery =
	 * "Drop table Level_By_Accounts"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery = "Drop table Levels"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery = "Drop table Chapters"; try
	 * { this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery =
	 * "Drop table Character_Sprites"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery =
	 * "Drop table Type_Of_Sprites"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery =
	 * "Drop table Account_Own_Characters"; try {
	 * this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery = "Drop table Characters";
	 * try { this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) {
	 * if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } this.sqlQuery = "Drop table Accounts"; try
	 * { this.statement.executeUpdate(this.sqlQuery); } catch (SQLException e) { if
	 * (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") ||
	 * e.getSQLState().equals("X0Y32")) {
	 * 
	 * } else { e.printStackTrace(); } } }
	 */

	public boolean InsertTheFirstLevel() {
		boolean bool = true;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			Enemy tempEnemyS;
			Enemy tempEnemyZ;
			Enemy tempEnemyB;
			Stage tempStage;
			Chapter tempChapter;
			Enemy_Per_Stage tempEpl;

			tempEnemyS = new Enemy("Skeleton", 1, 100, 10, 150, 0);
			this.session.save(tempEnemyS);
			tempEnemyZ = new Enemy("Zombie", 1, 150, 20, 100, 0);
			this.session.save(tempEnemyZ);
			tempEnemyB = new Enemy("Boar", 1, 300, 50, 10, 20);
			this.session.save(tempEnemyB);
			// Chapter One
			tempChapter = new Chapter("Chapter One");
			this.session.save(tempChapter);
			tempStage = new Stage("Facile", 125, 125, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyS, 1);
			this.session.save(tempEpl);
			tempStage = new Stage("Moyen", 400, 400, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyZ, 1);
			this.session.save(tempEpl);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyS, 1);
			this.session.save(tempEpl);
			tempStage = new Stage("Difficile", 70, 800, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyB, 2);
			this.session.save(tempEpl);
			tempStage = new Stage("Difficile+", 600, 850, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyZ, 2);
			this.session.save(tempEpl);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyZ, 1);
			this.session.save(tempEpl);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyS, 2);
			this.session.save(tempEpl);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyS, 2);
			this.session.save(tempEpl);
			// Chapter Two
			tempChapter = new Chapter("Chapter Two");
			this.session.save(tempChapter);
			tempStage = new Stage("Ultra Difficile", 150, 70, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyB, 3);
			this.session.save(tempEpl);
			// Chapter Three
			tempChapter = new Chapter("Chapter Three");
			this.session.save(tempChapter);
			tempStage = new Stage("Hardcore", 500, 70, tempChapter);
			this.session.save(tempStage);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyB, 5);
			this.session.save(tempEpl);
			tempEpl = new Enemy_Per_Stage(tempStage, tempEnemyB, 5);
			this.session.save(tempEpl);

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
		return bool;
	}

	public boolean InsertAllCharacters() {
		boolean bool = true;

		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			Character tempCharacter;

			tempCharacter = new Character("Nom-eos", 1, 400, 30, 100, 0,
					"Ce personnage est très détendu ,&n a trouvé son épée dans un champ de fleur&n et pense qu '' il a une grande destinée.");
			this.session.save(tempCharacter);

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
		return bool;

	}

	public Session OpenDatabaseConnection() {

		try {
			this.sessionFactory = new Configuration().configure().buildSessionFactory();
			this.session = sessionFactory.getCurrentSession();
			return this.session;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public void CloseDatabaseConnection() {
		try {
			this.sessionFactory.close();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory close failed." + ex);
			throw new ExceptionInInitializerError(ex);
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



		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

		
		/*this.sqlQuery = "SELECT Level_Id FROM Levels";
		try {
			ResultSet resultQuery;
			Session session = OpenDatabaseConnection();
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
		}*/

	}

	public void InsertAccountInDatabase(Account userAccount) {

		try {
			this.sqlQuery = "INSERT INTO Accounts (Username,Password_Hash,Account_Level) values('"
					+ userAccount.getUsername() + "','" + userAccount.getPasswordHash() + "',1)";
			Session session = OpenDatabaseConnection();
			statement.executeUpdate(sqlQuery);
			this.sqlQuery = "INSERT INTO Account_Own_Characters (Character_Id, Account_Id, Level,Experience_point)values (1,(SELECT Account_Id from Accounts where Username ='"
					+ userAccount.getUsername() + "'),1,0)";
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void TakePlayerProgression(Account userAccount) {
		Session session = OpenDatabaseConnection();
		ResultSet resultQuery;
		int levelId = 0;
		int chapterId = 1;
		List<Stage> listOfLevel = new ArrayList<>();
		List<List<Stage>> listOfChapter = new ArrayList<>();
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
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), bimg.getWidth(),
										bimg.getHeight(), new Image("/res/entity/" + resultQuery.getString(5) + "/"
												+ resultQuery.getString(5) + ".png")));
						bimg = null;
					} else {
						levelId = resultQuery.getInt("LEVEL_ID");
						listOfLevel.add(new Stage(resultQuery.getString(3), resultQuery.getBoolean(4),
								resultQuery.getInt(11), resultQuery.getInt(12)));
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(levelId - 1).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), bimg.getWidth(),
										bimg.getHeight(), new Image("/res/entity/" + resultQuery.getString(5) + "/"
												+ resultQuery.getString(5) + ".png")));

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
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), bimg.getWidth(),
										bimg.getHeight(), new Image("/res/entity/" + resultQuery.getString(5) + "/"
												+ resultQuery.getString(5) + ".png")));
					} else {
						levelId = resultQuery.getInt("LEVEL_ID");
						listOfLevel.add(new Stage(resultQuery.getString(3), resultQuery.getBoolean(4),
								resultQuery.getInt(11), resultQuery.getInt(12)));
						bimg = ImageIO.read(new File(
								"./res/entity/" + resultQuery.getString(5) + "/" + resultQuery.getString(5) + ".png"));
						listOfLevel.get(0).getListOfEnemy()
								.add(new Enemy(resultQuery.getString(5), resultQuery.getInt("LEVEL"),
										resultQuery.getInt("HEALTH"), resultQuery.getInt("DEFENSE"),
										resultQuery.getInt("ATTACK"), resultQuery.getInt("SPEED"), bimg.getWidth(),
										bimg.getHeight(), new Image("/res/entity/" + resultQuery.getString(5) + "/"
												+ resultQuery.getString(5) + ".png")));
						if (resultQuery.isLast()) {
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
			Session session = OpenDatabaseConnection();
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
				Session session = OpenDatabaseConnection();
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
								resultQueryOwnCharacters.getInt("speed"), bimg.getWidth(), bimg.getHeight(),
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
