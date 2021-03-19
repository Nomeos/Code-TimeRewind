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
import view.guis.BattleGameState;

@Getter
@Setter
@NoArgsConstructor
public class BattleController implements InputProviderListener {
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private Character currentCharacter;
	private Enemy currentEnemy;
	private int currentSpell;
	private boolean isEnemiesTurn;
	private boolean isInitDone = false;

	private BattleCommand mode;
	private BattleGameState game;

	public BattleController(List<Character> c, List<Enemy> e, BattleGameState game) {
		this.listOfEnemy = e;
		this.listOfCharacter = c;
		this.isEnemiesTurn = false;
		this.game = game;

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
		this.currentCharacter.getSpells().get(currentSpell).startDebutPassiveEffects(this.currentCharacter,this.currentEnemy);
		int result = this.currentCharacter.getAttack();
		float result1 = 100 / (100 + (float) this.currentEnemy.getDefense());
		int result2 = Math.round(result * result1);
		this.currentEnemy.setHealth(this.currentEnemy.getHealth() - result2);
		this.currentCharacter.getSpells().get(currentSpell).startEndPassiveEffects(this.currentCharacter,this.currentEnemy);
		

	}

	private void endPlayerAttack() {
		this.game.setCurrentTurn(this.game.getCurrentTurn() + 1);
		if(this.currentEnemy.getHealth()<=0) {
			this.currentEnemy.setFadingOut(true);
			
		}

	}

	private void ennemyAssignDamage() {

		int result = this.currentEnemy.getAttack();
		float result1 = 100 / (100 + (float) this.currentCharacter.getDefense());
		int result2 = Math.round(result * result1);
		this.currentCharacter.setHealth(this.currentCharacter.getHealth() - result2);

	}

	private void endEnnemyAttack() {
		this.game.setCurrentTurn(this.game.getCurrentTurn() + 1);
		this.game.setCurrentEnemyAnimation(this.game.getCurrentEnemyAnimation() + 1);
		this.currentEnemy.setDone(false);
	
	}

	@Override
	public void controlPressed(Command command) {

		this.mode = (BattleCommand) command;
		startBattle();

	}

	private void startBattle() {
		if (this.isEnemiesTurn) {
			switch (this.mode) {
			case SPELLONE:
				this.currentEnemy.startAttack();
				this.currentSpell = 1;
				break;
			case SPELLTWO:
				this.currentEnemy.startAttack();
				this.currentSpell = 2;
				break;
			case SPELLTHREE:
				this.currentEnemy.startAttack();
				this.currentSpell = 3;
				break;
			default:
				mode = BattleCommand.NONE;
				break;

			}
		} else {
			switch (this.mode) {
			case SPELLONE:
				this.currentCharacter.startAttack();
				this.currentSpell = 1;
				break;
			case SPELLTWO:
				this.currentCharacter.startAttack();
				this.currentSpell = 2;
				break;
			case SPELLTHREE:
				this.currentCharacter.startAttack();
				this.currentSpell = 3;
				break;
			default:
				mode = BattleCommand.NONE;
				break;

			}
		}

	}

	@Override
	public void controlReleased(Command arg0) {
		// TODO Auto-generated method stub

	}
}