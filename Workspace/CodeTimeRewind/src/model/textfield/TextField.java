package model.textfield;

import org.newdawn.slick.Font;
import org.newdawn.slick.gui.GUIContext;

public class TextField extends org.newdawn.slick.gui.TextField {

	public TextField(GUIContext container, Font font, int x, int y, int width, int height) {
		super(container, font, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

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
		case 6:
			s = "The Login failed. Try to reconnect or create a new account.";
		default:
			 s = "";

		}
		return s;

	}

}
