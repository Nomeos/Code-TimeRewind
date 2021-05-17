package controller;

import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;
import view.guis.GuiLobby;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Lobby View
 * 
 * @author Mathieu Rabot
 *
 */
public class LobbyController extends Controller {

	private GuiLobby guiLobby;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiLobby this is the current Lobby view and allows me to interact with
	 *                 it
	 */
	public LobbyController(GuiLobby guiLobby) {
		this.guiLobby = guiLobby;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof BigButton) {
			this.changeView(5);
		} else if (currentButton instanceof SmallButton && currentButton.getX() == 50) {
			this.changeView(4);
		} else if (currentButton instanceof SmallButton && currentButton.getX() == 450) {

		} else if (currentButton instanceof SmallButton && currentButton.getX() == 1520) {
			this.changeView(9);
		}
	}

}
