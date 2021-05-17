package view.guis;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import controller.SignupController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.button.Button;
import model.textfield.TextField;

@Getter
@Setter
@NoArgsConstructor
public class GuiSignup extends Gui {
	private int middleButtonXPosition;
	private int requirementY;
	private int stateId;
	private int[] duration = { 200, 200, 200, 200, 200, 200 };
	private Image backgroundImage;
	private Animation knightIdleAnimation;
	private TrueTypeFont trueTypeFont;
	private String currentPassword = "";
	private String currentPasswordConfirmation = "";
	private String currentUsername = "";
	private String currentHiddenPassword = "";
	private String currentHiddenPasswordConfirmation = "";
	private String errorText = "";
	private TextField usernameTextField;
	private TextField passwordTextField;
	private TextField passwordConfirmationTextField;

	private List<Button> listOfCurrentButton;
	private SignupController controller;

	public GuiSignup(int state) {
		super(state);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.controller = new SignupController(this);
		this.buttonNeeded = new int[] {2,4};
		int textFieldHeight = 30;
		int middleComponentsWidth = 600;
		int middleButtonYPositionStarting = 380;
		this.trueTypeFont = new TrueTypeFont(new Font("Century Gothic", Font.BOLD, 20), true);

		this.middleButtonXPosition = ((gc.getWidth() / 2) - (middleComponentsWidth / 2));

		this.passwordTextField = new TextField(gc, trueTypeFont, middleButtonXPosition,
				middleButtonYPositionStarting + 70, middleComponentsWidth, textFieldHeight);
		this.usernameTextField = new TextField(gc, trueTypeFont, middleButtonXPosition, middleButtonYPositionStarting,
				middleComponentsWidth, textFieldHeight);
		this.passwordConfirmationTextField = new TextField(gc, trueTypeFont, middleButtonXPosition,
				middleButtonYPositionStarting + 140, middleComponentsWidth, textFieldHeight);

		this.requirementY = passwordConfirmationTextField.getY() + passwordConfirmationTextField.getHeight() + 10;

		this.listOfCurrentButton = new ArrayList<Button>();
		for(int i :this.buttonNeeded) {
			if (i == 2) {
				this.getListOfButtons().get(i).setY(700);
				this.getListOfButtons().get(i).setX(660);
				this.listOfCurrentButton.add(this.getListOfButtons().get(i));
			}else {
				this.listOfCurrentButton.add(this.getListOfButtons().get(i));
			}
		}

		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
		this.passwordConfirmationTextField.setText("");

		this.backgroundImage = new Image("/res/Main_Screen_Background.png");

		Image[] knightIdle = new Image[6];
		for (int i = 1; i < 7; i++)
			knightIdle[i - 1] = new Image("/res/entity/Nomeos/Idle/Knight_idle_0" + i + "_uninterlace.png");
		this.knightIdleAnimation = new Animation(knightIdle, duration);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Draw the background image
		g.drawImage(backgroundImage, 0, 0);
		// Draw the buttons
		for (Button button : this.listOfCurrentButton)
			button.draw();
		// Draw the three labels in white
		g.setColor(Color.white);
		g.drawString("* Enter your username : ", middleButtonXPosition, this.usernameTextField.getY() - 20);
		g.drawString("* Enter your password : ", middleButtonXPosition, this.passwordTextField.getY() - 20);
		g.drawString("* Confirm your password : ", middleButtonXPosition,
				this.passwordConfirmationTextField.getY() - 20);
		// Render the three TextFields with their colors
		this.usernameTextField.setBorderColor(Color.black);
		this.usernameTextField.setBackgroundColor(Color.white);
		this.usernameTextField.setTextColor(Color.black);

		this.passwordTextField.setBorderColor(Color.black);
		this.passwordTextField.setBackgroundColor(Color.white);
		this.passwordTextField.setTextColor(Color.black);

		this.passwordConfirmationTextField.setBorderColor(Color.black);
		this.passwordConfirmationTextField.setBackgroundColor(Color.white);
		this.passwordConfirmationTextField.setTextColor(Color.black);

		this.usernameTextField.render(gc, g);
		this.passwordTextField.render(gc, g);
		this.passwordConfirmationTextField.render(gc, g);
		// Draw the requirements that the user will follow
		g.drawString("Mandatory = *", middleButtonXPosition, requirementY);
		g.drawString("Fill the two text field with your username and password.", middleButtonXPosition,
				requirementY + 20);
		g.drawString("Username requirements :", middleButtonXPosition, requirementY + 40);
		g.drawString("- Make sure it's at least between 4 and 12 characters.", middleButtonXPosition,
				requirementY + 60);
		g.drawString("- Only alphanumeric characters can be usable.", middleButtonXPosition, requirementY + 80);
		g.drawString("Password requirements :", middleButtonXPosition, requirementY + 100);
		g.drawString("- Be sure to remember it.", middleButtonXPosition, requirementY + 120);
		// Draw the string for every error in the page
		g.setColor(Color.red);
		g.drawString(errorText, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		// Draw the standing character animaton
		g.drawAnimation(knightIdleAnimation, 100, 700);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	@Override
	public void keyReleased(int key, char c) {
		// Remove the error from the GUI
		this.errorText = this.usernameTextField.errorManagement(0);
		// Requirement check for the password confirmation textfield
		StepsIfPasswordConfirmationHasFocus(key, c);
		// Requirement check for the password textfield
		StepsIfPasswordHasFocus(key, c);
		// Requirement check for the username textfield
		StepsIfUsernameHasFocus(key, c);

	}

	public void StepsIfUsernameHasFocus(int key, char c) {
		if (this.usernameTextField.hasFocus()) {
			if (usernameTextField.getText().length() <= 12) {
				if (isAlphaNumeric(String.valueOf(c))) {
					this.currentUsername += c;
					this.usernameTextField.setText(currentUsername);
				}
				if (key == 211 || key == 14) {
					this.currentUsername = usernameTextField.getText();
				}
				this.usernameTextField.setCursorPos(this.usernameTextField.getText().length());
			} else {
				if (key == 211 || key == 14) {
					this.usernameTextField.setAcceptingInput(true);
					this.currentUsername = usernameTextField.getText();
				} else {
					this.usernameTextField.setAcceptingInput(false);
				}

			}
			if (key == 15) {
				this.usernameTextField.setFocus(false);
				this.passwordConfirmationTextField.setFocus(false);
				this.passwordTextField.setFocus(true);

			}

		}
	}

	public void StepsIfPasswordHasFocus(int key, char c) {
		if (this.passwordTextField.hasFocus()) {
			if (key == 200 || key == 203 || key == 205 || key == 208) {
				this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());
			}
			if (isAlphaNumericPassword(String.valueOf(c))) {
				this.currentPassword += c;
				this.currentHiddenPassword += "*";
				this.passwordTextField.setText(currentHiddenPassword);
			}
			if (key == 211 || key == 14) {

				this.currentHiddenPassword = this.passwordTextField.getText();
				int currentHiddenPasswordLength = this.currentHiddenPassword.length();
				this.currentPassword = currentPassword.substring(0, currentHiddenPasswordLength);
			}
			if (key == 15) {

				this.passwordTextField.setFocus(false);
				this.passwordConfirmationTextField.setFocus(true);
			}
			this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());

		}
	}

