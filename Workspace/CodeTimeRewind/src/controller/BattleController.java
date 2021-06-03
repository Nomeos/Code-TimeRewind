package controller;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.animation.AnimationListener;
import model.animation.BezierPath;
import model.animation.PathAnimation;
import model.enemyPerStage.EnemyPerStage;
import model.fight.BattleCommand;
import model.livingEntity.LivingEntity;
import model.stageByAccount.StageByAccount;
import view.guis.BattleGameState;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Adventure View
 * 
 * @author Mathieu Rabot
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BattleController extends Controller implements InputProviderListener {
	private List<LivingEntity> listOfCharacter;
	private List<LivingEntity> listOfCurrentEnemies;
	private LivingEntity currentCharacter;
	private LivingEntity currentEnemy;
	private int currentSpell;
	private boolean isEnemiesTurn;
	private boolean isInitDone = false;
	private boolean isEndBattle = false;
	private StageByAccount currentLevel;

	private BattleCommand mode;
	private BattleGameState game;
	


	/**
	 * This is the principal constructor for this controller, it takes all the
	 * information he needs for manage the fight
	 * 
	 * @param listOfCharacter This is the list of character that the player is using
	 * @param listOfEnemy     This is the list of enemy that the player is fighting
	 * @param game            This is the current view that the player see
	 */
	public BattleController(BattleGameState game) {
		this.isEnemiesTurn = false;
		this.game = game;

	}

	/**
	 * This method initialize all needed variables
	 */
	public void init() {
		initAnimationListeners();
		
	}

	/**
	 * This method initialize all animation needed
	 */
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

	/**
	 * This method is use when the player assign damage to an enemy
	 */
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

	/**
	 * This method is use when the player end his turn
	 */
	private void endPlayerAttack() {
		this.game.setCurrentTurn(this.game.getCurrentTurn() + 1);
		for (LivingEntity e : this.listOfCurrentEnemies) {
			if (e.getHealth() <= 0) {
				e.setFadingOut(true);
			}
		}
		boolean end = false;
		for (LivingEntity e : this.listOfCurrentEnemies) {
			if (e.isFadingOut()) {
				end = true;
			} else {
				end = false;
				break;
			}
		}
		this.currentCharacter.setDone(false);
		if (end) {
			for (LivingEntity e : this.listOfCurrentEnemies) {
				for (LivingEntity c : this.listOfCharacter) {
					c.calculateExperienceEarned(e.getLevel());
					c.allocateEarnedExperience();

				}
			}
			this.jm.saveCharacterOwnedAfterFight(account, this.listOfCharacter);
			this.changeView(8);
			this.game.setInitiliazeVariable(false);
			
		}

	}

	/**
	 * This method is use when the enemy assign damage to player character
	 */
	private void ennemyAssignDamage() {

		this.currentEnemy.setCurrentSpell(currentSpell);
		this.currentEnemy.dealDamage(this.currentEnemy, this.currentCharacter);

	}

	/**
	 * This method is use when the enemy end his turn
	 */
	private void endEnnemyAttack() {
		this.game.setCurrentTurn(this.game.getCurrentTurn() + 1);
		this.game.setCurrentEnemyAnimation(this.game.getCurrentEnemyAnimation() + 1);
		this.currentEnemy.setDone(false);

	}

	/**
	 * 
	 */
	public void getAllEntitiesForThisStage() {
		
		int i = 1;
		if(this.listOfCurrentEnemies != null) {
			this.listOfCurrentEnemies = null;
		}
		List<StageByAccount> listSba = Game.getInstance().getPlayerAccount().getStageByAccount();
		for (StageByAccount sba : listSba) {
			if (i == Game.getInstance().getCurrentLevel()) {
				this.currentLevel = sba;
				break;
			} else {
				i++;
			}
		}

		List<LivingEntity> tempListOfEnemy = new ArrayList<LivingEntity>();

		for (EnemyPerStage eps : this.currentLevel.getStage().getEnemy_Per_Stage()) {
			eps.getEnemy().setLevel(eps.getLevel());
			eps.getEnemy().setAnEnemy(true);
			eps.getEnemy().setAnimation(this.game.getEnemyAnimation());
			tempListOfEnemy.add(eps.getEnemy());

		}
		this.listOfCurrentEnemies = tempListOfEnemy;

		List<LivingEntity> tempListOfCharacter = new ArrayList<LivingEntity>();
		int numberCharacterAccountHas = Game.getInstance().getPlayerAccount().getAccountOwnCharacter().size();
		if (numberCharacterAccountHas < 4) {
			for (int j = 0; j <= (numberCharacterAccountHas - 1); j++) {
				Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity()
						.setAnEnemy(false);
				Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity()
				.setAnimation(this.game.getCharacterAnimation());
				tempListOfCharacter.add(
						Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity());
				Game.getInstance().getCurrentCharacterInFight().add(Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j));
				
			}
		} else if (numberCharacterAccountHas >= 4) {
			for (int j = 0; j <= 3; j++) {
				Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity()
						.setAnEnemy(false);
				Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity()
				.setAnimation(this.game.getCharacterAnimation());
				tempListOfCharacter.add(
						Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j).getLivingEntity());
				Game.getInstance().getCurrentCharacterInFight().add(Game.getInstance().getPlayerAccount().getAccountOwnCharacter().get(j));
			}
		}
		this.listOfCharacter = tempListOfCharacter;
	}
	
	/**
	 * This method is call when a command has been used
	 */
	@Override
	public void controlPressed(Command command) {

		this.mode = (BattleCommand) command;
		startBattle();

	}

	/**
	 * This method start the attack and say which spell has been used
	 */
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

	/**
	 * This method is call when a command has been used
	 */
	@Override
	public void controlReleased(Command arg0) {

	}
}