package controller;

import model.button.Button;
import model.button.SmallButton;
import view.guis.GuiCharacter;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Character View
 * 
 * @author Mathieu Rabot
 *
 */
public class CharacterController extends Controller {
	private GuiCharacter guiCharacter;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiCharacter this is the current guiCharacter view and allows me to interact
	 *                  with it
	 */
	public CharacterController(GuiCharacter guiCharacter) {
		this.guiCharacter = guiCharacter;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof SmallButton) {
			this.changeView(3);
		} 
	}

}
