package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.button.BigButton;
import model.button.Button;
import model.button.LevelButton;
import model.button.MediumButton;
import model.button.SmallButton;
import model.button.bigButton.BigChapterOneButton;
import model.button.bigButton.BigChapterThreeButton;
import model.button.bigButton.BigChapterTwoButton;
import model.button.mediumButton.MediumExitButton;
import model.button.mediumButton.MediumPlayButton;

/**
 * This class Contains every global variable that my views use
 * 
 * @author Mathieu Rabot
 */

@Getter
@Setter
@NoArgsConstructor
public class Gui extends BasicGameState {

	private int stateId;
	private List<Image> listOfBackgrounds;
	private List<Button> listOfButtons;
	protected int[] duration = { 200, 200, 200, 200, 200, 200 };
	protected String errorText = "";
	protected int[] buttonNeeded;
	protected TrueTypeFont trueTypeFont;
	protected Image backgroundImage;

	/**
	 * This is the constructor of this class
	 * 
	 * @param stateId It contains the current view Id
	 */
	public Gui(int stateId) {
		this.stateId = stateId;
		try {
			init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the init method of this class, it initialize the needed variable
	 * once.
	 * 
	 * @param gc  It's the GameContainer of the game, it contains every method that
	 *            i need for manage my game settings.
	 * @param sbg It's the StateBasedGame of the Game, it contains every listeners
	 *            and they the system of view changing.
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	private void init() throws SlickException {
		this.listOfBackgrounds = new ArrayList<Image>();
		for (int i = 1; i <= 9; i++) {
			this.listOfBackgrounds.add(new Image("/res/backgrounds/Background_0" + i + ".png"));
		}
		this.listOfButtons = new ArrayList<Button>();
		initListOfButtons();
	}

	/**
	 * This method initialize all buttons that the game will need
	 * 
	 * @throws SlickException it throws a Slick exception if there is any problem
	 *                        with the image
	 */
	private void initListOfButtons() throws SlickException {
		for (int i = 1; i <= 18; i++) {
			switch (i) {
			case 1:
				this.listOfButtons.add(new MediumPlayButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 2:
				this.listOfButtons.add(new MediumExitButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 3:
				this.listOfButtons.add(new MediumButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 4:
			case 5:
			case 15:
			case 17:
				this.listOfButtons.add(new SmallButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png"), 1520, 970));
				break;

			case 6:
			case 18:
				this.listOfButtons.add(new BigButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png"), 785, 365));
				break;

			case 7:
			case 9:
				this.listOfButtons.add(new SmallButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png"), 50, 970));
				break;

			case 8:
			case 16:
				this.listOfButtons.add(new SmallButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png"), 450, 970));
				break;

			case 10:
				this.listOfButtons.add(new BigChapterOneButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 11:
				this.listOfButtons.add(new BigChapterTwoButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 12:
				this.listOfButtons.add(new BigChapterThreeButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;

			case 13:
				this.listOfButtons.add(new LevelButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;
			case 14:
				this.listOfButtons.add(new LevelButton(new Image("/res/buttons/Button_" + i + ".png"),
						new Image("/res/buttons/Button_" + i + "_Hit.png")));
				break;
			}
		}
	}

	/**
	 * This is the render method of this class, it render every component every
	 * milliseconds.
	 * 
	 * @param gc  It's the GameContainer of the game, it contains every method that
	 *            i need for manage my game settings.
	 * @param sbg It's the StateBasedGame of the Game, it contains every listeners
	 *            and they the system of view changing.
	 * @param g   It's the Graphics of the game, it contains every method that allow
	 *            me to draw in the view like picture or shape.
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

	}

	/**
	 * This is the update method of this class, it update everything i need to
	 * update every milliseconds.
	 * 
	 * @param gc    It's the GameContainer of the game, it contains every method
	 *              that i need for manage my game settings.
	 * @param sbg   It's the StateBasedGame of the Game, it contains every listeners
	 *              and they the system of view changing.
	 * @param delta It's the delta variable of this game, it's a int the put +1
	 *              every milliseconds. It allows me to use it like a timer.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	/**
	 * This method is a listener that start when the mouse button is press
	 * 
	 * @param button this variable contains the mouse key which has been pressed
	 *               (left click is 0).
	 * @param x      this variable contains the X coordinate where the mouse has
	 *               been pressed
	 * @param y      this variable contains the Y coordinate where the mouse has
	 *               been pressed
	 */
	@Override
	public void mousePressed(int button, int x, int y) {

	}

	/**
	 * This method is a listener that start when the mouse button is release
	 * 
	 * @param button this variable contains the mouse key which has been released
	 *               (left click is 0).
	 * @param x      this variable contains the X coordinate where the mouse has
	 *               been pressed
	 * @param y      this variable contains the Y coordinate where the mouse has
	 *               been pressed
	 */
	@Override
	public void mouseReleased(int button, int x, int y) {

	}

	/**
	 * This is the getter of the current view Id
	 * 
	 * @return stateId It return the id of the current view
	 */
	@Override
	public int getID() {

		return this.stateId;
	}

}
