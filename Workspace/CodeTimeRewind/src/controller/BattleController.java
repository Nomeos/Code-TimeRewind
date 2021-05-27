package controller;

import java.util.List;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.animation.AnimationListener;
import model.fight.BattleCommand;
import model.livingEntity.LivingEntity;
import view.guis.BattleGameState;

@Getter
@Setter
@NoArgsConstructor
public class BattleController extends Controller implements InputProviderListener {
	private List<LivingEntity> listOfCharacter;
	private List<LivingEntity> listOfEnemy;
	private LivingEntity currentCharacter;
	private LivingEntity currentEnemy;
	private int currentSpell;
	private boolean isEnemiesTurn;
	private boolean isInitDone = false;
	private boolean isEndBattle = false;

	private BattleCommand mode;
	private BattleGameState game;

	public BattleController(List<LivingEntity> listOfCharacter, List<LivingEntity> listOfEnemy, BattleGameState game) {
		this.listOfEnemy = listOfEnemy;
		this.listOfCharacter = listOfCharacter;
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
		this.currentCharacter.setCurrentSpell(currentSpell);
		// if (this.currentCharacter.isSingleAttack()) {
		this.currentCharacter.dealDamage(this.currentCharacter, this.currentEnemy);
		// } else {
		// for (LivingEntity e : this.listOfEnemy) {
		// this.currentCharacter.dealDamage(this.currentCharacter, e);
		// }
		// }

	}

	private void endPlayerAttack() {
		this.game.setCurrentTurn(this.game.getCurrentTurn() + 1);
		for (LivingEntity e : this.listOfEnemy) {
			if (e.getHealth() <= 0) {
				e.setFadingOut(true);
			}
		}
		boolean end = false;
		for (LivingEntity e : this.listOfEnemy) {
			if (e.isFadingOut()) {
				end = true;
			} else {
				end = false;
				break;
			}
		}
		this.currentCharacter.setDone(false);
		if (end) {
			for (LivingEntity e : this.listOfEnemy) {
				for (LivingEntity c : this.listOfCharacter) {
					c.calculateExperienceEarned(e.getLevel());
					c.allocateEarnedExperience();
					
				}
			}
			this.jm.saveCharacterOwnedAfterFight(account, this.listOfCharacter);
			this.changeView(8);
		}

	}

	private void ennemyAssignDamage() {

		this.currentEnemy.setCurrentSpell(currentSpell);
		this.currentEnemy.dealDamage(this.currentEnemy, this.currentCharacter);

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