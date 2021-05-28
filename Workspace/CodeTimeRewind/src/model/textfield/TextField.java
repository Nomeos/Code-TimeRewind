package model.textfield;

import org.newdawn.slick.Font;
import org.newdawn.slick.gui.GUIContext;

/**
 * This is an inheritance of slick's textField and do the error management
 * 
 * @author Mathieu Rabot
 *
 */
public class TextField extends org.newdawn.slick.gui.TextField {

	/**
	 * This is the constructor of this class
	 * 
	 * @param container This is the container of the game
	 * @param font      This is the font that the text use
	 * @param x         This is the X position of the textfield
	 * @param y         This is the Y position of the textfield
	 * @param width     This is the width of the textfield
	 * @param height    This is the height of the textfield
	 */
	public TextField(GUIContext container, Font font, int x, int y, int width, int height) {
		super(container, font, x, y, width, height);
	}

	/**
	 * This method manage all the errors that my login and register can make
	 * 
	 * @param errorId This is the error id
	 * @return It returns the text of the current error
	 */
	public String errorManagement(int errorId) {
		String s;
		switch (errorId) {
		case 1:
			s = "The length of the username is invalid (between 4 and 12 charactercs long).";
			break;
		case 2:
			s = "Please fill the text fields with your informations.";
			break;
		case 3:
			s = "The password and the password confirmation are not equals.";
			break;
		case 4:
			s = "An account with this username already exist.";
			break;
		case 5:
			s = "Registration Successful !";
			break;
		case 6:
			s = "The Login failed. Try to reconnect or create a new account.";
			break;
		default:
			s = "";
			break;

		}
		return s;

	}

}
