package controller;

import model.*;

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

public class GuiLogin extends BasicGameState {
	private int x, y, requirementY, stateId, width, height;
	private Font ft = null;
	private Shape submitButton = null, signUp = null;
	private String currentPassword = "", currentUsername = "", currentHiddenPassword = "",
			errorUsernameLengthString = "", missingFieldFillString = "", registrationSuccessfulString = "",
			errorLoginFailString = "", loginSuccessfulString = "";
	private float stringX, stringY;
	private Account account;
	private JsonManager jm;
	private TrueTypeFont trueTypeFont;
	private TextField usernameTextField, passwordTextField;
	private boolean errorUsernameLength, missingFieldFill, errorLoginFail, loginSuccessful;

	public GuiLogin() {
	}

	public GuiLogin(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.account = new Account();
		this.jm = new JsonManager();
		this.height = 30;
		this.width = 600;
		this.x = ((gc.getWidth() / 2) - (width / 2));
		this.y = 500;
		this.errorUsernameLength = false;
		this.ft = new Font("Century Gothic", Font.BOLD, 20);
		this.trueTypeFont = new TrueTypeFont(ft, true);
		this.passwordTextField = new TextField(gc, trueTypeFont, x, y, width, height);
		this.usernameTextField = new TextField(gc, trueTypeFont, x, y - 70, width, height);
		this.submitButton = new Rectangle((gc.getWidth() - 170), (gc.getHeight() - 110), 120, 60);
		this.requirementY = passwordTextField.getY() + passwordTextField.getHeight() + 10;
		this.signUp = new Rectangle(x, requirementY + 20, width, 100);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("* Enter your username : ", x, this.usernameTextField.getY() - 20);
		g.drawString("* Enter your password : ", x, this.passwordTextField.getY() - 20);

		this.usernameTextField.setBorderColor(Color.black);
		this.usernameTextField.setBackgroundColor(Color.white);
		this.usernameTextField.setTextColor(Color.black);

		this.passwordTextField.setBorderColor(Color.black);
		this.passwordTextField.setBackgroundColor(Color.white);
		this.passwordTextField.setTextColor(Color.black);

		this.usernameTextField.render(gc, g);
		this.passwordTextField.render(gc, g);

		g.setColor(Color.white);
		g.fill(submitButton);
		g.draw(submitButton);

		g.fill(signUp);
		g.draw(signUp);

		g.setColor(new Color(102, 204, 255));
		this.stringX = signUp.getCenterX() - (signUp.getWidth() / 4);
		this.stringY = (signUp.getCenterY() - (signUp.getHeight() / 4)) + 5;
		g.drawString("No Account ? -> Sign up !", stringX + 20, stringY + 10);

		this.stringX = submitButton.getCenterX() - (submitButton.getWidth() / 4);
		this.stringY = (submitButton.getCenterY() - (submitButton.getHeight() / 4)) + 5;
		g.drawString("SUBMIT", stringX, stringY);

		g.setColor(Color.red);
		g.drawString(errorUsernameLengthString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(missingFieldFillString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(registrationSuccessfulString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(errorLoginFailString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(loginSuccessfulString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (Game.getInstance().getTheRegisterSucessfull()) {
			this.registrationSuccessfulString = "Registration Successful !";
		} else {
			this.registrationSuccessfulString = "";
		}
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
		if (errorLoginFail) {
			this.errorLoginFailString = "The Login failed. Try to reconnect or create a new account.";
		} else {
			this.errorLoginFailString = "";
		}
		if (loginSuccessful) {
			this.loginSuccessfulString = "Login Successful !";
		} else {
			this.loginSuccessfulString = "";
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateId;
	}

	@Override
	public void keyReleased(int key, char c) {
		this.errorUsernameLength = false;
		this.missingFieldFill = false;
		this.errorLoginFail = false;
		Game.getInstance().setTheRegisterSucessfull(false);

		if (this.passwordTextField.hasFocus()) {
			if (key == 200 || key == 203 || key == 205 || key == 208) {
				this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());
			}
			if ((key >= 2 && key <= 13) || (key >= 16 && key <= 27) || (key >= 30 && key <= 41)
					|| (key >= 43 && key <= 53 || key == 57 || key >= 71 && key <= 82)) {
				this.currentPassword += c;
				this.currentHiddenPassword += "*";
				this.passwordTextField.setText(currentHiddenPassword);
			}
			if (key == 211 || key == 14) {

				this.currentHiddenPassword = this.passwordTextField.getText();
				int currentHiddenPasswordLength = this.currentHiddenPassword.length();
				this.currentPassword = currentPassword.substring(0, currentHiddenPasswordLength);
			}
			this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());
		}
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

		}
	}

	public void drawString(String text, float x, float y, Color color) {
		this.trueTypeFont.drawString(x, y, text, color);
	}

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (isHoveringButton(submitButton, x, y) && button == 0) {
			if (this.usernameTextField.getText() != "" && this.passwordTextField.getText() != "") {
				if (usernameTextField.getText().length() <= 12 && usernameTextField.getText().length() >= 4) {
					this.account.setUsername(this.usernameTextField.getText());
					try {
						this.account.setPassword(this.currentPassword);
						this.account.setPasswordHash(this.account.hashPassword(this.currentPassword));
						if (this.jm.LoginAccount(this.account)) {
							this.loginSuccessful = true;
							Game.getInstance().enterState(3);
						} else {
							this.errorLoginFail = true;
							this.usernameTextField.setText("");
							this.passwordTextField.setText("");
							this.currentHiddenPassword = "";
							this.currentPassword = "";
							this.currentUsername = "";
						}

					} catch (NoSuchAlgorithmException e) {

						e.printStackTrace();
					}

				} else {
					this.errorUsernameLength = true;
				}
			} else {
				this.missingFieldFill = true;
			}

		}
		if (isHoveringButton(signUp, x, y) && button == 0) {
			this.errorUsernameLength = false;
			this.missingFieldFill = false;
			this.errorLoginFail = false;
			Game.getInstance().setTheRegisterSucessfull(false);
			Game.getInstance().enterState(2);
		}
	}

	public boolean isHoveringButton(Shape shape, int x, int y) {
		return shape.getX() < x && shape.getX() + shape.getWidth() > x && shape.getY() < y
				&& shape.getY() + shape.getHeight() > y;
	}

	@Override
	public void keyPressed(int key, char c) {

	}

	@Override
	public void mouseReleased(int button, int x, int y) {
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {

	}

}
