package controller;

import java.awt.Font;
import java.security.NoSuchAlgorithmException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Game;
import model.Account;
import model.JsonManager;

public class GuiSignup extends BasicGameState {
	private int x, y, requirementY, stateId, width, height;
	private Font ft = null;
	private Shape submitButton = null, loginButton = null;
	private String currentPassword = "", currentPasswordConfirmation = "", currentUsername = "",
			currentHiddenPassword = "", errorUsernameLengthString = "", missingFieldFillString = "",
			currentHiddenPasswordConfirmation = "", errorPasswordNotEqualsString = "",
			errorAccountAlreadyExistString = "";
	private float stringX, stringY;
	private Account account;
	private JsonManager jm;
	private TrueTypeFont trueTypeFont;
	private TextField usernameTextField, passwordTextField, passwordConfirmationTextField;
	private boolean errorUsernameLength, missingFieldFill, errorPasswordNotEquals, errorAccountAlreadyExist;

	public GuiSignup() {
	}

	public GuiSignup(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.height = 30;
		this.width = 600;

		this.x = ((gc.getWidth() / 2) - (width / 2));
		this.y = 450;

		this.ft = new Font("Century Gothic", Font.BOLD, 20);
		this.trueTypeFont = new TrueTypeFont(ft, true);

		this.account = new Account();
		this.jm = new JsonManager();

		this.passwordTextField = new TextField(gc, trueTypeFont, x, y, width, height);
		this.usernameTextField = new TextField(gc, trueTypeFont, x, y - 70, width, height);
		this.passwordConfirmationTextField = new TextField(gc, trueTypeFont, x, y + 70, width, height);

		this.requirementY = passwordConfirmationTextField.getY() + passwordConfirmationTextField.getHeight() + 10;

		this.loginButton = new Rectangle((gc.getWidth() - 400), (gc.getHeight() - 110), 350, 60);
		this.submitButton = new Rectangle(x, requirementY + 140, width, 100);

		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
		this.passwordConfirmationTextField.setText("");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("* Enter your username : ", x, this.usernameTextField.getY() - 20);
		g.drawString("* Enter your password : ", x, this.passwordTextField.getY() - 20);
		g.drawString("* Confirm your password : ", x, this.passwordConfirmationTextField.getY() - 20);

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

		g.drawString("Mandatory = *", x, requirementY);
		g.drawString("Fill the two text field with your username and password.", x, requirementY + 20);
		g.drawString("Username requirements :", x, requirementY + 40);
		g.drawString("- Make sure it's at least between 4 and 12 characters.", x, requirementY + 60);
		g.drawString("- Only alphanumeric characters can be usable.", x, requirementY + 80);
		g.drawString("Password requirements :", x, requirementY + 100);
		g.drawString("- Be sure to remember it.", x, requirementY + 120);

		g.setColor(Color.white);
		g.fill(submitButton);
		g.draw(submitButton);

		g.fill(loginButton);
		g.draw(loginButton);

		g.setColor(new Color(102, 204, 255));
		this.stringX = submitButton.getCenterX() - 40;
		this.stringY = submitButton.getCenterY() - 10;
		g.drawString("SUBMIT", stringX, stringY);

		this.stringX = loginButton.getCenterX() - (loginButton.getWidth() / 4);
		this.stringY = (loginButton.getCenterY() - (loginButton.getHeight() / 4)) + 5;
		g.drawString("Already have an account ? Login !", stringX - 60, stringY);

		g.setColor(Color.red);
		g.drawString(errorUsernameLengthString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(missingFieldFillString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(errorPasswordNotEqualsString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(errorAccountAlreadyExistString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (errorUsernameLength) {
			this.errorUsernameLengthString = "The length of the username is invalid (between 4 and 12 charactercs long).";
		} else {
			this.errorUsernameLengthString = "";
		}
		if (missingFieldFill) {
			this.missingFieldFillString = "Please fill the text fields with your informations.";
		} else {
			this.missingFieldFillString = "";
		}
		if (errorPasswordNotEquals) {
			this.errorPasswordNotEqualsString = "The password and the password confirmation are not equals.";
		} else {
			this.errorPasswordNotEqualsString = "";
		}
		if (errorAccountAlreadyExist) {
			this.errorAccountAlreadyExistString = "An account with this username already exist.";
		} else {
			this.errorAccountAlreadyExistString = "";
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		// Remove the error from the GUI
		this.errorUsernameLength = false;
		this.missingFieldFill = false;
		this.errorPasswordNotEquals = false;
		this.errorAccountAlreadyExist = false;

		// Requirement check for the password confirmation textfield
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
		// Requirement check for the password textfield
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
		// Requirement check for the username textfield
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

	@Override
	public void mousePressed(int button, int x, int y) {
		if (isHoveringButton(submitButton, x, y) && button == 0) {
			if (this.usernameTextField.getText() != "" && this.passwordTextField.getText() != ""
					&& this.passwordConfirmationTextField.getText() != "") {
				if (usernameTextField.getText().length() <= 12 && usernameTextField.getText().length() >= 4) {
					System.out.println(this.currentPassword + " ? " + this.currentPasswordConfirmation);
					if (this.currentPassword.equals(currentPasswordConfirmation)) {
						this.account.setUsername(this.usernameTextField.getText());
						try {
							this.account.setPasswordHash(this.account.hashPassword(this.currentPassword));
							if (this.jm.RegisterAccount(account)) {
								Game.getInstance().setTheRegisterSucessfull(true);
								Game.getInstance().enterState(1);
							} else {
								this.errorAccountAlreadyExist = true;
								this.usernameTextField.setText("");
								this.passwordTextField.setText("");
								this.passwordConfirmationTextField.setText("");
								this.currentUsername = "";
								this.currentPassword = "";
								this.currentHiddenPassword = "";
								this.currentHiddenPasswordConfirmation = "";
								this.currentPasswordConfirmation = "";
							}

						} catch (NoSuchAlgorithmException e) {

							e.printStackTrace();
						}
					} else {
						this.errorPasswordNotEquals = true;
					}
				} else {
					this.errorUsernameLength = true;
				}
			} else {
				this.missingFieldFill = true;
			}

		}
		if (isHoveringButton(loginButton, x, y) && button == 0) {
			this.errorUsernameLength = false;
			this.missingFieldFill = false;
			this.errorPasswordNotEquals = false;
			this.errorAccountAlreadyExist = false;
			this.usernameTextField.setText("");
			this.passwordTextField.setText("");
			this.passwordConfirmationTextField.setText("");
			this.currentUsername = "";
			this.currentPassword = "";
			this.currentHiddenPassword = "";
			this.currentHiddenPasswordConfirmation = "";
			this.currentPasswordConfirmation = "";
			Game.getInstance().enterState(1);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
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

	public boolean isHoveringTextField(TextField textField, int x, int y) {
		return textField.getX() < x && textField.getX() + textField.getWidth() > x && textField.getY() < y
				&& textField.getY() + textField.getHeight() > y;
	}

	public boolean isHoveringButton(Shape shape, int x, int y) {
		return shape.getX() < x && shape.getX() + shape.getWidth() > x && shape.getY() < y
				&& shape.getY() + shape.getHeight() > y;
	}

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	public static boolean isAlphaNumericPassword(String s) {
		return s != null && s.matches("/[a-zA-Z0-9 \\/\\\\+@\"*#%&()=?'^~!{}.:,;°§_<>]*$/gm");
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateId;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c) {
		System.out.println(key + " " + c);
	}

}
