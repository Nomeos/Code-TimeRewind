package controller;

import model.button.Button;
import model.button.SmallButton;
import view.guis.GuiEndFight;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the End Fight View
 * 
 * @author Mathieu Rabot
 *
 */
public class EndFightController extends Controller {

	private GuiEndFight guiEndFight;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiEndFight this is the current End fight view and allows me to interact with
	 *                 it
	 */
	public EndFightController(GuiEndFight guiEndFight) {
		this.guiEndFight = guiEndFight;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if(currentButton instanceof SmallButton) {
			this.changeView(6);
		}
	}

}
