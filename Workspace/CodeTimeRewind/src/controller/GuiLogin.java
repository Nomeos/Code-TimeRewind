package controller;

import java.awt.Font;
import java.security.NoSuchAlgorithmException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.Account;
import model.DatabaseAccountManager;

public class GuiLogin extends BasicGameState {
	private int middleButtonXPosition;
	private int middleButtonYPositionStarting;
	private int stateId;
	private int middleComponentsWidth;
	private int textFieldHeight;
	private int smallButtonHeight;
	private int smallButtonWidth;
	private int signupButtonXPosition;
	private int signupButtonYPosition;
	private int submitButtonYPosition;
	private int[] duration = { 200, 200, 200, 200, 200, 200 };
	private Font ft = null;
	private String currentPassword = "";
	private String currentUsername = "";
	private String currentHiddenPassword = "";
	private String errorUsernameLengthString = "";
	private String missingFieldFillString = "";
	private String registrationSuccessfulString = "";
	private String errorLoginFailString = "";
	private String loginSuccessfulString = "";

	private Account account;
	private DatabaseAccountManager jm;
	private TrueTypeFont trueTypeFont;
	private TextField usernameTextField;
	private TextField passwordTextField;
	private boolean errorUsernameLength;
	private boolean missingFieldFill;
	private boolean errorLoginFail;
	private boolean loginSuccessful;
	private boolean initializeSubmit = true;
	private boolean isPressedSubmit = false;
	private boolean initializeSignup = true;
	private boolean isPressedSignup = false;
	private Image backgroundImage;
	private Image submitButton;
	private Image signupButton;
	private Animation knightIdleAnimation;

	public GuiLogin() {
	}

