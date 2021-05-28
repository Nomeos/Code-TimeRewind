package model.databaseManager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.account.Account;
import model.accountOwnCharacter.AccountOwnCharacter;
import model.chapter.Chapter;
import model.enemyPerStage.EnemyPerStage;
import model.livingEntity.LivingEntity;
import model.livingEntity.character.Character;
import model.livingEntity.character.Guiwi;
import model.livingEntity.character.Nomeos;
import model.livingEntity.character.Xtreme;
import model.livingEntity.enemy.Boar;
import model.livingEntity.enemy.Skeleton;
import model.livingEntity.enemy.Zombie;
import model.rarity.Epic;
import model.rarity.Legendary;
import model.rarity.Rare;
import model.stage.Stage;
import model.stageByAccount.StageByAccount;

@NoArgsConstructor
@Getter
@Setter
/**
 * This is the database manager class, it contains everything that connect to my
 * database
 * 
 * @author Mathieu Rabot
 *
 */
public class DatabaseAccountManager {

	private boolean isRegister = false;
	private Session session;
	private SessionFactory sessionFactory;
	private boolean isATest = false;

	/**
	 * This method create the database
	 */
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
		}
	}

	/**
	 * This method verify if the database has been created
	 * 
	 * @return It returns a boolean if the database has been created
	 */
	private boolean VerifyDatabaseCreated() {
		OpenDatabaseConnection();
		this.session.beginTransaction();
		boolean result;
		Query query;
		String sqlQuery;
		List<Character> characters;

		sqlQuery = "FROM Character LE WHERE LE.name = 'Nomeos'";
		query = session.createQuery(sqlQuery);
		characters = query.list();

		if (characters.isEmpty()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * This method insert all the default information that the game need for running
	 */
	private void InsertAllCharacters() {

		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			Epic tempEpic;
			Legendary tempLegendary;
			Rare tempRare;

			Guiwi tempGuiwi;
			Xtreme tempXtreme;
			Nomeos tempNomeos;

			Skeleton tempSkeleton;
			Boar tempBoar;
			Zombie tempZombie;

			Stage tempStage;
			Chapter tempChapter;
			EnemyPerStage tempEpl;

			tempLegendary = new Legendary();
			this.session.save(tempLegendary);

			tempEpic = new Epic();
			this.session.save(tempEpic);

			tempRare = new Rare();
			this.session.save(tempRare);

			tempNomeos = new Nomeos(tempEpic);
			this.session.save(tempNomeos);

			tempGuiwi = new Guiwi(tempLegendary);
			this.session.save(tempGuiwi);

			tempXtreme = new Xtreme(tempLegendary);
			this.session.save(tempXtreme);

			tempSkeleton = new Skeleton(tempRare);
			this.session.save(tempSkeleton);

			tempZombie = new Zombie(tempRare);
			this.session.save(tempZombie);

			tempBoar = new Boar(tempRare);
			this.session.save(tempBoar);

			// Chapter One

			tempChapter = new Chapter("Chapter One");
			this.session.save(tempChapter);
			tempStage = new Stage("Facile", 125, 125, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempSkeleton, 1);
			this.session.save(tempEpl);
			tempStage = new Stage("Moyen", 400, 400, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempZombie, 1);
			this.session.save(tempEpl);
			tempEpl = new EnemyPerStage(tempStage, tempSkeleton, 1);
			this.session.save(tempEpl);
			tempStage = new Stage("Difficile", 70, 800, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempBoar, 2);
			this.session.save(tempEpl);
			tempStage = new Stage("Difficile+", 600, 600, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempZombie, 2);
			this.session.save(tempEpl);
			tempEpl = new EnemyPerStage(tempStage, tempZombie, 1);
			this.session.save(tempEpl);
			tempEpl = new EnemyPerStage(tempStage, tempSkeleton, 2);
			this.session.save(tempEpl);
			tempEpl = new EnemyPerStage(tempStage, tempSkeleton, 2);
			this.session.save(tempEpl);

			// Chapter Two

			tempChapter = new Chapter("Chapter Two");
			this.session.save(tempChapter);
			tempStage = new Stage("Ultra Difficile", 150, 70, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempBoar, 3);
			this.session.save(tempEpl);

			// Chapter Three

			tempChapter = new Chapter("Chapter Three");
			this.session.save(tempChapter);
			tempStage = new Stage("Hardcore", 500, 70, tempChapter);
			this.session.save(tempStage);
			tempEpl = new EnemyPerStage(tempStage, tempBoar, 5);
			this.session.save(tempEpl);
			tempEpl = new EnemyPerStage(tempStage, tempBoar, 5);
			this.session.save(tempEpl);

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

	}

	/**
	 * This method open the database connection
	 * 
	 * @return It returns the current database session
	 */
	public Session OpenDatabaseConnection() {

		try {
			this.sessionFactory = new Configuration().configure().buildSessionFactory();
			this.session = sessionFactory.getCurrentSession();
			System.out.println("\n");
			return this.session;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * This method close the database connection
	 */
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

	/**
	 * This method call all the verification and register process for add the player
	 * to the database
	 * 
	 * @param userAccount current user account
	 * @return It returns a boolean if the user already exist
	 */
	public boolean RegisterAccount(Account userAccount) {
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

	/**
	 * This method call all the verification and login process for connect the
	 * player to his account
	 * 
	 * @param userAccount current user account
	 * @return It returns a boolean if the user already exist
	 */
	public boolean LoginAccount(Account userAccount) {
		this.isRegister = false;
		if (IsTheUserAlreadyExist(userAccount)) {
			if (!this.isATest) {
				GetPlayerProgression(userAccount);
			}
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method insert all the stages in the user account after his register
	 * 
	 * @param userAccount current user account
	 */
	public void InsertLevelsInAccount(Account userAccount) {
		Query query;
		String sqlQuery = "FROM Stage";
		StageByAccount sba;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			query = session.createQuery(sqlQuery, Stage.class);
			List<Stage> stages = query.list();
			for (Stage currentStage : stages) {
				sba = new StageByAccount(currentStage, userAccount, false);
				this.session.save(sba);
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
	}

	/**
	 * This method insert the user account in the database
	 * 
	 * @param userAccount current user account
	 */
	public void InsertAccountInDatabase(Account userAccount) {
		AccountOwnCharacter aoc;
		String sqlQuery;
		Query query;
		List<LivingEntity> character;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			userAccount.setAccountLevel(1);
			this.session.save(userAccount);

			sqlQuery = "FROM LivingEntity c WHERE c.name = 'Nomeos'";
			query = session.createQuery(sqlQuery);
			character = query.list();
			for (LivingEntity currentCharacter : character) {
				aoc = new AccountOwnCharacter(currentCharacter, userAccount, 1, 0);
				this.session.save(aoc);
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

	}

	/**
	 * This method verify if the user exist in the database
	 * 
	 * @param userAccount Current user account
	 * @return It returns a boolean if the user exist
	 */
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

	/**
	 * This method get all the player's progression 
	 * 
	 * @param userAccount current user account
	 */
	private void GetPlayerProgression(Account userAccount) {

		String sqlQuery;
		Query query;
		List<Account> account;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();

			sqlQuery = "FROM Account A WHERE A.username ='" + userAccount.getUsername() + "'";
			query = session.createQuery(sqlQuery);
			account = query.list();
			for (Account currentAccount : account) {
				for (AccountOwnCharacter aoc : currentAccount.getAccountOwnCharacter()) {
					aoc.getLivingEntity().setLevel(aoc.getLevel());
					aoc.getLivingEntity().setExperience(aoc.getExperiencePoint());
					aoc.getLivingEntity().init();
				}
				for (StageByAccount sba : currentAccount.getStageByAccount()) {
					sba.getStage().setLevelClear(sba.isLevelClear());
					for (EnemyPerStage eps : sba.getStage().getEnemy_Per_Stage()) {
						eps.getEnemy().setLevel(eps.getLevel());
						eps.getEnemy().init();
					}
				}
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

	/**
	 * This method get all entities that exist in the game
	 * 
	 * @return It returns the list with all entities
	 */
	public List<LivingEntity> GetAllLivingEntities() {
		String sqlQuery;
		Query query;
		List<LivingEntity> le;

		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			sqlQuery = "FROM LivingEntity";
			query = session.createQuery(sqlQuery);
			le = query.list();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
		return le;
	}

	/**
	 * This method add the summoned character in the user account
	 * 
	 * @param userAccount current user account
	 * @param le current entity owned
	 */
	public void insertCharacterOwnedInAccount(Account userAccount, LivingEntity le) {
		AccountOwnCharacter aoc;
		Query query;
		String sqlQuery;
		List<Account> accounts;
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();

			sqlQuery = "FROM Account A WHERE A.username ='" + userAccount.getUsername() + "'";
			query = session.createQuery(sqlQuery);
			accounts = query.list();
			for (Account currentAccount : accounts) {
				aoc = new AccountOwnCharacter(le, currentAccount, 1, 0);
				this.session.save(aoc);
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();

	}

	/**
	 * This method save the progression of the characters after the user's fight
	 * 
	 * @param userAccount current user account
	 * @param listOfCharacter characters that the user used in his fight
	 */
	public void saveCharacterOwnedAfterFight(Account userAccount, List<LivingEntity> listOfCharacter) {
		List<AccountOwnCharacter> aoc = Game.getInstance().getCurrentCharacterInFight();
		try {
			OpenDatabaseConnection();
			this.session.beginTransaction();
			int i = 0;
			for (AccountOwnCharacter currentAoc : aoc) {
				currentAoc.setExperiencePoint(listOfCharacter.get(i).getExperience());
				currentAoc.setLevel(listOfCharacter.get(i).getLevel());
				this.session.update(currentAoc);
				i++;
			}

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		this.session.getTransaction().commit();
		CloseDatabaseConnection();
	}

}
