package controller;

import java.security.NoSuchAlgorithmException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.button.Button;
import model.button.MediumButton;
import model.button.SmallButton;
import view.guis.GuiLogin;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Login View
 * 
 * @author Mathieu Rabot
 *
 */
public class LoginController extends Controller {

	private GuiLogin guiLogin;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiLogin this is the current Login view and allows me to interact
	 *                  with it
	 */
	public LoginController(GuiLogin guiLogin) {
		this.guiLogin = guiLogin;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {

		if (currentButton instanceof MediumButton) {
			String username = this.guiLogin.getUsernameTextField().getText();
			if (!username.isEmpty() && !this.guiLogin.getCurrentPassword().isEmpty()) {
				if (username.length() <= 12 && username.length() >= 4) {
					this.getAccount().setUsername(username);
					try {
						this.getAccount().setPassword(this.guiLogin.getCurrentPassword());
						this.getAccount().setPasswordHash(
								this.getAccount().hashPassword(this.guiLogin.getCurrentPassword()));
						if (this.getJm().LoginAccount(this.getAccount())) {
							Game.getInstance().enterState(3, new FadeOutTransition(), new FadeInTransition());
						} else {
							this.guiLogin.resetTextFieldContent();
							this.guiLogin.setErrorText(this.guiLogin.getUsernameTextField().errorManagement(6));
						}
					} catch (NoSuchAlgorithmException | SlickException e) {

						e.printStackTrace();
					}
				} else {
					this.guiLogin.setErrorText(this.guiLogin.getUsernameTextField().errorManagement(1));

				}
			} else {
				this.guiLogin.setErrorText(this.guiLogin.getUsernameTextField().errorManagement(2));

			}
		} else if (currentButton instanceof SmallButton) {
			this.guiLogin.setErrorText(this.guiLogin.getUsernameTextField().errorManagement(0));
			this.guiLogin.resetTextFieldContent();
			Game.getInstance().setTheRegisterSucessfull(false);
			this.changeView(2);

		}

	}

}
