package controller;

import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;
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

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiSummon this is the current Summon view and allows me to interact with
	 *                 it
	 */
	public SummonController(GuiSummon guiSummon) {
		this.guiSummon = guiSummon;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof BigButton) {
			
		} else if (currentButton instanceof SmallButton) {
			this.changeView(4);
		} 
	}

}
