package controller;

import java.util.List;
import java.util.Random;

import main.Game;
import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;
import model.livingEntity.LivingEntity;
import model.rarity.Epic;
import model.rarity.Legendary;
import model.rarity.Rare;
import view.guis.GuiSummon;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Summon View
 * 
 * @author Mathieu Rabot
 *
 */
public class SummonController extends Controller {

	private GuiSummon guiSummon;
	private Random rnd;
	private List<LivingEntity> livingEntity;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiSummon this is the current Summon view and allows me to interact
	 *                  with it
	 */
	public SummonController(GuiSummon guiSummon) {
		this.guiSummon = guiSummon;
		this.rnd = new Random();
		
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		this.account = Game.getInstance().getPlayerAccount();
		if (currentButton instanceof BigButton) {
			this.livingEntity = this.jm.GetAllLivingEntities();

			double result = this.rnd.nextDouble();
			if (result <= 0.1) {
				this.summonLegendary();
			} else if (result > 0.1 && result <= 0.4) {
				this.summonEpic();
			} else if (result > 0.4 && result <= 1) {
				this.summonRare();
			}

		} else if (currentButton instanceof SmallButton) {
			this.changeView(3);
		}
	}

	/**
	 * This method makes a random summon between all legendary unit
	 */
	private void summonLegendary() {
		int numberOfLegendary = 0;
		int currentLegendary = 0;
		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Legendary) {
				numberOfLegendary++;
			}
		}
		int resultInt = this.rnd.nextInt(numberOfLegendary);

		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Legendary) {
				if (currentLegendary == resultInt) {
					displayOwnedCharacter(le);
					addCharacterUserAccount(le);
					break;
				} else {
					currentLegendary++;
				}
			}
		}
	}

	/**
	 * This method makes a random summon between all Epic unit
	 */
	private void summonEpic() {
		int numberOfEpic = 0;
		int currentEpic = 0;
		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Epic) {
				numberOfEpic++;
			}
		}
		int resultInt = this.rnd.nextInt(numberOfEpic);

		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Epic) {
				if (currentEpic == resultInt) {
					displayOwnedCharacter(le);
					addCharacterUserAccount(le);
					break;
				} else {
					currentEpic++;
				}
			}
		}
	}

	/**
	 * This method makes a random summon between all Rare unit
	 */
	private void summonRare() {
		int numberOfRare = 0;
		int currentRare = 0;
		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Rare) {
				numberOfRare++;
			}
		}
		int resultInt = this.rnd.nextInt(numberOfRare);
		for (LivingEntity le : this.livingEntity) {
			if (le.getRarity() instanceof Rare) {
				if (currentRare == resultInt) {
					displayOwnedCharacter(le);
					addCharacterUserAccount(le);
					break;
				} else {
					currentRare++;
				}
			}
		}
	}

	/**
	 * This method give the summoned character to the view and display it
	 * 
	 * @param le Current character that has been summoned
	 */
	private void displayOwnedCharacter(LivingEntity le) {
		le.init();
		this.guiSummon.setCurrentCharacterOwned(le);
	}

	/**
	 * This method call the database manager for insert the summoned character in the player account
	 * 
	 * @param le Current character that has been summoned
	 */
	private void addCharacterUserAccount(LivingEntity le) {
		this.jm.insertCharacterOwnedInAccount(this.account, le);
	}

}
