package controller;

import lombok.NoArgsConstructor;
import main.Game;
import model.button.Button;
import model.button.mediumButton.MediumExitButton;
import model.button.mediumButton.MediumPlayButton;
import view.guis.GuiMenu;

/**
 * this is a subclass of the Controller, it allow me to control every action of
 * the Menu view
 * 
 * @author Mathieu Rabot
 *
 */
@NoArgsConstructor
public class MenuController extends Controller {
	private GuiMenu guiMenu;
	
	public MenuController(GuiMenu guiMenu) {
		this.guiMenu = guiMenu;
	}
	
	public void activeButton(Button currentButton) {
		if (currentButton instanceof MediumPlayButton) {
			this.changeView(1);
		} else if (currentButton instanceof MediumExitButton) {
			Game.getInstance().getContainer().exit();
		}
	}

	

}
