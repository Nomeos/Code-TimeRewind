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

		}
	}

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
}