	public void StepsIfPasswordConfirmationHasFocus(int key, char c) {
		if (this.passwordConfirmationTextField.hasFocus()) {
			if (key == 200 || key == 203 || key == 205 || key == 208) {
				this.passwordConfirmationTextField.setCursorPos(this.passwordConfirmationTextField.getText().length());
			}
			if (isAlphaNumericPassword(String.valueOf(c))) {
				this.currentPasswordConfirmation += c;
				this.currentHiddenPasswordConfirmation += "*";
				this.passwordConfirmationTextField.setText(currentHiddenPasswordConfirmation);
			}
			if (key == 211 || key == 14) {

				this.currentHiddenPasswordConfirmation = this.passwordConfirmationTextField.getText();
				int currentHiddenPasswordLength = this.currentHiddenPasswordConfirmation.length();
				this.currentPasswordConfirmation = currentPasswordConfirmation.substring(0,
						currentHiddenPasswordLength);
			}
			this.passwordConfirmationTextField.setCursorPos(this.passwordConfirmationTextField.getText().length());
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(false);
			}
		}
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				this.controller.activeButton(b);
			}
		}

		if (isHoveringTextField(this.usernameTextField, x, y) && button == 0) {

			this.passwordTextField.setFocus(false);
			this.passwordConfirmationTextField.setFocus(false);
			this.usernameTextField.setFocus(true);
		}
		if (isHoveringTextField(this.passwordTextField, x, y) && button == 0) {
			this.usernameTextField.setFocus(false);
			this.passwordConfirmationTextField.setFocus(false);
			this.passwordTextField.setFocus(true);
		}
		if (isHoveringTextField(this.passwordConfirmationTextField, x, y) && button == 0) {

			this.usernameTextField.setFocus(false);
			this.passwordTextField.setFocus(false);
			this.passwordConfirmationTextField.setFocus(true);
		}
	}

	public void resetTextFieldContent() {
		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
		this.passwordConfirmationTextField.setText("");
		this.currentUsername = "";
		this.currentPassword = "";
		this.currentHiddenPassword = "";
		this.currentHiddenPasswordConfirmation = "";
		this.currentPasswordConfirmation = "";
	}

	public boolean isHoveringTextField(TextField textField, int x, int y) {
		return textField.getX() < x && textField.getX() + textField.getWidth() > x && textField.getY() < y
				&& textField.getY() + textField.getHeight() > y;
	}

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	public static boolean isAlphaNumericPassword(String s) {
		return s != null && s.matches("^[a-zA-Z0-9 +@\\\"*$#%&()=?'^~!{}\\/\\\\.:,;°§_<>]*");
	}


	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c) {
	}

}
