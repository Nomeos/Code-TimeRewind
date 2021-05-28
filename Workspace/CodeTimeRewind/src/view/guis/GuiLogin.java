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

import controller.LoginController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.account.Account;
import model.button.Button;
import model.databaseManager.DatabaseAccountManager;
import model.textfield.TextField;

@Getter
@Setter
@NoArgsConstructor
public class GuiLogin extends Gui {
	private int middleButtonXPosition;
	private int middleButtonYPositionStarting;
	private int stateId;
	private int[] duration = { 200, 200, 200, 200, 200, 200 };
	private Font ft = null;
	private String currentPassword = "";
	private String currentUsername = "";
	private String currentHiddenPassword = "";
	private String errorText = "";
	private Account account;
	private DatabaseAccountManager jm;
	private TrueTypeFont trueTypeFont;
	private TextField usernameTextField;
	private TextField passwordTextField;
	private Image backgroundImage;
	private Animation knightIdleAnimation;

	private List<Button> listOfCurrentButton;
	private LoginController controller;

	public GuiLogin(int state) {
		super(state);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.controller = new LoginController(this);
		this.buttonNeeded = new int[] { 2, 3 };

		int textFieldHeight = 30;
		int middleComponentsWidth = 600;
		this.middleButtonXPosition = ((gc.getWidth() / 2) - (middleComponentsWidth / 2));
		this.middleButtonYPositionStarting = 500;

		this.ft = new Font("Century Gothic", Font.BOLD, 20);
		this.trueTypeFont = new TrueTypeFont(ft, true);

		this.passwordTextField = new TextField(gc, trueTypeFont, middleButtonXPosition, middleButtonYPositionStarting,
				middleComponentsWidth, textFieldHeight);

		this.usernameTextField = new TextField(gc, trueTypeFont, middleButtonXPosition,
				middleButtonYPositionStarting - 70, middleComponentsWidth, textFieldHeight);

		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			if (i == 2) {
				this.getListOfButtons().get(i).setY(550);
				this.getListOfButtons().get(i).setX(660);
				this.listOfCurrentButton.add(this.getListOfButtons().get(i));
			} else {
				this.listOfCurrentButton.add(this.getListOfButtons().get(i));
			}
		}

		this.backgroundImage = this.getListOfBackgrounds().get(0);

		Image[] knightIdle = new Image[6];
		for (int i = 1; i < 7; i++)
			knightIdle[i - 1] = new Image("/res/entity/Nomeos/Idle/Knight_idle_0" + i + "_uninterlace.png");
		this.knightIdleAnimation = new Animation(knightIdle, duration);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		for (Button button : this.listOfCurrentButton)
			button.draw();
		g.setColor(Color.white);

		g.drawString("* Enter your username : ", middleButtonXPosition, this.usernameTextField.getY() - 25);
		g.drawString("* Enter your password : ", middleButtonXPosition, this.passwordTextField.getY() - 25);

		this.usernameTextField.setBorderColor(Color.black);
		this.usernameTextField.setBackgroundColor(Color.white);
		this.usernameTextField.setTextColor(Color.black);

		this.passwordTextField.setBorderColor(Color.black);
		this.passwordTextField.setBackgroundColor(Color.white);
		this.passwordTextField.setTextColor(Color.black);

		this.usernameTextField.render(gc, g);
		this.passwordTextField.render(gc, g);

		g.setColor(Color.red);
		g.drawString(errorText, middleButtonXPosition, middleButtonYPositionStarting - 115);
		g.drawAnimation(knightIdleAnimation, 100, 700);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Game.getInstance().isTheRegisterSucessfull()) {
			this.errorText = this.usernameTextField.errorManagement(5);
		}
	}


	@Override
	public void keyReleased(int key, char c) {
		if (!this.errorText.isEmpty()) {
			this.errorText = this.usernameTextField.errorManagement(0);
		}
		Game.getInstance().setTheRegisterSucessfull(false);
		StepsIfPasswordHasFocus(key, c);
		StepsIfUsernameHasFocus(key, c);

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
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(true);
			}
		}

	}

	@Override
	public void keyPressed(int key, char c) {
		// Do nothing because the user will not release or press any key.
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
	}

	public void resetTextFieldContent() {
		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
		this.currentHiddenPassword = "";
		this.currentPassword = "";
		this.currentUsername = "";
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing because no action while use the mouse mouvement feature.
	}

}
