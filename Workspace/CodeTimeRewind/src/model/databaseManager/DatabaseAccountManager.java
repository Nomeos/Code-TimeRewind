package model.databaseManager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.newdawn.slick.SlickException;

import lombok.NoArgsConstructor;
import main.Game;
import model.account.Account;
import model.account_Own_Character.Account_Own_Character;
import model.chapter.Chapter;
import model.enemy_Per_Stage.Enemy_Per_Stage;
import model.livingEntity.character.Character;
import model.livingEntity.enemy.Enemy;
import model.rarity.Epic;
import model.rarity.Legendary;
import model.rarity.Rare;
import model.stage.Stage;
import model.stage_By_Account.Stage_By_Account;

@NoArgsConstructor
public class DatabaseAccountManager {

	private boolean isRegister = false;
	private Session session;
	private SessionFactory sessionFactory;

	public void DatabaseCreation() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session = sessionFactory.getCurrentSession();
		this.session.close();

		if (VerifyDatabaseCreated()) {
			InsertAllCharacters();
			InsertTheFirstLevel();
		}
	}

	private boolean VerifyDatabaseCreated() {
		OpenDatabaseConnection();
		this.session.beginTransaction();
		boolean result;
		Query query;
		String sqlQuery;
		List<Character> characters;

		sqlQuery = "FROM Character LE WHERE LE.name = 'Nom-eos'";
		query = session.createQuery(sqlQuery);
		characters = query.list();

		if (characters.isEmpty()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private void InsertTheFirstLevel() {

		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			Enemy tempEnemyS;
			Enemy tempEnemyZ;
			Enemy tempEnemyB;
			Stage tempStage;
			Chapter tempChapter;
			Enemy_Per_Stage tempEpl;
			Rare tempRare;
			Epic tempEpic;
			tempEpic = new Epic();
			this.session.save(tempEpic);
			tempRare = new Rare();
			this.session.save(tempRare);
			tempEnemyS = new Enemy("Skeleton", 1, 100, 10, 150, 0, "Petit squelette argneux", tempRare);
			this.session.save(tempEnemyS);
			tempEnemyZ = new Enemy("Zombie", 1, 150, 20, 100, 0, "Petit zombie espiegle", tempRare);
			this.session.save(tempEnemyZ);
			tempEnemyB = new Enemy("Boar", 1, 300, 50, 10, 20, "Gros cochon violent", tempRare);
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
	}

	private void InsertAllCharacters() {

		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			Character tempCharacter;
			Legendary tempLegendary;
			
			tempLegendary = new Legendary();
			this.session.save(tempLegendary);
			tempCharacter = new Character("Nom-eos", 1, 400, 30, 100, 0,
					"Ce personnage est très détendu ,&n a trouvé son épée dans un champ de fleur&n et pense qu '' il a une grande destinée.",
					tempLegendary);
			this.session.save(tempCharacter);

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

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
		} finally {
			System.out.flush();
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

	// Principal method for check if the user can login public boolean
	public boolean LoginAccount(Account userAccount) throws SlickException {
		this.isRegister = false;
		if (IsTheUserAlreadyExist(userAccount)) {
			GetPlayerProgression(userAccount);
			return true;
		} else {
			return false;
		}

	}

	public void InsertLevelsInAccount(Account userAccount) {
		Query query;
		String sqlQuery = "FROM Stage";
		Stage_By_Account sba;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			query = session.createQuery(sqlQuery, Stage.class);
			List<Stage> stages = query.list();
			for (Stage currentStage : stages) {
				sba = new Stage_By_Account(currentStage, userAccount, false);
				this.session.save(sba);
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
	}

	public void InsertAccountInDatabase(Account userAccount) {
		Account_Own_Character aoc;
		String sqlQuery;
		Query query;
		List<Character> character;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			userAccount.setAccount_Level(1);
			this.session.save(userAccount);

			sqlQuery = "FROM Character c WHERE c.name = 'Nom-eos'";
			query = session.createQuery(sqlQuery);
			character = query.list();
			for (Character currentCharacter : character) {
				aoc = new Account_Own_Character(currentCharacter, userAccount, 1, 0);
				this.session.save(aoc);
			}

			/*
			 * this.sqlQuery =
			 * "INSERT INTO Accounts (Username,Password_Hash,Account_Level) values('" +
			 * userAccount.getUsername() + "','" + userAccount.getPasswordHash() + "',1)";
			 * Session session = OpenDatabaseConnection();
			 * statement.executeUpdate(sqlQuery); this.sqlQuery =
			 * "INSERT INTO Account_Own_Characters (Character_Id, Account_Id, Level,Experience_point)values (1,(SELECT Account_Id from Accounts where Username ='"
			 * + userAccount.getUsername() + "'),1,0)"; statement.executeUpdate(sqlQuery);
			 */
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

	}

	// Check if the user already exist in the json, it returns a boolean (works with
	// login and register).
	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;
		String sqlQuery;
		Query query;
		List<Account> account;
		if (isRegister) {

			try {
				OpenDatabaseConnection();
				this.session.beginTransaction();

				sqlQuery = "FROM Account A WHERE A.username ='" + userAccount.getUsername() + "'";
				query = session.createQuery(sqlQuery);
				account = query.list();

				if (account.isEmpty()) {
					result = false;
				} else {
					result = true;
				}

			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			this.session.getTransaction().commit();
			CloseDatabaseConnection();
		} else {
			try {

				OpenDatabaseConnection();
				this.session.beginTransaction();

				sqlQuery = "FROM Account A WHERE A.username ='" + userAccount.getUsername() + "'";
				query = session.createQuery(sqlQuery);
				account = query.list();
				for (Account currentAccount : account) {
					if (userAccount.areThePasswordEquals(currentAccount.getPasswordHash())) {
						result = true;
					}
				}

			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			this.session.getTransaction().commit();
			CloseDatabaseConnection();

		}
		return result;

	}

	private void GetPlayerProgression(Account userAccount) {

		String sqlQuery;
		Query query;
		List<Account> account;
		List<Account_Own_Character> accountOwnCharacter;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();

			sqlQuery = "FROM Account A WHERE A.username ='" + userAccount.getUsername() + "'";
			query = session.createQuery(sqlQuery);
			account = query.list();
			for (Account currentAccount : account) {
				userAccount = currentAccount;
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

		if (Game.getInstance() != null) {
			Game.getInstance().setPlayerAccount(userAccount);
		}

	}

}
