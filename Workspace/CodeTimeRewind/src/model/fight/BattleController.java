package model.fight;

import java.util.List;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.animation.AnimationListener;
import model.entity.Character;
import model.entity.Enemy;

@Getter
@Setter
@NoArgsConstructor
public class BattleController implements InputProviderListener {
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private Character currentCharacter;
	private Enemy currentEnemy;
	private boolean isEnemiesTurn;
	private BattleCommand mode;

	public BattleController(List<Character> c, List<Enemy> e) {
		this.listOfEnemy = e;
		this.listOfCharacter = c;
		this.isEnemiesTurn = false;
		
	}
	
	public void init() {
		initAnimationListeners();
	}

	private void initAnimationListeners() {
		AnimationListener playerAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				playerAssignDamage();
			}
		};
		AnimationListener endPlayerAttack = new AnimationListener() {
			@Override
			public void on() {
				endPlayerAttack();
			}
		};
		AnimationListener ennemiAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				ennemyAssignDamage();
			}
		};
		AnimationListener endEnnemiAttack = new AnimationListener() {
			@Override
			public void on() {
				endEnnemyAttack();
			}
		};
		this.currentCharacter.addAnimationListener(playerAssignDamage, endPlayerAttack);
		this.currentEnemy.addAnimationListener(ennemiAssignDamage, endEnnemiAttack);
	}

	private void playerAssignDamage() {
		int result = this.currentCharacter.getAttack();
		float result1 = 100 / (100 + (float) this.currentEnemy.getDefense());
		int result2 = Math.round(result * result1);
		this.currentEnemy.setHealth(this.currentEnemy.getHealth() - result2);
	}

	private void endPlayerAttack() {
	}

	private void ennemyAssignDamage() {
		int result = this.currentEnemy.getAttack();
		float result1 = 100 / (100 + (float) this.currentCharacter.getDefense());
		int result2 = Math.round(result * result1);
		this.currentCharacter.setHealth(this.currentCharacter.getHealth() - result2);
	}

	private void endEnnemyAttack() {
	}

	@Override
	public void controlPressed(Command command) {
		this.mode = (BattleCommand) command;
		startBattle();
	}

	private void startBattle() {
		if(this.isEnemiesTurn) {
			switch (this.mode) {
			case SPELLONE:
				this.currentEnemy.startAttack();
				break;
			case SPELLTWO:
				this.currentEnemy.startAttack();
				break;
			case SPELLTHREE:
				this.currentEnemy.startAttack();
				break;

			}
		}else {
			switch (this.mode) {
			case SPELLONE:
				this.currentCharacter.startAttack();
				break;
			case SPELLTWO:
				this.currentCharacter.startAttack();
				break;
			case SPELLTHREE:
				this.currentCharacter.startAttack();
				break;

			}
		}
		
	}

	@Override
	public void controlReleased(Command command) {
	}
}