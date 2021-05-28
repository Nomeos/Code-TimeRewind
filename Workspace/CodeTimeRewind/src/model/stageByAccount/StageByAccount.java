package model.stageByAccount;

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
import model.stage.Stage;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "StageByAccount")
/**
 * 
 * This class create the link between Stage and Account that define all the
 * stages that the account own
 * 
 * @author Mathieu Rabot
 *
 */
public class StageByAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stageByAccount_Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "stage_Id")
	private Stage stage;

	@ManyToOne
	@JoinColumn(name = "account_Id")
	private Account account;

	@Column(name = "is_Level_Clear")
	boolean isLevelClear;

	/**
	 * This is the constructor of this class
	 * 
	 * @param levelTable This is the current stage
	 * @param account This is current player account
	 * @param isLevelClear This is a boolean that say if the stage is clear
	 */
	public StageByAccount(Stage levelTable, Account account, boolean isLevelClear) {
		this.stage = levelTable;
		this.account = account;
		this.isLevelClear = isLevelClear;
	}
}
