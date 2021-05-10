package model.stage_By_Account;

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
@Table(name = "Stage_By_Account")
public class Stage_By_Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stage_By_Account_Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "stage_Id")
	private Stage stage;

	@ManyToOne
	@JoinColumn(name = "account_Id")
	private Account account;

	boolean is_Level_Clear;

	public Stage_By_Account(Stage levelTable, Account account, boolean is_Level_Clear) {
		this.stage = levelTable;
		this.account = account;
		this.is_Level_Clear = is_Level_Clear;
	}
}
