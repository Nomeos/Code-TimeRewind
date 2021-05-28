package model.account;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.accountOwnCharacter.AccountOwnCharacter;
import model.stageByAccount.StageByAccount;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
/**
 * This is the class account, it generate the table account in the database and
 * contains all the user informations
 * 
 * @author Mathieu Rabot
 *
 */
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Account_Id")
	private int id;

	@Column(name = "Account_Level")
	private int accountLevel;

	@Column(name = "Username")
	private String username;

	@Column(name = "Password_Hash")
	private String passwordHash;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<StageByAccount> stageByAccount = new ArrayList<StageByAccount>();

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<AccountOwnCharacter> accountOwnCharacter = new ArrayList<AccountOwnCharacter>();

	@Transient
	private String password = "";

	/**
	 * This is the constructor for this class
	 * 
	 * @param username This is the username of the player
	 * @param password This is the password of the player
	 * @throws NoSuchAlgorithmException It throws this exception because of the Password hash
	 */
	public Account(String username, String password) throws NoSuchAlgorithmException {
		this.username = username;
		this.passwordHash = hashPassword(password);
	}
	
	/**
	 * This method hash the password of the user
	 * 
	 * @param password This is the current password of the user
	 * @return It returns the password hash
	 * @throws NoSuchAlgorithmException It throws this exception because of the Password hash
	 */
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	/**
	 * This method verify if the password and the hash password are equals
	 * 
	 * @param password This is the password that will be test
	 * @return It returns a boolean with true if the password and the password hash are equals
	 */
	public boolean areThePasswordEquals(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(this.password, password);
	}
}
