package controller;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.button.Button;
import model.button.LevelButton;
import view.guis.GuiAdventure;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Adventure View
 * 
 * @author Mathieu Rabot
 *
 */
public class AdventureController extends Controller {
	private GuiAdventure guiAdventure;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiChapters this is the current guiCharacter view and allows me to
	 *                    interact with it
	 */
	public AdventureController(GuiAdventure guiAdventure) {
		this.guiAdventure = guiAdventure;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof LevelButton) {
			Game.getInstance().setCurrentLevel(((LevelButton) currentButton).getLevelId());
			Game.getInstance().enterState(7, new FadeOutTransition(), new FadeInTransition());
		} else if (currentButton.getX() == 450) {
			this.changeView(5);
			this.guiAdventure.getListOfCurrentButtons().removeIf(n -> (n instanceof LevelButton));
			this.guiAdventure.getListOfLevels().clear();
			this.guiAdventure.setInitializeButtons(false);

		} else if (currentButton.getX() == 50) {
			this.changeView(3);
			this.guiAdventure.getListOfCurrentButtons().removeIf(n -> (n instanceof LevelButton));
			this.guiAdventure.getListOfLevels().clear();
			this.guiAdventure.setInitializeButtons(false);

		}
	}

}