	public GuiLogin(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.textFieldHeight = 30;
		this.middleComponentsWidth = 600;
		this.smallButtonHeight = 60;
		this.smallButtonWidth = 350;
		this.signupButtonXPosition = (gc.getWidth() - 400);
		this.signupButtonYPosition = (gc.getHeight() - 110);
		this.middleButtonXPosition = ((gc.getWidth() / 2) - (middleComponentsWidth / 2));
		this.middleButtonYPositionStarting = 500;

		this.ft = new Font("Century Gothic", Font.BOLD, 20);
		this.trueTypeFont = new TrueTypeFont(ft, true);

		this.account = new Account();
		this.jm = new DatabaseAccountManager();

		this.errorUsernameLength = false;
		this.missingFieldFill = false;
		this.errorLoginFail = false;
		this.loginSuccessful = false;

		this.passwordTextField = new TextField(gc, trueTypeFont, middleButtonXPosition, middleButtonYPositionStarting,
				middleComponentsWidth, textFieldHeight);
		this.usernameTextField = new TextField(gc, trueTypeFont, middleButtonXPosition,
				middleButtonYPositionStarting - 70, middleComponentsWidth, textFieldHeight);

		this.submitButtonYPosition = this.passwordTextField.getY() + 50;

		this.backgroundImage = new Image("/res/Main_Screen_Background.png");

		Image[] knightIdle = { new Image("/res/entity/Nom-eos/Idle/Knight_idle_01_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Idle/Knight_idle_02_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Idle/Knight_idle_03_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Idle/Knight_idle_04_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Idle/Knight_idle_05_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Idle/Knight_idle_06_uninterlace.png") };
		this.knightIdleAnimation = new Animation(knightIdle, duration);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		if (initializeSubmit) {
			this.submitButton = new Image("/res/buttons/SubmitButton.png");
			this.submitButton.draw(middleButtonXPosition, submitButtonYPosition);
		} else {
			if (!isPressedSubmit) {
				this.submitButton.destroy();
				this.submitButton = new Image("/res/buttons/SubmitButton.png");
				this.submitButton.draw(middleButtonXPosition, submitButtonYPosition);
			} else {
				this.submitButton.destroy();
				this.submitButton = new Image("/res/buttons/SubmitButtonHit.png");
				this.submitButton.draw(middleButtonXPosition, submitButtonYPosition);
			}
		}
		if (initializeSignup) {
			this.signupButton = new Image("/res/buttons/SignupButton.png");
			this.signupButton.draw(signupButtonXPosition, signupButtonYPosition);
		} else {
			if (!isPressedSignup) {
				this.signupButton.destroy();
				this.signupButton = new Image("/res/buttons/SignupButton.png");
				this.signupButton.draw(signupButtonXPosition, signupButtonYPosition);
			} else {
				this.signupButton.destroy();
				this.signupButton = new Image("/res/buttons/SignupButtonHit.png");
				this.signupButton.draw(signupButtonXPosition, signupButtonYPosition);
			}
		}
		g.setColor(Color.white);
		g.drawString("* Enter your username : ", middleButtonXPosition, this.usernameTextField.getY() - 20);
		g.drawString("* Enter your password : ", middleButtonXPosition, this.passwordTextField.getY() - 20);

		this.usernameTextField.setBorderColor(Color.black);
		this.usernameTextField.setBackgroundColor(Color.white);
		this.usernameTextField.setTextColor(Color.black);

		this.passwordTextField.setBorderColor(Color.black);
		this.passwordTextField.setBackgroundColor(Color.white);
		this.passwordTextField.setTextColor(Color.black);

		this.usernameTextField.render(gc, g);
		this.passwordTextField.render(gc, g);

		g.setColor(Color.red);
		g.drawString(errorUsernameLengthString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(missingFieldFillString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(registrationSuccessfulString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(errorLoginFailString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawString(loginSuccessfulString, this.usernameTextField.getX(), this.usernameTextField.getY() - 40);
		g.drawAnimation(knightIdleAnimation, 100, 700);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
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
		return this.stateId;
	}

	@Override
	public void keyReleased(int key, char c) {
		this.errorUsernameLength = false;
		this.missingFieldFill = false;
		this.errorLoginFail = false;
		Game.getInstance().setTheRegisterSucessfull(false);
		StepsIfPasswordHasFocus(key,c);
		StepsIfUsernameHasFocus(key,c);
		
		
	}
	public void StepsIfPasswordHasFocus(int key, char c) {
		if (this.passwordTextField.hasFocus()) {
			if (key == 200 || key == 203 || key == 205 || key == 208) {
				this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());
			}
			if (isAlphaNumericPassword(Character.toString(c))) {
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
				this.usernameTextField.setFocus(true);
			}
			this.passwordTextField.setCursorPos(this.passwordTextField.getText().length());
		}
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
				this.passwordTextField.setFocus(true);
				this.usernameTextField.setFocus(false);
			}

		}
	}
	public void drawString(String text, float x, float y, Color color) {
		this.trueTypeFont.drawString(x, y, text, color);
	}

	public static boolean isAlphaNumeric(String s) {
		return s != null && s.matches("[a-zA-Z0-9]");
	}

	public static boolean isAlphaNumericPassword(String s) {
		return s != null && s.matches("^[a-zA-Z0-9 +@\\\"*$#%&()=?'^~!{}\\/\\\\.:,;§_<>]*");
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (middleButtonXPosition <= x && (middleButtonXPosition + 600) >= x && (submitButtonYPosition) <= y
				&& (submitButtonYPosition + 150) >= y && button == 0) {
			this.initializeSubmit = false;
			this.isPressedSubmit = true;
		}
		if (signupButtonXPosition <= x && (signupButtonXPosition + smallButtonWidth) >= x && signupButtonYPosition <= y
				&& (signupButtonYPosition + smallButtonHeight) >= y && button == 0) {
			this.initializeSignup = false;
			this.isPressedSignup = true;
		}

	}

	@Override
	public void keyPressed(int key, char c) {
		//Do nothing because the user will not release or press any key.
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.isPressedSubmit = false;
		this.isPressedSignup = false;

		if (middleButtonXPosition <= x && (middleButtonXPosition + 600) >= x && (submitButtonYPosition) <= y
				&& (submitButtonYPosition + 150) >= y && button == 0) {
			if (this.usernameTextField.getText() != "" && this.passwordTextField.getText() != "") {
				if (usernameTextField.getText().length() <= 12 && usernameTextField.getText().length() >= 4) {
					this.account.setUsername(this.usernameTextField.getText());
					try {
						this.account.setPassword(this.currentPassword);
						this.account.setPassword(currentPassword);
						this.account.setPasswordHash(this.account.hashPassword(this.currentPassword));
						if (this.jm.LoginAccount(this.account)) {
							this.loginSuccessful = true;
							Game.getInstance().enterState(3, new FadeOutTransition(), new FadeInTransition());
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
		if (signupButtonXPosition <= x && (signupButtonXPosition + smallButtonWidth) >= x && signupButtonYPosition <= y
				&& (signupButtonYPosition + smallButtonHeight) >= y && button == 0) {
			this.errorUsernameLength = false;
			this.missingFieldFill = false;
			this.errorLoginFail = false;
			this.usernameTextField.setText("");
			this.passwordTextField.setText("");
			this.currentHiddenPassword = "";
			this.currentPassword = "";
			this.currentUsername = "";
			Game.getInstance().setTheRegisterSucessfull(false);
			Game.getInstance().enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		//Do nothing because no action while use the mouse mouvement feature.
	}

}
