package model.account_Own_Character;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.account.Account;
import model.livingEntity.character.Character;

/**
 * This class create the link between Account and Character that define all the
 * characters that the account own
 * 
 * @author Mathieu Rabot
 *
 */

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Account_Own_Characters")
public class Account_Own_Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_Own_Character_Id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "character_Id")
	private Character character;

	@ManyToOne
	@JoinColumn(name = "account_Id")
	private Account account;

	@Column(name = "level")
	private Integer level;

	@Column(name = "experience_Point")
	private Integer experience_Point;

	/**
	 * This is the constructor of this class
	 * 
	 * @param character
	 * @param account
	 * @param level
	 * @param experience_Point
	 */
	public Account_Own_Character(Character character, Account account, Integer level, Integer experience_Point) {
		this.character = character;
		this.account = account;
		this.level = level;
		this.experience_Point = experience_Point;
	}

}
