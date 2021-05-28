package model.databaseManager;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import junit.framework.Assert;
import lombok.Getter;
import lombok.Setter;
import model.account.Account;

@Getter
@Setter
/**
 * This is the test class that test the database manager
 * @author Mathieu Rabot
 *
 */
public class DatabaseAccountManagerTest {

	private DatabaseAccountManager jm;
	private Account account;
	private Session session;
	private String sqlQuery;

	File file;

	/**
	 * This method initialize the needed variable before the tests run
	 */
	@Before
	public void Init() {
		this.jm = new DatabaseAccountManager();
		this.account = new Account();
		this.DeleteDatabaseContent();
		this.jm.DatabaseCreation();

	}
	
	/**
	 * This test check if the database connection works
	 */
	@Test
	public void DatabaseConnection() {
		this.jm.OpenDatabaseConnection();
	}
	
	/**
	 * This test check if the user account does not exist
	 */
	@Test
	public void RegisterOKLoginNotOK() {
		try {
			this.account.setPasswordHash(this.account.hashPassword("test"));
			this.account.setPassword("test");
			this.account.setUsername("test");
			this.jm.RegisterAccount(account);
			this.account.setUsername("Albert");
			assertTrue(!this.jm.LoginAccount(account));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This test check if the user account exists
	 */
	@Test
	public void RegisterOKLoginOK() {
		try {
			this.account.setPasswordHash(this.account.hashPassword("test"));
			this.account.setPassword("test");
			this.account.setUsername("test");
			this.jm.RegisterAccount(account);
			this.jm.setATest(true);
			assertTrue(this.jm.LoginAccount(account));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method delete the database content before every tests
	 */
	private void DeleteDatabaseContent() {
		this.session = this.jm.OpenDatabaseConnection();
		this.session.beginTransaction();
		this.session.createQuery("DELETE FROM StageByAccount").executeUpdate();
		this.session.createQuery("DELETE FROM EnemyPerStage").executeUpdate();
		this.session.createQuery("DELETE FROM AccountOwnCharacter").executeUpdate();
		this.session.createQuery("DELETE FROM Stage").executeUpdate();
		this.session.createQuery("DELETE FROM LivingEntity").executeUpdate();
		this.session.createQuery("DELETE FROM Chapter").executeUpdate();
		this.session.createQuery("DELETE FROM Rarity").executeUpdate();
		this.session.createQuery("DELETE FROM Account").executeUpdate();
		this.session.getTransaction().commit();
		this.session.close();

	}

	/**
	 * This method clean every variable at the end of every tests
	 */
	@After
	public void Clean() {
		this.account = null;
		this.DeleteDatabaseContent();
		this.jm = null;
		this.setSession(null);

	}
}
