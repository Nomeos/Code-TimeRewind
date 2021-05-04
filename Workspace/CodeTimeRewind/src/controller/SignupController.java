package controller;

import java.security.NoSuchAlgorithmException;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.button.Button;
import model.button.MediumButton;
import model.button.SmallButton;
import view.guis.GuiSignup;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Signup View
 * 
 * @author Mathieu Rabot
 *
 */
public class SignupController extends Controller {
	private GuiSignup guiSignup;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiSignup this is the current Signup view and allows me to interact
	 *                  with it
	 */
	public SignupController(GuiSignup guiSignup) {
		this.guiSignup = guiSignup;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof MediumButton) {
			// Check if the TextFields are fill
			if (this.guiSignup.getUsernameTextField().getText() != ""
					&& this.guiSignup.getPasswordTextField().getText() != ""
					&& this.guiSignup.getPasswordConfirmationTextField().getText() != "") {
				// Check if the username respect the length requirement.
				if (this.guiSignup.getUsernameTextField().getText().length() <= 12
						&& this.guiSignup.getUsernameTextField().getText().length() >= 4) {
					// check if the passwords TextField are equals
					if (this.guiSignup.getCurrentPassword().equals(this.guiSignup.getCurrentPasswordConfirmation())) {
						this.getAccount().setUsername(this.guiSignup.getUsernameTextField().getText());
						try {
							this.getAccount().setPasswordHash(
									this.getAccount().hashPassword(this.guiSignup.getCurrentPassword()));
							if (this.getJm().RegisterAccount(this.getAccount())) {
								Game.getInstance().setTheRegisterSucessfull(true);
								this.guiSignup.resetTextFieldContent();
								Game.getInstance().enterState(1, new FadeOutTransition(), new FadeInTransition());
							} else {
								this.guiSignup.setErrorText(this.guiSignup.getUsernameTextField().errorManagement(4));
								this.guiSignup.resetTextFieldContent();
							}

						} catch (NoSuchAlgorithmException | SlickException e) {

							e.printStackTrace();
						}
					} else {
						this.guiSignup.setErrorText(this.guiSignup.getUsernameTextField().errorManagement(3));
					}
				} else {
					this.guiSignup.setErrorText(this.guiSignup.getUsernameTextField().errorManagement(1));

				}
			} else {
				this.guiSignup.setErrorText(this.guiSignup.getUsernameTextField().errorManagement(2));

			}
		} else if (currentButton instanceof SmallButton) {
			this.guiSignup.setErrorText(this.guiSignup.getUsernameTextField().errorManagement(0));
			this.guiSignup.resetTextFieldContent();
			Game.getInstance().setTheRegisterSucessfull(false);
			this.changeView(1);

		}
	}

}
