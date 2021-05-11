package model.account;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
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
import model.account_Own_Character.Account_Own_Character;
import model.stage_By_Account.Stage_By_Account;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Account_Id")
	private int id;
	
	@Column(name = "Account_Level")
	private int account_Level;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password_Hash")
	private String passwordHash;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Stage_By_Account> stage_By_Account = new HashSet<>();
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Account_Own_Character> account_Own_Character = new HashSet<>();
	
	@Transient
	private String password = "";

	public Account(String username, String password) throws NoSuchAlgorithmException {
		this.username = username;
		this.passwordHash = hashPassword(password);
	}

	public String hashPassword(String password) throws NoSuchAlgorithmException {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public boolean areThePasswordEquals(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(this.password, password);
	}
}
